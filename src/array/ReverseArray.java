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
		int[] arr = ArrayUtils.randomIntArray(20, 0, 20, 10000L);  
		System.out.println("Array before reversal:");
		print(arr);
		reverseArray(arr);
		System.out.println("Array after reversal:");
		print(arr);
	}
	
	public static void reverseArray(int[] arr){
		int n = arr.length;
		for(int i = 0; i < n; i ++){
			int j = n - 1 - i;
			if(i >= j) break;
			swap(arr, i, j);
		}
	}
	
	private static void swap(int []arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
