package Array;


 class RangeSum2D {
	 int [][]ps;
	 RangeSum2D(int[][]arr){
		 int n=arr.length,m=arr[0].length;
		 ps= new int[n][m];
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<m;j++) {
				 if(j==0) {
					 ps[i][j]=arr[i][j];
					 
				 }else {
					 ps[i][j]=ps[i][j-1]+arr[i][j];
				 }
			 }
		 }
		 for(int j=0;j<m;j++) {
			 for(int i=1;i<n;i++) {
				 ps[i][j]=ps[i-1][j]+ps[i][j];
			 }
		 }
	 }
    public static void main(String[] args) {
        // Test case 1: Example from the problem description
        int[][] matrix1 = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };
        RangeSum2D numMatrix1 = new RangeSum2D(matrix1);
        System.out.println("Test 1: " + numMatrix1.sumRegion(2, 1, 4, 3)); // Expected: 8
        System.out.println("Test 2: " + numMatrix1.sumRegion(1, 1, 2, 2)); // Expected: 11
        System.out.println("Test 3: " + numMatrix1.sumRegion(1, 2, 2, 4)); // Expected: 12

        // Test case 4: Small matrix
        int[][] matrix2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        RangeSum2D numMatrix2 = new RangeSum2D(matrix2);
        System.out.println("Test 4: " + numMatrix2.sumRegion(0, 0, 2, 2)); // Expected: 45 (sum of entire matrix)

        // Test case 5: Matrix with negative numbers
        int[][] matrix3 = {
            {-1, -2, -3},
            {-4, -5, -6},
            {-7, -8, -9}
        };
        RangeSum2D numMatrix3 = new RangeSum2D(matrix3);
        System.out.println("Test 5: " + numMatrix3.sumRegion(0, 0, 1, 1)); // Expected: -12

        // Test case 6: 1x1 matrix
        int[][] matrix4 = {{5}};
        RangeSum2D numMatrix4 = new RangeSum2D(matrix4);
        System.out.println("Test 6: " + numMatrix4.sumRegion(0, 0, 0, 0)); // Expected: 5

        // Test case 7: Large matrix (200x200)
        int[][] matrix5 = new int[200][200];
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                matrix5[i][j] = i + j;
            }
        }
        RangeSum2D numMatrix5 = new RangeSum2D(matrix5);
        System.out.println("Test 7: " + numMatrix5.sumRegion(50, 50, 100, 100)); // Expected: Sum of the region

        // Test case 8: Matrix with all zeros
        int[][] matrix6 = new int[5][5];
        RangeSum2D numMatrix6 = new RangeSum2D(matrix6);
        System.out.println("Test 8: " + numMatrix6.sumRegion(1, 1, 3, 3)); // Expected: 0

        // Test case 9: Matrix with all same positive number
        int[][] matrix7 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix7[i][j] = 1;
            }
        }
        RangeSum2D numMatrix7 = new RangeSum2D(matrix7);
        System.out.println("Test 9: " + numMatrix7.sumRegion(0, 0, 3, 3)); // Expected: 16

        // Test case 10: Rectangle with width 1
        System.out.println("Test 10: " + numMatrix1.sumRegion(0, 2, 4, 2)); // Expected: Sum of the third column

     // Test case 11: Rectangle with height 1
        System.out.println("Test 11: " + numMatrix1.sumRegion(2, 0, 2, 4)); // Expected: Sum of the third row

        // Test case 12: Upper left corner
        System.out.println("Test 12: " + numMatrix1.sumRegion(0, 0, 1, 1)); // Expected: Sum of upper left 2x2 submatrix

        // Test case 13: Lower right corner
        System.out.println("Test 13: " + numMatrix1.sumRegion(3, 3, 4, 4)); // Expected: Sum of lower right 2x2 submatrix

        // Test case 14: Matrix with alternating positive and negative numbers
        int[][] matrix8 = {
            { 1, -2,  3, -4},
            {-5,  6, -7,  8},
            { 9, -10, 11, -12},
            {-13, 14, -15, 16}
        };
        RangeSum2D numMatrix8 = new RangeSum2D(matrix8);
        System.out.println("Test 14: " + numMatrix8.sumRegion(1, 1, 2, 2)); // Expected: 0

        // Test case 15: Multiple queries on the same matrix
        System.out.println("Test 15a: " + numMatrix1.sumRegion(0, 0, 4, 4)); // Expected: Sum of entire matrix
        System.out.println("Test 15b: " + numMatrix1.sumRegion(1, 1, 3, 3)); // Expected: Sum of inner 3x3 submatrix
        System.out.println("Test 15c: " + numMatrix1.sumRegion(2, 2, 2, 2)); // Expected: Single element at (2,2)
    }
	public int sumRegion(int i, int j, int k, int l) {
		int sum=0;
		sum+=ps[k][l];
		if(i-1>=0) {
			sum-=ps[i-1][l];
		}
		if(j-1>=0) {
			sum-=ps[k][j-1];
		}
		if(i-1>=0&&j-1>=0) {
			sum+=ps[i-1][j-1];
		}
		return sum;
	}
}