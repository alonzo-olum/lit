/*
 * page 1 is always on the right side
 * flip page 1 and they see page 2, 3
 * each page except last is printed on both sides 
 * book n pages and student want to turn to p
 * min num of pages to turn to
 */
class TurnPages {
	public static void main(String[] args) {
		int n = 6, p = 5;
		System.out.println(pageCount(n, p));
	}
	public static int pageCount(int n, int p) {
		int minPages = Math.min(p / 2, (n - p) / 2);
		if (p == 1)
			return minPages;
		if (p == (n - 1) && p % 2 != 0)
			minPages++;
		return minPages;
	}
}
