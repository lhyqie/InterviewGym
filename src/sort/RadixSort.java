package sort;

import java.util.Arrays;

public class RadixSort {
	
	public static void main(String[] args) {
		
		int [] nums = {170, 45, 75, 90, 802, 24, 2, 66};
		System.out.println(Arrays.toString(nums));
		
		radixSort(nums);
	
	}
	
	private static int getMax(int[] nums){
		int mx = Integer.MIN_VALUE;
	    for(int num : nums) mx = Math.max(mx, num);
	    return mx;
	}
	
	public static void radixSort(int[] nums){
		int mx = getMax(nums);
		for(int exp = 1; mx / exp > 0; exp *= 10){ // try 1, 10, 100, ... 
			System.out.println("sorting on " + exp + "s place");
			countingSort(nums, exp);
			System.out.println(Arrays.toString(nums));
		}	
	}
	
	// count on the digit (nums[i] / exp %10)
	// *** radix sort calls counting sort as a subroutine ** 
	// *** but requires stable counting sort **
	public static void countingSort(int []nums, int exp){
		int[] buffer = new int[nums.length];
		int[] count = new int[10];
		//count
		for(int num : nums){
			count[num/exp % 10] ++;
		}
		//accumulate
		for(int i = 1; i < count.length; i++){
			count[i] += count[i-1];
		}
		//rearrange
		for(int j = nums.length - 1; j >=0 ; j--){   // from back to front to maintain the stability of sorting
			buffer[--count[nums[j]/exp % 10]] = nums[j];
		}
		//copy back
		for(int j = 0; j < nums.length; j++){
			nums[j] = buffer[j];
		}
	}
}
