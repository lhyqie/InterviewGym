package dp;

import java.util.*;

//you have an array of integers, you are going to put some of them into one bucket 
//and the rest of them into another bucket. 
//where the different of sum of one bucket and the other is smallest

public class ClosestParition {
	public static void main(String[] args) {
		int nums [] = //{2,5,1,11,3,5,10,24,555,123,985,1337,9,13,24,89,19,27};
					  {1,5,1,11,3,5,10,24,555,123,985,1337,9,13,24,89,19,27};
		findClosest(nums);
	}
	
	public static void findClosest(int nums[]){
		int n = nums.length;
        int sum=0;
        for(int i = 0; i < n; i++) sum += nums[i];
        int temp = sum / 2;
        int [][] dp = new int[n+1][temp+1];
        int [][] par = new int[n+1][temp+1];
        for(int i = 0;i <= n; i++)
        {
            for(int j = 0; j<= temp; j++)
            {
                if(i==0 || j==0)
                	dp[i][j] = 0;
                else if(j >= nums[i-1])
                	if(dp[i-1][j] > dp[i-1][j-nums[i-1]] + nums[i-1]){
                		dp[i][j] = dp[i-1][j];
                		par[i][j] = 0;  //don't choose item i-1
                	}else{
                		dp[i][j] =  dp[i-1][j-nums[i-1]] + nums[i-1];
                		par[i][j] = 1;  // choose item i-1
                	}
                else
                {
                	dp[i][j] = dp[i-1][j];  // can not choose item i-1
                }
            }
        }
        System.out.println("sum of partition 1 : " +  dp[n][temp]);
        System.out.println("sum of partition 2 : " +  (sum - dp[n][temp]));
        
        List<Integer> group1 = new ArrayList<Integer>();
        List<Integer> group2 = new ArrayList<Integer>();
        
        int j =  temp;
        for(int i = n; i >=1; i--){
        	if(par[i][j] == 1){
        		j -= nums[i-1];
        		group1.add(nums[i-1]);
        	}else{
        		group2.add(nums[i-1]);
        	}
        }
        
        System.out.println("group1 :" + group1);
        System.out.println("group2 :" + group2);
        
        System.out.println(Arrays.deepToString(dp));
        
	}
}
