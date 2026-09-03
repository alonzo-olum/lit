import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringSum {

	/*
	 * @param array: {1, 2, 3, 4, 5}
	 * @param k: 5
	 * @returns: {2, 3}
	 */
	public static List<Integer> getLongestSubarray(List<Integer> array, int k) {
		if (array.stream().mapToInt(Integer::valueOf).sum() == k)
			return array;

		int left = 0, right = 1;
		int maxLen = 0, maxStart = 0;
		int currSum = array.get(left);

		while (left <= right && right < array.size()) {
			if (currSum < k) {
				currSum += array.get(right);
				right++;
			} else if (currSum > k) {
				currSum -= array.get(left);
				left++;
			} else {
				if ((right - left) > maxLen) {
					maxLen   = right - left;
					maxStart = left;
				}
				currSum += array.get(right);
				right++;
			}
		}
		return array.subList(maxStart, maxStart + maxLen);
	}

	public static void main(String[] args) {
		List<Integer> list1 = List.of(1, 1000, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1);
		System.out.println(LongestSubstringSum.getLongestSubarray(list1, 10));
	}
}
