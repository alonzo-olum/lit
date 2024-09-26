import java.lang.Math;
import java.util.stream.IntStream;

class Prime {
	public static boolean isPrime(int number) {
	    for (int i=2; i <= Math.sqrt(number); i++) {
	        if (number % i == 0) {
		    return false;
		}
	    }
	    return true;
	}

	public static void main(String[] args) {
	    int range = Integer.valueOf(args[0]);
	    IntStream.range(1, range)
		    .filter(Prime::isPrime)
		    .forEach(System.out::println);
	}
}
