import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimalMaxBlock {
	public static int bruteForce(List<Integer> list1) {
		int minMaxBlockSize = Integer.MAX_VALUE;
		int minValue = -1;
		Set<Integer> unique = new HashSet<>(list);
		for (Integer num : unique) {
			List<Integer> indices = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == num) {
					indices.add(i);
				}
			}
			indices.add(0, -1);
			indices.add(list.size());
			int maxBlockSize = 0;
			for (int i = 0; i < list.size(); i++) {
				maxBlockSize = Math.max(maxBlockSize, indices.get(i) - indices.get(i - 1) - 1);
			}

			if (maxBlockSize < minMaxBlockSize) {
				minMaxBlockSize =  maxBlockSize;
				minValue = num;
			}
		}

		return minValue;
	}
}
