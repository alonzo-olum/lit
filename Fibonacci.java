class Fibonacci {
    private static final int FIB_NUM = 10;

    private static void allFib(int n) {
        int [] memo = new int[n+1];
	for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + fibonacci(i, memo));
	}
    }
    
    private static int fibonacci(int n, int[] memo) {
        if (n <= 0)  return 0;
        else if (n == 1) return 1;
        else if (memo[n] > 0) return memo[n];
        // memoization for already established fib(n)
        memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
	allFib(FIB_NUM);
    }
}
