package array;

import java.util.Arrays;

public class BestTimeBuySellStockIII {
	
	public static void main(String[] args) {
		int [] prices = {2,1,2,0,1}; //{1,2}; //{3,3,5,0,0,3,1,4}; //{2,1,2,0,1}; //{1,2,4,2,5,7,2,4,9,0};
		int ret = maxProfit(prices);
		System.out.println(ret);
	}
	
	public static int maxProfit(int[] prices) {
		if(prices == null || prices.length == 0) return 0;
		int n = prices.length;
		int maxProfitL[] = new int[n];
		int maxProfitR[] = new int[n];
		
		int res = 0;
		int curMin = prices[0];
		//left to right
		for(int i = 1; i < n; i++){
			maxProfitL[i] = Math.max(maxProfitL[i-1], prices[i] - curMin);
			curMin = Math.min(curMin, prices[i]);
		}
		//System.out.println(Arrays.toString(maxProfitL));
		
		//right to left
		int curMax = prices[prices.length - 1];
		for(int i = prices.length - 1; i >=0; i--){
			res = Math.max(res, maxProfitL[i] + curMax - prices[i]);
			curMax = Math.max(curMax, prices[i]);
		}
				
		return res;
    }
}
