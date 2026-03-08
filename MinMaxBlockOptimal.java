import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinMaxBlockOptimal {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,2,3,1,4,4,1,2,5);
		long startTime =  System.nanoTime();
		int result = minMaxBlock(list);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;

		System.out.println("Optimized Results: " + result);
	}
	public static int minMaxBlock(List<Integer> list) {
		Map<Integer, Integer> lastOccurr = new HashMap<>();
		Map<Integer, Integer> maxBlockSizes = new HashMap<>();

		for (int i = 0; i < list.size(); i++) {
			int num = list.get(i);
			if (lastOccurr.containsKey(num)) {
				maxBlockSizes.put(num, Math.max(maxBlockSizes.get(num), i - lastOccurr.get(num) - 1));
			} else {
				maxBlockSizes.put(num, i);
			}

			lastOccurr.put(num, i);

		}
		for (Map.Entry<Integer, Integer> e : lastOccurr.entrySet()) {
			int num = e.getKey();
			int pos = e.getValue();
			maxBlockSizes.put(num, Math.max(maxBlockSizes.get(num), list.size() - pos - 1));
		}

		int minNum = -1;
		int minBlockSize = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Integer> e : maxBlockSizes.entrySet()) {
			if (e.getValue() < minBlockSize) {
				minBlockSize = e.getValue();
				minNum = e.getKey();
			}
		}
		return minNum;
	}
}
