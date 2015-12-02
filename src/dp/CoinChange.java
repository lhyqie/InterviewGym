package dp;

//http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
public class CoinChange {
	public static void main(String[] args) {
		int n = 5;
		int coins[] = {1,2,3};
		System.out.println(count(coins, coins.length, n)); //geeksforgeeks solution
		System.out.println(count2(coins, n));
	}
	
	static int count2(int[] coins, int n){
		int dp[] = new int[n+1];
		dp[0] = 1;
		for(int i = 0; i <coins.length; i++){
			for(int j = coins[i]; j <= n; j ++){
				dp[j] += dp[j-coins[i]]; 
			}
		}
		return dp[n];
	}
	
	static  int count( int S[], int m, int n )
	{
	    // table[i] will be storing the number of solutions for
	    // value i. We need n+1 rows as the table is consturcted
	    // in bottom up manner using the base case (n = 0)
	    int [] table = new int[n+1];
	    // Base case (If given value is 0)
	    table[0] = 1;
	    // Pick all coins one by one and update the table[] values
	    // after the index greater than or equal to the value of the
	    // picked coin
	    for(int i=0; i<m; i++)
	        for(int j=S[i]; j<=n; j++)
	            table[j] += table[j-S[i]];
	 
	    return table[n];
	}
}
