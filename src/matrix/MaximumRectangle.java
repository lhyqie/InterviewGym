package matrix;

import java.util.Arrays;


// unfinished
public class MaximumRectangle {
	
	public static void main(String[] args) {
		char[][] matrix = {{'1','1'}};
		int ret = maximalRectangle(matrix);
		System.out.println(ret);
	}
	
	public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int[][] dr = new int[m][n];
        int ans1 = 0, ans2 = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                dr[i][j] = dp[i][j] = matrix[i][j] - '0';
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if( i > 0 && j > 0 && dp[i][j] == 1){
                    int min = dp[i-1][j-1];
                    min = Math.min(min, dp[i-1][j]);
                    min = Math.min(min, dp[i][j-1]);
                    dp[i][j] = min + 1;
                }
                if( i > 0 && j > 0 && dr[i][j] == 1){
                    int max = dr[i-1][j-1];
                    max = Math.max(max, dr[i-1][j]);
                    max = Math.max(max, dr[i][j-1]);
                    dr[i][j] = max + 1;
                }
                
                ans1 = Math.max(ans1, dp[i][j]);
                ans2 = Math.max(ans2, dr[i][j]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println(Arrays.deepToString(dr));
        return ans1 * ans2;
    }
}
