
class SumDigits {
	private static int sumDigits(int number) {
		int digits = 0;
		while (number > 0) {
			digits+= number % 10;
			number/=10;
		}
		return digits;
	}

	public static void main(String[] args) {
		System.out.println("Digits in 105 "+ SumDigits.sumDigits(105));
	}
}
