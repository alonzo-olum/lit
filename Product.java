
class Product {
	private static int product(int number, int factor) {
		int sum = 0;
		for (int i = 0; i < factor; i++) {
			sum+=number;
		}
		return sum;
	}

	public static void main (String[] args) {
		int number = 5, factor = 10;
		System.out.println(number +" * "+ factor +" = "+ Product.product(number, factor));
	}
}
