import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstrLength {

	public static void main(String[] args) {
		String s1 = "abacdcd";
		String s2 = "abacbc";
		String s3 = "ghdcac";
		String s4 = "bbcarc";
		String s5 = "abracadabrac";
		String s6 = "cabrab";
		String s7 = "ababc";
		String s8 = "abacdc";
		String s9 = "acacbd";
		String s10 = "cbacbc";
		for (String s : new String[] {s1, s2, s3,
			s4, s5, s6, s7,
			s8, s9, s10}) {
			System.out.println(substr(s));
		}
	}

	public static List<Integer> substr(String s) {
		// last occcurrence for each char
		Map<Character, Integer> lastOccur = new HashMap<>();
		for (int i = 0; i < s.length(); i++)
			lastOccur.put(s.charAt(i), i);
		
		int start = 0, end = 0;
		List<Integer> res = new ArrayList<>();
		for (int j = 0; j < s.length(); j++) {
			// for each character get farthest last occurrence (end)
			end = Math.max(end, lastOccur.get(s.charAt(j)));
			// if curr index == current end
			if (j == end) {
				// calculate the substr length and include to result
				res.add(j - start + 1);
				start = j + 1;
			}
		}
		return res;
	}
}
