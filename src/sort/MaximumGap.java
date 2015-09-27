package sort;

import java.util.*;

// wrong solution
public class MaximumGap {
	
	public static void main(String[] args) {
		int[] nums = new int[10];
		for(int i = 0; i < nums.length; i++){
			nums[i] = i+1;
		}
		
		int ret = maximumGap(nums);
		System.out.println(ret);
	}
	
	public static int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int n = nums.length;
        
        List<Bucket> buckets = new ArrayList<Bucket>(n+1);
        for(int i = 0; i <= n;i++){
        	buckets.add(new Bucket());
        }
        // 1. define the range of bucket
 		int min = Integer.MAX_VALUE;
 		int max = Integer.MIN_VALUE;
 		for(int i = 0; i < n; i++){
 			min = Math.min(min, nums[i]);
 			max = Math.max(max, nums[i]);
 		}
        
 		double range = max - min;
		double L  = range / n ; 
		// for i in [0, n) range of bucket[i] : [min + i * L, min + (i+1) * L) 
		//                          bucket[0]  : [min,  min + L)
		//                          bucket[n-1] : [min + (n-1)* L,  min + n *L )
		//                          bucket[n] :   [min+ n*L, ...)                //cover the max
		// index of bucket that a number x belongs to   floor((x - min)/ L)
		
		
		// 2. put numbers to bucket, but only saves low and high of each bucket
		for(int i = 0; i < n; i++){
			int x = nums[i];
			int bid = (int)((x - min)  * 1.0 / L);
			if(buckets.get(bid).low == -1){
				buckets.get(bid).low = x;
				buckets.get(bid).high = x;
			}else{
				buckets.get(bid).low = Math.min(buckets.get(bid).low, x);
				buckets.get(bid).high = Math.max(buckets.get(bid).high, x);
			}
		}
		
		// 3 the maximum gap only exist between bucket[i].high and bucket[i+1].low
		int prev = buckets.get(0).high;
		int maxGap = 0;
		for(int i = 0; i <=n ;i++){
			if(buckets.get(i).low != -1){
				maxGap = Math.max(maxGap, buckets.get(i).low - prev);
				prev = buckets.get(i).high;
			}
		}
        return maxGap;
    }
	
	
	static class Bucket{
		public Bucket(){}
		int low = -1;
		int high = -1;
		public String toString(){
			return "["+low+","+high+"]";
		}
	}
}
