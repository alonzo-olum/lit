
class Permutation {
	public static void permutation(String str) {
		permutation(str, "");
	}

	private static void permutation(String str, String prefix) {
		if (str.length() == 0) {
			System.out.println("final: " + prefix);
		} else {
			for (int i = 0; i < str.length(); i++) {
				System.out.println("what is string now?" + str);
				System.out.println("what will rem be ?" + str.substring(0,i));
				String rem = str.substring(0, i) + str.substring(i+1);
				System.out.println("str.substring(0, " 
						+ i 
						+ ") " 
						+ str.substring(0,i));
				System.out.println("str.substring(" 
						+ i 
						+"+1) " 
						+ str.substring(i+1));
				System.out.println("rem :" 
						+ rem 
						+ ", str.charAt(" 
						+ i 
						+ ") is "
						+ str.charAt(i));
				permutation(rem, prefix + str.charAt(i));
			}
		}
	}

	public static void main(String[] args) {
		Permutation.permutation(args[0]);
	}
}
