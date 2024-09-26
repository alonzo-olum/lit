
class Sqrt {
    private static int sqrt(int number) {
        return sqrtHelper(number, 1, number);
    }

    private static int sqrtHelper(int number, int min, int max) {
	System.out.println("min: "+ min + " max: "+ max);
        if (max < min) return -1;
	int guess = (min+max) / 2;
	if (guess * guess == number) {
	    // found it
	    return guess;
	} else if (guess * guess < number) {
            // too low
	    return sqrtHelper(number, guess + 1, max);
	} else {
	    // too high
            return sqrtHelper(number, min, guess - 1);
	}
    }

    public static  void main (String[] args) {
        int number = 100;
	System.out.println("Sqrt of "+ number +" is "+ Sqrt.sqrt(number));
    }
}
