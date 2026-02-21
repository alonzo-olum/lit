import java.lang.Math;

public class NextPrime {
	public static int nextPrime(int number) {
		int counter;
		number++;

		while (true) {
			counter=0;
			for (int i=2; i <= Math.sqrt(number); i++) {
				if (number % i == 0) {
					counter++;
				}
				if (counter == 0) {
					return number;
				} else {
					number++;
					continue;
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(NextPrime.nextPrime(Integer.valueOf(args[0])));
	}
}
