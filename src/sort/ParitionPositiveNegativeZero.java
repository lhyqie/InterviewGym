package sort;

import java.util.*;
import utils.ArrayUtils;

// array contains 0 , 1, -1
// sort it such that  all -1's before 0  and all 1's after 0 
public class ParitionPositiveNegativeZero {
	
	public static void main(String[] args) {
		//int nums [] = ArrayUtils.randomIntArray(10, -1, 1, 1234567L);
		int nums [] = ArrayUtils.randomIntArray(10, -1, 1);
		sort(nums);
	}
	
	// idea 1: two times  quick parition
	// idea 2: alternative , dutch flags sort three colors
	public static void sort(int[] nums){
		System.out.println(Arrays.toString(nums));
		int pivotIndex = partition(nums, 0, nums.length - 1, 0);
		System.out.println("Array now = "+ Arrays.toString(nums));
		System.out.println("pivot at = "+ pivotIndex);
		
		pivotIndex = partition(nums, 0, nums.length - 1, 1);
		System.out.println("Array now = "+ Arrays.toString(nums));
		System.out.println("pivot at = "+ pivotIndex);
	}
	//
	public static int partition(int[] nums, int left, int right, int pivotValue){
		while(left <= right){
			while(left <= right && nums[left] < pivotValue) left ++;
			while(left <= right && nums[right] >= pivotValue) right --;
			if(left < right) {
				swap(nums, left, right);
			}
		}
		return right + 1;
	}
	
	static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
