
import static java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * A and B with varying length 1 to 1000
 * each element ranges 1 <= n < 10^6
 */
public class FindAndReplaceConstraints {

	public static void main(String[] args) {
		int[] A = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
		int[] B = {4, 12, 3, 9, 6, 1, 5, 8, 37, 25, 100};
		int [] C = findAndReplace(A, B);
		System.out.println(Arrays.toString(C));
	}

	public static int[] findAndReplace(int[] A, int[] B) {
		List<SimpleEntry<Integer, Integer>> B_sorted = new ArrayList<>();

		for (int i = 0; i < B.length; i++) {
			B_sorted.add(new SimpleEntry<>(B[i], i));
		}
		B_sorted.sort(Comparator.comparingInt(SimpleEntry::getKey));

		int j = 0;
		int[] C = new int[A.length];
		for (int i = 0; i < B.length; i++) {
			int target = 2 * B_sorted.get(i).getKey();
			while (j < B.length - 1 && B_sorted.get(j + 1).getKey() < target) {
				j++; // move the right pointer to find a number smaller or equal than target
			} 
			if (j < B.length - 1 &&
					Math.abs(B_sorted.get(j + 1).getKey() - target)
					< Math.abs(target - B_sorted.get(j).getKey())) {
						j++;
					}
			C[B_sorted.get(i).getValue()] = A[B_sorted.get(j).getValue()];
		}
		return C;
	}
}
