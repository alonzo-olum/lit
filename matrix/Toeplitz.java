
public class Toeplitz {
	public static void main(String[] args) {
		int[][] cells = {{5, 9, 4, 1}, {2, 5, 9, 4}, {3, 2, 5, 9}};
		System.out.println(Toeplitz.toeplitz(cells));
	}

	public static boolean toeplitz(int[][] cells) {
		for (int r = 1; r < cells.length; r++) {
			for (int c = 1; c < cells[0].length; c++) {
				if (cells[r][c] != cells[r - 1][c - 1])
					return false;
			}
		}
		return true;
	}
}
