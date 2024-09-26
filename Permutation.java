
class Permutation {
    public static void permutation(String str) {
        permutation(str, "");
    }

    private static void permutation(String str, String prefix) {
        if (str.length() == 0) {
	    System.out.println("final: " + prefix);
	} else {
	    for (int i = 0; i < str.length(); i++) {
		System.out.println("str :" + str);
		System.out.println("(0, " + i +") "+ str.substring(0, i));
		System.out.println(i + "+1 " + str.substring(i+1));
	        String rem = str.substring(0, i) + str.substring(i+1);
		permutation(rem, prefix + str.charAt(i));
	    }
	}
    }

    public static void main(String[] args) {
        Permutation.permutation(args[0]);
    }
}
