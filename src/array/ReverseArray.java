package array;

import utils.ArrayUtils;
import static utils.ArrayUtils.print;   //static import for short hand

/**
 * Given an array of integers, reverse it *in place*
 * O(n) time complexity
 */

public class ReverseArray {
	public static void main(String[] args) {
		//using seed ensures the array generated from different runs are the same
		//change seed to other value to get a different array
		int[] arr1 = ArrayUtils.randomIntArray(20, 0, 20, 10000L);  
		System.out.print("Array arr1 before reversal :\t");
		print(arr1);
		reverseArray(arr1);
		System.out.print("Array arr1  after reversal :\t");
		print(arr1);
		System.out.println();
		
		int[] arr2 = ArrayUtils.randomIntArray(10, 0, 20, 10000L);
		int low = 3;
		int high = 5;
		System.out.print(String.format("Array arr2 before reversal within position [%d,%d]:\t", low, high));
		print(arr2);
		reverseArray(arr1, low, high);
		System.out.print(String.format("Array arr2  after reversal within position [%d,%d]:\t", low, high));
		print(arr2);
	}
	
	/**
	 * Reverse the entire array
	 * @param arr: the array to be reversed
	 * 
	 * Solution:
	 * let n be the length of arr
	 * arr[i] <-> arr[j], for any i < j and  i + j = n - 1 
	 */
	public static void reverseArray(int[] arr){
		reverseArray(arr, 0, arr.length - 1);
	}
	
	/**
	 * Reverse a portion of array arr[low:high] (both inclusive)
	 * @param arr : the array to be reversed
	 * @param low : starting position 
	 * @param high : ending position 
	 */
	public static void reverseArray(int []arr, int low, int high){
		
	}
	
	private static void swap(int []arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
