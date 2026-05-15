import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class LogStock {
	public static void main(String[] args) throws InterruptedException {
		List<Stock> stockList = List.of(new Stock("NVDA", 201, 2), new Stock("AAPL", 125, 4), new Stock("DOW", 150, 3));
		//List<Stock> futureList = List.of(new Stock("S&P", 134, 8), new Stock("NASDAQ", 292, 1.5), new Stock("MNQ", 185, 2));
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 5; i++) {
			executor.submit(() -> System.out.println(logStock(6, 3, stockList)));
		}
		executor.shutdown();
		executor.awaitTermination(5, TimeUnit.SECONDS);
	}
	
	static final Object LOCK = new Object();

	public static List<String> logStock(int instances, int numOfStock, List<Stock> stocks) {
		int count = 1;
		List<String> logs = new ArrayList<>();

		// acquire lock however not as fast as lock-free strategies
		synchronized(LOCK) {
			while (count <= instances) {
			    String header = String.format("Second: %d", count);
			    StringBuilder sb = new StringBuilder();
			    stocks.forEach(stock -> {
			    	stock.updatePrice();
			    	sb.append(String.format("%s", stock));
			    	sb.append("\n");
			    });
			    logs.add(String.format("%s\n%s\n", header, sb.toString()));
			    count++;
			}
		}
		return logs;
	}

	static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	static final AtomicInteger counter = new AtomicInteger(0);

	public static List<String> logStockUpdated(int instances, int numOfStock, List<Stock> stocks) throws InterruptedException {
		List<String> logs = Collections.synchronizedList( new ArrayList<>());

		scheduler.scheduleAtFixedRate(() -> {
			int count = counter.incrementAndGet();
			String header = String.format("Second: %d", count);
			StringBuilder strBuilder = new StringBuilder();

			stocks.forEach(stock -> {
				stock.updatePrice();
				strBuilder.append(stock.toString());
				strBuilder.append("\n");
			});
			logs.add(String.format("%s\n%s", header, strBuilder.toString()));
			//System.out.println(strBuilder.toString());

			if (count >= instances)
			    scheduler.shutdown();
		}, 1, 1, TimeUnit.SECONDS);
		scheduler.awaitTermination(7, TimeUnit.SECONDS);
		return logs;
	}

	public static class Stock {
		private String name;
		private double price;
		private double incr;

		Stock(String name, double price, double incr) {
			this.name = name;
			this.price = price;
			this.incr = incr;
		}

		public void updatePrice() {
			this.price += this.price * incr / 100d;
		}

		@Override
		public String toString() {
			return this.name +": $"+ String.format("%.2f", this.price);
		}
	}
}
