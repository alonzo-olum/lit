import java.util.ArrayList;
import java.util.List;

public class CommonElements {

	public static void main(String[] args) {
		List<Integer> lst1 = List.of(1, 2, 3, 4, 5);
		List<Integer> lst2 = List.of(6, 7, 8, 4, 5);
		System.out.println(common(lst1, lst2));
	}

	public static List<Integer> common(List<Integer> lst1, List<Integer> lst2) {
		List<Integer> common = new ArrayList<>();

		for (Integer l : lst1) {
			if (lst2.contains(l)) {
				common.add(l);
			}
		}
		return common;
	}
		
}
