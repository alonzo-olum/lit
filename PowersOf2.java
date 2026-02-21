
class PowersOf2 {
    private static int powersOf2(int exponent) {
        if (exponent < 1) {
	    return 0;
	} else if (exponent == 1) {
	    System.out.println(1);
	    return 1;
	} else {
	    int previous = powersOf2(exponent / 2);
	    int current = previous * 2;
	    System.out.println(current);
	    return current;
	}
    }

    public static void main(String[] args) {
	int exponent = 8;
        PowersOf2.powersOf2(exponent);
    }
}
