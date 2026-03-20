import static java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindReplaceII {

	public static void main(String[] args) {
		long[] a = {10, 20, 30, 40, 50};
		long[] b = {7, 5, 1, 2, 4};
		long[] c = FindReplaceII.replace(a, b);
		System.out.println(Arrays.toString(c));
	}
	public static long[] replace(long[] A, long[] B) {
		List<SimpleEntry<Long, Integer>> B_sorted = new ArrayList<>();
		for (int i = 0; i < B.length; i++) {
			B_sorted.add(new SimpleEntry<Long, Integer>(B[i], i));
		}
		B_sorted.sort(Comparator.comparingLong(SimpleEntry::getKey));

		long[] C = new long[A.length];
		for (int i = 0; i < B.length; i++) {
			if (i == 0) {
				C[B_sorted.get(i).getValue()] = A[B_sorted.get(i + 1).getValue()];
			} else if (i == B.length - 1) {
				C[B_sorted.get(i).getValue()] = A[B_sorted.get(i - 1).getValue()];
			} else if (B_sorted.get(i).getKey() - B_sorted.get(i - 1).getKey() < B_sorted.get(i + 1).getKey() - B_sorted.get(i).getKey()) {
				C[B_sorted.get(i).getValue()] = A[B_sorted.get(i - 1).getValue()];
			} else {
				C[B_sorted.get(i).getValue()] = A[B_sorted.get(i + 1).getValue()];
			}
		}
		return C;
	}
}
