import java.util.ArrayList;
import java.util.List;

class MinValueQueries {
	public static List<Integer> queryMin(int[] arr, List<Integer> Ls, List<Integer> Rs) {
		int n           = arr.length;
		int[][] preCalc = new int[n][n];
		for (int l = 0; l < n; ++l) {
			int minVal = arr[l];
			for (int r = l; r < n; ++r) {
				minVal        = Math.min(minVal, arr[r]);
				preCalc[l][r] = minVal;

			}
		}
		List<Integer> res = new ArrayList<>();
		for (int l = 0; l < Ls.size(); ++l) {
			res.add(preCalc[Ls.get(l)][Rs.get(l)]);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = {2, 1, 3, 7, 5};
		List<Integer> Ls = new ArrayList<>();
		List<Integer> Rs = new ArrayList<>();

		Ls.add(0);
		Rs.add(1);
		Ls.add(2);
		Rs.add(3);
		Ls.add(4);
		Rs.add(4);
		System.out.println(MinValueQueries.queryMin(arr, Ls, Rs));
	}
}
