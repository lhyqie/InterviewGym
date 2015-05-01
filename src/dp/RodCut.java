package dp;

import cern.colt.Arrays;

/**
 * page 360 of Introduction to Algorithms 3rd edition
 * 
 * @author howie
 *
 */
public class RodCut {
	public static void main(String[] args) {
		
		// price for rod at length i is  prices[i-1]  
		int prices[] = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
		
		for (int len = 0; len <= prices.length; len++) {
			System.out.println(" len = " + len + " \t opt_cut_recur = " + opt_cut_recur(prices, len));
			System.out.println(" \t opt_cut_dp = " + opt_cut_dp(prices, len));
					
			System.out.println();
		}
		
	}
	
	public static int opt_cut_recur(int[] prices, int len){
		if(len == 0){
			return 0;
		}else{
			int q = Integer.MIN_VALUE;
			for (int i = 1; i <= len; i++) {
				q = Math.max(q, prices[i-1] + opt_cut_recur(prices, len-i));
			}
			return q;
		}
	}
	
	public static int opt_cut_dp(int[] prices, int len){
		int[] r = new int[len+1]; // optimal price r[i] for length i
		int[] s = new int[len+1];
		r[0] = 0;
		for (int j = 1; j <= len; j++) { // solve r[1...len] bottom up
			int q = Integer.MIN_VALUE;
			for (int i = 1; i <= j; i++) {  // length i
				if(q < prices[i-1] + r[j-i]){
					q = prices[i-1] + r[j-i];
					s[j] = i;
				}
			}
			r[j] = q;
		}
		print_opt_cut(s, len);
		return r[len];
	}
	
	public static void print_opt_cut(int [] s, int len){
		while(len > 0){
			System.out.print(s[len]+ " ");
			len = len - s[len];
		}
	}
}
