 public class Kadane {
	 public static int[] findSubMatrix(int[][] matrix) {
		 int maxSum = Integer.MIN_VALUE;
		 // top, left, bottom, right
		 int[] result = new int[4];

		 for (int top = 0; top < matrix.length; top++) {
			 int[] temp = new int[matrix[0].length];
			 for (int bottom = top; bottom < matrix.length; bottom++) {
				 for (int i = 0; i < matrix[0].length; i++) {
					 temp[i] = matrix[bottom][i];
				 }
				 int[] currentResult = kadane(temp);
				 if (currentResult[2] > maxSum) {
					 maxSum = currentResult[2];
					 result[0] = top;
					 result[1] = currentResult[0];
					 result[2] = bottom;
					 result[3] = currentResult[1];
				 }
			 }
		 }
		 return result;
	 }

	 private static int[] kadane(int[] temp) {
		 int maxSum = Integer.MIN_VALUE;
		 int currentSum = 0, start = 0, maxStart = 0, maxEnd = 0;

		 for (int i = 0; i < temp.length; i++) {
			 currentSum += temp[i];
			 if (currentSum > maxSum) {
				 maxSum = currentSum;
				 maxStart = start;
				 maxEnd = i;
			 }
			 if (currentSum < 0) {
				 currentSum = 0;
				 start = i + 1;
			 }
		 }
		 return new int[]{maxStart, maxEnd, maxSum};
	 }
 }
