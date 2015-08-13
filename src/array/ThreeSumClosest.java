package array;

import java.util.*;

public class ThreeSumClosest {
	public static void main(String[] args) {
		System.out.println(threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1));
	}
	
	public static int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return -9999; 
        int closestSum = nums[0] + nums[1] + nums[2];
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; i++){
            int low = i+1;
            int high = nums.length - 1;
            while(low < high){
                int sum =  nums[i] + nums[low] + nums[high];
                //System.out.println(Math.abs(sum-target) + " " +  Math.abs(closestSum-target));
                if( sum == target){
                    return target;
                }else if( sum < target){
                    closestSum = Math.abs(sum-target) < Math.abs(closestSum-target) ? sum : closestSum;
                    low ++ ;
                }else{ // sum > target
                    closestSum = Math.abs(sum-target) < Math.abs(closestSum-target) ? sum : closestSum;
                    high --;
                }
            }
        }
        
        return closestSum;
   }
}
