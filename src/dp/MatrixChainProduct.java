package dp;

import utils.MatrixUtils;

/**
 * Introduction to algorithms second edition, page 331
 * @author howie
 *
 */
public class MatrixChainProduct {
	public static void main(String[] args) {
		int r[] = {30, 35, 15, 5, 10, 20};
		int c[] = {35, 15, 5, 10, 20, 25};
		System.out.println(minimumCost(r, c));
	}
	
	public static int minimumCost(int[] r, int [] c){
		int n = r.length;
		int m [][] = new int[n][n];
		int s [][] = new int[n][n]; 
		for (int i = 0; i < n; i++) {  // chain size == 1
			m[i][i] = 0;
		}
		for (int l = 2; l <= n; l++) { // chain size >= 2
			for (int i = 0; i < n - l + 1; i++) { // starting index chain
				int k = i + l - 1;                // end index of chain
				m[i][k] = Integer.MAX_VALUE;
				for (int j = i; j < k; j++) {
					//System.out.println(" i = " + i + " j = "+j + " k = " +k);
					int q = m[i][j] + m[j+1][k] + r[i] * c[j] * c[k];
					if (m[i][k] > q){
						m[i][k] = q;
						s[i][k] = j;
					}
				}
			}
		}
		
		printSolution(s, 0, n-1);
		System.out.println();
		return m[0][n-1];
	}

	private static void printSolution(int[][] s, int start, int end) {
		int j = s[start][end];
		if (start == end){
			System.out.print("A"+start);
		}else{
			System.out.print("(");
			printSolution(s, start, j);
			printSolution(s, j+1, end);
			System.out.print(")");
		}
		
	}
}
