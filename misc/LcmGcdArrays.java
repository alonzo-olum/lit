import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LcmGcdArrays {

	public static void main(String[] args) {
		List<Integer> a = List.of(2, 4);
		List<Integer> b = List.of(16, 32, 64);
		System.out.println(commonIntegers(a, b));
	}

	public static int commonIntegers(List<Integer> a, List<Integer> b) {
		//range from lcm(a) to gcd(b)
		int lcmA = lcmArrays(a);
		int gcdB = gcdArrays(b);
		//
		int factor = 1;
		int target = 0;
		Set<Integer> setOfTarget = new HashSet<>();
		while (target <= gcdB) {
			target = lcmA * factor;
			// target = a[i] * factor
			outer:
			for (Integer i : a) {
				//
				// target % a[i] == 0 is the holding condition
				if (target % i != 0)
					break;
				for (Integer j : b) {
					//
					// b[i] % target == 0 is the holding condition
					if (j % target != 0)
						break outer;
				}
				setOfTarget.add(target);
			}
			factor++;
		}
		System.out.println(setOfTarget);
		return setOfTarget.size();
	}

	public static int lcm(int a, int b) {
		if (a == 0 || b == 0)
			return 0;
		return Math.abs(a * b) / gcd(a, b);
	}

	public static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	public static int gcdArrays(List<Integer> aList) {
		int result = aList.get(0);

		for (int i = 1; i < aList.size(); i++) {
			result = gcd(result, aList.get(1));
		}
		return result;
	}

	public static int lcmArrays(List<Integer> aList) {
		int result = aList.get(0);

		for (int i = 1; i < aList.size(); i++) {
			result = lcm(result, aList.get(i));
		}
		return result;
	}
}
