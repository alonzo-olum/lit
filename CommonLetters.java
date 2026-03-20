import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonLetters {
	public static void main(String[] args) {
		String s = "halloween";
		char[] letters = {'h', 'a', 'o', 'l'};
		System.out.println(
				Arrays.toString(
					common(s, letters)
					)
				);
	}

	public static char[] common(String s, char[] letters) {
		// set of s
		Set<Character> sSet = s.chars().mapToObj(obj -> (char) obj).collect(Collectors.toSet());
		// set of letters to eliminate dups
		Set<Character> lSet = new String(letters).chars().mapToObj(obj -> (char) obj).collect(Collectors.toSet());
		// common letters
		List<Character> add = new ArrayList<>();
		for (Character c : lSet) {
			if (sSet.contains(c))
				add.add(c);
		}
		char[] common = add.stream().map(String::valueOf).collect(Collectors.joining()).toCharArray();
		// sort
		Arrays.sort(common);
		return common;
	}
}
