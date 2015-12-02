package sort;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
	For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 *
 */
public class WiggleSort {
	public static void main(String[] args) {
		int nums[] =  {1}; //{3, 5, 2, 1, 6, 4};
		wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
	 public static void wiggleSort(int[] nums) {
	        Arrays.sort(nums);
	        int left = 0, right = nums.length - 1;
	        int b[] = new int[nums.length];
	        int cnt = 0;
	        while(left < right){
	            b[cnt++] = nums[left++];
	            b[cnt++] = nums[right--];
	        }
	        if(left == right) b[cnt] = nums[left];
	        for(int i = 0; i < nums.length; i++){
	            nums[i] = b[i];
	        }
	  }
}
