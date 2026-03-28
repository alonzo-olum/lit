import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrokenWords {
	public static void main(String[] args) {
		String s1 = "Life is like a box of chocolates";
		String s2 = "A fox jumped into the box";
		String s3 = "try as we may why";
		String s4 = "a rude tug of war would ruin our ray";
		String s5 = "still sea never made a skill mariner";
		String s6 = "my very educated mother";
		String s7 = "all good guys goad glory";
		String s8 = "bid thite it might bite";
		String s9 = "alot has been got in a spot";
		String s10 = "prepare to go ballistic";
		String[] cases = {s1, s2, s3, s4,
					s5, s6, s7, s8,
						s9, s10};
		Arrays.stream(cases).forEach(s -> System.out.println(optimizedSoln(s)));
	}
	public static Map.Entry<Character, Integer> solution(String s) {
		// delimit s by '\s'(space)
		String[] words = s.split("\\s");
		// remove dups from words
		Set<String> wordSet = Stream.of(words)
			.collect(Collectors.toCollection(LinkedHashSet::new));
		Map<Character, Integer> charAppear = new LinkedHashMap<>();
		// we intend to iterate each word once
		for (String word : wordSet) {
			Set<Character> charSet = word.chars()
				.mapToObj(obj -> (char) obj)
				.collect(Collectors.toCollection(LinkedHashSet::new));
			// each chars in word, incr count
			for (Character c : charSet) {
				charAppear.put(c, charAppear.getOrDefault(c, 0) + 1);
			}
		}
		// get the char most occurring,
		// this is the char with the most broken words
		return charAppear.entrySet().stream()
			.max(Map.Entry.comparingByValue())
			.get();
	}

	public static Map.Entry<Character, Integer> optimizedSoln(String s) {
		// delimit
		String[] words = s.split("\\s");
		// dedup
		Set<String> wordSet = Stream.of(words)
			.collect(Collectors.toCollection(LinkedHashSet::new));
		Map<Character, Set<String>> charSet = new LinkedHashMap<>();
		// add each word to charSet
		for (String word : wordSet) {
			Set<Character> cSet = word.chars()
				.mapToObj(obj -> (char) obj)
				.collect(Collectors.toCollection(LinkedHashSet::new));
			for (Character c : cSet) {
				charSet.computeIfAbsent(c, k -> new HashSet<>()).add(word);
			}
		}
		// return the Map.Entry w/ most words
		return charSet.entrySet().stream()
			.max(Comparator.comparingInt(e -> e.getValue().size()))
			.map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size()))
			.orElse(new AbstractMap.SimpleEntry<>('\0', 0));
	}
}
