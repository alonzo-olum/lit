class MinMaxScore {

	public static void main(String[] args) {
		int[] input1 = {12, 21, 25, 31, 44, 60};
		int[] input2 = {23, 34, 40, 42, 45, 50};
		int[] input3 = {23, 24, 25, 26, 27, 28};

		int target = 30;
		System.out.println(MinMaxScore.getIndex(input1, target));
	}

	public static int getIndex(int[] scores, int target) {
		int start = 0, end = scores.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			if (scores[mid] > target) {
				int j = mid;
				while (scores[j - 1] > target)  {
					j--;
				}
				// scores[j] is closest to target and hence smallest max
				return j;
			} else if (scores[mid] == target) {
				return mid;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}
}
