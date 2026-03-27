import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortestWordOccurrence {

	public static void main(String[] args) {
		List<String> words = List.of("dog", "bengal", "cat", "dog", "cat", "dog");
		System.out.println(solution(words));
	}
	public static Map<String, Integer> solution(List<String> wordList) {
		// word => indices map init
		Map<String, Set<Integer>> wordIndices = new HashMap<>();
		for (int i = 0; i < wordList.size(); i++) {
			wordIndices.computeIfAbsent(wordList.get(i), k -> new HashSet<>()).add(i);
		}
		// filter for ones with set elems > 1
		return wordIndices.entrySet().stream().filter(e -> e.getValue().size() > 1)
			.collect(Collectors.toMap(Map.Entry::getKey, e -> {
				List<Integer> sorted = new ArrayList<>(e.getValue());
				Collections.sort(sorted);
				// return map with word => min abs diff
				int min = Integer.MAX_VALUE;
				for (int j = 1; j < sorted.size(); j++) {
					int diff = sorted.get(j) - sorted.get(j - 1);
					if (diff < min)
						min = diff;
				}
				return min;
			}, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
