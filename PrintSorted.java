
class PrintSorted {
	static final int NUM_CHARS = 26;

	public void printSortedStrings (int remaining) {
		printSortedStrings(remaining, "");
	}

	private void printSortedStrings(int remaining, String prefix) {
		if (remaining == 0) {
			if (isInOrder(prefix))
				System.out.println(prefix);
		} else {
			System.out.println("prefix: " + prefix);
			for (int i = 0; i < NUM_CHARS; i++) {
				char c = ithLetter(i);
				System.out.println("char from ithLetter: " + c);
				printSortedStrings(remaining - 1, prefix + c);
			}
		}
	}

	private boolean isInOrder(String prefix) {
		for (int i = 1; i < prefix.length(); i++) {
			int prev = ithLetter(prefix.charAt(i - 1));
			int curr = ithLetter(prefix.charAt(i));
			if (prev > curr) return false;
		}
		return true;
	}

	private char ithLetter(int index) {
		System.out.println("ithLetter index: " + index);
		return (char)(((int) 'a') +  index);
	}

	public static void main(String[] args) {
		PrintSorted printSorted = new PrintSorted();
		printSorted.printSortedStrings(3);
	}
}
