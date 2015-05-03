package dp;

import utils.MatrixUtils;

/**
 * Introduction to algorithms second edition, page 324
 * @author howie
 *
 */
public class AssemblyLine {
	public static void main(String[] args) {
		int e[] = {2, 4}; //entry time
		int x[] = {3, 2}; //exit time
		int a[][] = {{7, 9, 3, 4, 8, 4},   // assembly time
				     {8, 5, 6, 4, 5, 7}};
		int t[][] = {{2, 3, 1, 3, 4},      // transition time
					 {2, 1, 2, 2, 1}};
		System.out.println(minimumTime(e, x, a, t));
	}
	
	
	public static int minimumTime(int []e, int []x, int[][] a, int[][] t){
		int n = a[0].length;
		int [][] L = new int[2][n];
		int [][] f = new int[2][n];
		f[0][0] = e[0] + a[0][0];
		f[1][0] = e[1] + a[1][0];
		for (int i = 1; i < n; i++) {
			// line 1
			if(f[0][i-1] < f[1][i-1]+t[1][i-1]){
				f[0][i] = f[0][i-1] + a[0][i];  
				L[0][i] = 0;
			}else{
				f[0][i] = f[1][i-1]+t[1][i-1] + a[0][i];
				L[0][i] = 1;
			}
			// line 2
			if(f[1][i-1] < f[0][i-1]+t[0][i-1]){
				f[1][i] = f[1][i-1] + a[1][i];
				L[1][i] = 1;
			}else{
				f[1][i] = f[0][i-1]+t[0][i-1] + a[1][i];
				L[1][i] = 0;
			}

		}
		MatrixUtils.print(f);
		
		int ret = -1;
		int last_i = 0; // the line just before exiting
		if(f[0][n-1] + x[0] < f[1][n-1] + x[1]){
			ret = f[0][n-1] + x[0];
			last_i = 0;
		}else{
			ret = f[1][n-1] + x[1];
			last_i = 1;
		}
		printPath(L, last_i, n);
		return ret;
	}
	
	public static void printPath(int[][] L, int last_i, int n){	
		System.out.println("line "+ last_i + ", station" + n + " <-");
		for (int j = n- 1; j >= 1; j--) {
			last_i = L[last_i][j];
			System.out.println("line "+ last_i + ", station" + (j) + " <- ");
		}
	}
}
