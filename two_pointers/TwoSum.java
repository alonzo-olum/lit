import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class TwoSum {
	public static void main(String[] args) {
		int[] numbers = {3, 5, 2, 7, 9, 4};
		List<Map.Entry<Integer, Integer>> pairs = findPairsOptimized(numbers, 11);
		System.out.println(pairs);
	}

	public static ArrayList<Map.Entry<Integer, Integer>> findPairs(int[] numbers, int target) {
		Map<Integer, Integer> pairer = new HashMap<>();
		Map<Integer, Integer> result = new HashMap<>();

		int length = numbers.length - 1;
		for (int i = 0; i < length; i++) {
			if (pairer.containsKey(target - numbers[i])) {
				result.put(pairer.get(target - numbers[i]), i);
			} else {
				pairer.put(numbers[i], i);
			}
		}
		return new ArrayList<>(result.entrySet());
	} 

	public static List<Map.Entry<Integer, Integer>> findPairsOptimized(int[] numbers, int target) {
		Arrays.sort(numbers);
		int left = 0, right = numbers.length - 1;
		List<Map.Entry<Integer, Integer>> pairs = new ArrayList<>();
		while (left < right) {
			int total = numbers[left] + numbers[right];
			if ( total == target) {
				pairs.add(new SimpleEntry<>(numbers[left], numbers[right]));
				left++;
				right--;
			} else if ( total > target) {
				right--;
			} else {
				left++;
			}
		}
		return pairs;
	}
}
