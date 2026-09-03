import java.util.List;

class MaxSubArraySum {

	public static int solve(int[] nums, int target) {
		int max = 0, sum = 0;
		for (int start = 0, end = 0; end < nums.length; end++) {
			sum += nums[end];
			while (sum > target) {
				sum -= nums[start];
				start++;
			}
			max = Math.max(max, end - start + 1);
			System.out.println(nums[start] +" "+ nums[end]);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] nums = { -1, 2, 3, 1, -6, 5 };
		int[] nums2 = {1, 1000, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1};
		System.out.println(MaxSubArraySum.solve(nums2, 10));
	}
}
