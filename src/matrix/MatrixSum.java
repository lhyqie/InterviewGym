package matrix;

import java.util.Arrays;

/*
 * 
 * Medium Submatrix Sum Show result 

   Given an integer matrix, find a submatrix where the sum of numbers is zero. 
   Your code should return the coordinate of the left-up and right-down number.

 */
public class MatrixSum {
	
	public static void main(String[] args) {
		int[][] matrix = { {1, 5, 7},
		                   {3, 7, -8},
		                   {4, -8, 9}};
		int [][] ret = submatrixSum(matrix);
		System.out.println(Arrays.deepToString(ret));
	}
	
	public static int[][] submatrixSum(int[][] matrix) {
        
        int m = matrix.length, n = matrix[0].length;
        
        int[][] A = new int[m+1][n+1];  //rename
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                A[i][j] = -A[i-1][j-1] + A[i-1][j] + A[i][j-1] + matrix[i-1][j-1];
            }
        }
        
        //System.out.println(Arrays.deepToString(A));
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                for(int i2 = i; i2 <= m; i2++){
                    for(int j2 = j; j2 <= n; j2++){
                    	int t = A[i2][j2] - A[i2][j-1] - A[i-1][j2] + A[i-1][j-1];
                        //System.out.println(String.format("%s,%s -> %s,%s = %d \t", i, j, i2, j2, t));
                    	if( t == 0){
                            return new int[][]{{i-1,j-1},{i2-1,j2-1}};
                        }
                    }
                }
            }
        }
    
        return new int[][]{{}};
    }
}
