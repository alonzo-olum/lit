import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonStrs {
	public static void main(String[] args) {
		String[] arr1 = {"mars", "jupiter", "pluto", "venus"};
		String[] arr2 = {"jupiter", "venus", "earth", "neptune"};
		System.out.println(
				Arrays.toString(common(arr1, arr2))
				);
	}

	public static Boolean[] common(String[] arr1, String[] arr2) {
		// set of arr2
		Set<String> a2Set = Stream.of(arr2).collect(Collectors.toSet());
		// arr1[i] contained in arr2
		Boolean[] exists = new Boolean[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			exists[i] = a2Set.contains(arr1[i]);
		}
		// add boolean to array
		return exists;
	}
}
