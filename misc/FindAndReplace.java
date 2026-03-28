import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindAndReplace {

	public static void main(String[] args) {
		int[] A = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};
		int[] B = {4, 12, 3, 9, 6, 1, 5, 8, 37, 25, 100};
		int[] C = findAndReplace(A, B);
		System.out.println(Arrays.toString(C));
	}

	public static int[] findAndReplace(int[] A, int[] B) {
		int[] C = new int[A.length];

		for (int i = 0; i < B.length; i++) {
			int target = 2 * B[i];
			 int j = indexOf(target, B);
			 C[i] = A[j];
		}
		return C;
	}

	private static int indexOf(int target, int[] B) {
		Map<Integer, Integer> elemDifference = new HashMap<>();
		for (int i = 0; i < B.length; i++) {
			elemDifference.put(i, Math.abs(B[i] - target));
		}
		return elemDifference.entrySet()
			.stream()
			.min(Map.Entry.comparingByValue())
			.map(Map.Entry::getKey)
			.orElse(null);
	}
}
