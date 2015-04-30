package utils;

/**
 * 
 * @author howie
 * the Matrix here means 2D array
 */
public class MatrixUtils {
	
	/**
	 * create matrix with default value
	 * @param m
	 * @param n
	 * @param value
	 * @return
	 */
	public static int[][] createMatrix(int m, int n, int value){
		int [][] M = new int[m][n];
        for (int i = 0; i < m; i++) {
        	M[i] = new int[n];
        	for (int j = 0; j < n; j++) {
				M[i][j] = value;
			}
		}
        return M;
	}
	
	public static int[][] createMatrix(int m, int n){
		int [][] M = new int[m][n];
        for (int i = 0; i < m; i++) {
        	M[i] = new int[n];
        	for (int j = 0; j < n; j++) {
				M[i][j] = 0;
			}
		}
        return M;
	}
	
	public static void print(int[][] M){
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				System.out.print(String.format("%5d", M[i][j]));
			}
			System.out.println();
		}
	}
}
