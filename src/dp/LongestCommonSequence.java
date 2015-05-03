package dp;

import utils.MatrixUtils;

public class LongestCommonSequence {
	
	public static void main(String[] args) {
		String s1 = "ABCBDAB";
		String s2 = "BDCABA";
		System.out.println(lcs(s1,s2));
	}
	
	public static int lcs(String s1, String s2){
		char[] chs1 = s1.toCharArray();
		char[] chs2 = s2.toCharArray();
		
		int n1 = chs1.length;
		int n2 = chs2.length;
		
		int[][] c = new int[n1+1][n2+1];
		char[][] s = new char[n1+1][n2+1];
		
		for (int i = 0; i < n1+1; i++) {
			c[i][0] = 0;
			s[i][0] = '¡ü';
		}
		for (int j = 0; j < n2+1; j++) {
			c[0][j] = 0;
			s[0][j] = '¡û';
		}
		for (int i = 1; i < n1+1; i++) {
			for (int j = 1; j < n2+1; j++) {
				if(chs1[i-1] == chs2[j-1]){
					c[i][j] = c[i-1][j-1]+1;
					s[i][j] = '\\'; //special char for diagonal movement
				}else{
					if(c[i][j-1] > c[i-1][j]){
						c[i][j] = c[i][j-1];
						s[i][j] = '¡û';
					}else{
						c[i][j] = c[i-1][j];
						s[i][j] = '¡ü';
					}
				}
			}
		}
		MatrixUtils.print(c);
		System.out.println();
		MatrixUtils.print(s);
		printSolution(s, chs1, chs2, n1, n2);
		System.out.println();
		return c[n1][n2];
	}
	
	public static void printSolution(char [][]s, char[] chs1, char[] chs2, int r, int c){
		if(r == 0 && c == 0){
			return ;
		}else{
			System.out.println("r ="+r +" c="+c);
			char choice = s[r][c];
			if(choice == '*'){
				printSolution(s, chs1, chs2, r-1, c-1);
				System.out.print(chs1[r-1] + " ");
			}else if(choice == '¡û'){
				printSolution(s, chs1, chs2, r, c-1);
			}else{
				printSolution(s, chs1, chs2, r-1, c);
			}
		}
		
	}
}
