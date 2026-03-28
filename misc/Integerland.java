import java.util.List;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Integerland {

	public static void main(String[] args) {
		Integerland intLand = new Integerland();
		List<int[]> queries = List.of(
				new int[]{1, 5},
				new int[]{2, 6},
				new int[]{3, 7},
				new int[]{4, 8},
				new int[]{5, 9}
				);
		System.out.println(intLand.sumNumbers(queries));
	}
	public List<Long> sumNumbers(List<int[]> queries) {
		return queries.stream().mapToLong(q -> {
			long nUpper = Math.max(q[0], q[1]);
			long nLower = Math.min(q[0], q[1]) - 1;

			Function<? super Long, ? extends Long> triSum = a -> a * (a + 1) / 2;
			return triSum.apply(nUpper) - triSum.apply(nLower);
		}).boxed().collect(Collectors.toList());
	}
}
