public class CountingValleys {

	public static void main(String[] args) {
		String path = "UDDDUDUU";
		System.out.println(countingValleys(8, path));
	}
	public static int countingValleys(int steps, String path) {
		int altitude = 0, valleys = 0;
		boolean level = true;

		for (Character c : path.toCharArray()) {
			if (c == 'U')
				altitude++;
			if (c == 'D')
				altitude--;

			if (altitude < 0 && level) {
				level = false;
				valleys++;
			}

			if (altitude >= 0 && !level)
				level = true;
		}
		return valleys;
	}
}
