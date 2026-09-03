import java.util.*;

public class FourSum {
	public static List<Integer> findFourSum(int targetSum, List<Integer> numbers) {
		int length                       = numbers.size();
		Map<Integer, List<int[]>> sumMap = new HashMap<>();
		// Step 2: populate map with sum pairs
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int pairSum = numbers.get(i) + numbers.get(j);
				sumMap.computeIfAbsent(pairSum, k -> new ArrayList<>()).add(new int[]{i, j});
			}
		}
		// Step 3: Iterate over the sums
		for (int sum : sumMap.keySet()) {
			int complement = targetSum - sum;
			if (sumMap.containsKey(complement)) {
				List<int[]> pairs1 = sumMap.get(sum);
				List<int[]> pairs2 = sumMap.get(complement);
				for (int[] pair1 : pairs1) {
					for (int[] pair2 : pairs2) {
						int a = pair1[0], b = pair1[1];
						int c = pair2[0], d = pair2[1];
						// Ensure all indices are distinct
						if (a != c && a != d && b != c && b != d) {
							return Arrays.asList(numbers.get(a), numbers.get(b), numbers.get(c), numbers.get(d));
						}
					}
				}
			}
		}
		return Collections.emptyList();
	}

	public static void main(String[] args)  {
		int target            = 24;
		List<Integer> numbers = Arrays.asList(5, 15, 2, 7, 8, 4);
		System.out.println(findFourSum(target, numbers));
	}
}
