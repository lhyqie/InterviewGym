package matrix;

import utils.MatrixUtils;

public class RotateMatrix {
	public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        while(top < bottom && left < right){
            // four way swap
            for(int offset = 0; offset < right - left; offset++){
                int t = matrix[top][left + offset];
                matrix[top][left + offset] = matrix[bottom - offset][left];
                matrix[bottom - offset][left] = matrix[bottom][right - offset];
                matrix[bottom][right - offset] = matrix[top + offset][right];
                matrix[top + offset][right] = t;
            }
            MatrixUtils.print(matrix);
            System.out.println();
            top ++; bottom --;
            left ++; right -- ;
        }
    }
	
	public static void main(String[] args) {
		
		int[][] matrix = {{1,2},{3,4}};
		MatrixUtils.print(matrix);
		System.out.println();
		
		rotate(matrix);
		
		MatrixUtils.print(matrix);
	}
}
