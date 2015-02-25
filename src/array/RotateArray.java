package array;

import utils.ArrayUtils;
import static utils.ArrayUtils.print;   //static import for short hand

/**
 * Given an array of integers, rotate an array to its left for K position *in place*
 * For example:
 *  [1, 2, 3, 4, 5]  rotating K=2 position to it left will be [3, 4, 5, 1, 2]
 */

public class RotateArray {
	public static void main(String[] args) {
		int[] origin = {1,2,3,4,5,6,7,8,9,10};  
		System.out.print("Array origin:");
		print(origin);
		
		int K = 3;  // try K = 13, 23, 33 and think of a possible improvement
		System.out.println("K =" + K);
		System.out.println();
		
		int [] copy1 = origin.clone();
		System.out.print("Array copy1 before applying rotate1 :\t");
		print(copy1);
		rotate1(copy1, K);
		System.out.print("Array copy1 after applying rotate1 :\t");
		print(copy1);
		System.out.println();
		
		int [] copy2 = origin.clone();
		System.out.print("Array copy2 before applying rotate2 :\t");
		print(copy2);
		rotate2(copy2, K);
		System.out.print("Array copy2 after applying rotate2 :\t");
		print(copy2);
		System.out.println();
		
		int [] copy3 = origin.clone();
		System.out.print("Array copy3 before applying rotate3 :\t");
		print(copy3);
		rotate3(copy3, K);
		System.out.print("Array copy3 after applying rotate3 :\t");
		print(copy3);
	}
	
	
	/**
	 * Use a buffer array of size K
	 * @param arr: the array to rotate
	 * @param K: the number of positions to the left
	 * Time complexity O(n)
	 * Space complexity O(K)
	 */
	public static void rotate1(int[] arr, int K){
		
	}
	
	/**
	 * Swap elements directly without extra storage
	 * @param arr: the array to rotate
	 * @param K: the number of positions to the left
	 *  Time complexity O(n)
	 *  Space complexity O(1)
	 */
	public static void rotate2(int[] arr, int K){
		
	}
	
	/**
	 * Achieve rotation by reversing the array for three times
	 * @param arr: the array to rotate
	 * @param K: the number of positions to the left
	 * Time complexity O(n)
	 * Space complexity O(1)
	 */
	public static void rotate3(int[] arr, int K){
		
	}
}
