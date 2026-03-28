import java.util.Arrays;

public class MaxKSubArray {
	public static void main(String[] args) {
		System.out.println(
				Arrays.toString(maxSum(new int[] {2, 1, 5, 1, 3, 2}, 3))
				);
	}

	public static int[] maxSum(int[] nums, int k) {
		int startIndex = 0;
		long winSum = 0;
		// first window sum
		for (int i = 0; i < k; i++) {
			winSum += nums[i];
		}
		long maxSum = winSum;
		// subsequent window sum
		for (int i = k; i < nums.length; i++) {
			winSum += nums[i] - nums[i - k];
			if (winSum > maxSum) {
				maxSum = winSum;
				startIndex = i - k + 1;
			}
		}
		return new int[] {(int)maxSum, startIndex};
	}
}
