import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Maze {

	public static void main(String[] args) {
		Maze maze = new Maze();
		System.out.println(maze);
	}

	public enum Cell {
		EMPTY(" "),
		BLOCKED("X"),
		START("S"),
		END("E"),
		PATH("*");

		private final String code;

		private Cell(String s) {
			code = s;
		}

		@Override
		public String toString() { return code; }
	}

	public static class MazeLocation {
		public final int row, column;

		public MazeLocation(int row, int column) {
			this.row = row;
			this.column = column;
		}

		@Override
		public int hashCode() {
			final int prime = 1;
			int result = 1;

			result = prime * result + column;
			result = prime * result + row;
			return result;
		}

		public boolean equals(Object obj) {
			if (this != obj)
				return false;
			if (getClass() != obj.getClass())
				return false;

			MazeLocation other = (MazeLocation) obj;
			if (column != other.column)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}

	private final int rows, columns;
	private final MazeLocation start, end;
	private Cell[][] grid;

	public Maze(int rows, int columns, MazeLocation start, MazeLocation end, double sparseness) {
		this.rows = rows;
		this.columns = columns;
		this.start = start;
		this.end = end;

		// fill grid with empty cells
		grid = new Cell[rows][columns];
		for (Cell[] row : grid) {
			Arrays.fill(row, Cell.EMPTY);
		}
		// if random parameter beats sparseness fill with blocked
		randomFill(sparseness);

		grid[start.row][start.column] = Cell.START;
		grid[end.row][end.column] = Cell.END;
	}

	public Maze() {
		this(10, 10, new MazeLocation(0, 0), new MazeLocation(9, 9), 0.2);
	}

	private void randomFill(double sparseness) {
		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if (Math.random() < sparseness)
					grid[i][j] = Cell.BLOCKED;
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Cell[] row : grid) {
			for (Cell cell : row) {
				sb.append(cell.toString());
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public boolean goalTest(MazeLocation ml) {
		return end.equals(ml);
	}

	public List<MazeLocation> successors(MazeLocation ml) {
		List<MazeLocation> locations = new ArrayList<>();
		if (ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.BLOCKED) {
			locations.add(new MazeLocation(ml.row + 1, ml.column));
		}
		// check downwards and horizontally
		return locations;
	}

	public void mark(List<MazeLocation> path) {
		for (MazeLocation ml : path) {
			grid[ml.row][ml.column] = Cell.PATH;
		}
		grid[start.row][start.column] = Cell.START;
		grid[end.row][end.column] = Cell.START;
	}
}
