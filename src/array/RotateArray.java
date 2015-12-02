package array;

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
		
	}
	
	
	/**
	 * Use a buffer array of size K
	 * @param arr: the array to rotate
	 * @param K: the number of positions to the left
	 * Time complexity O(n)
	 * Space complexity O(K)
	 */
	public static void rotate1(int[] arr, int K){
		if(K < 0)  throw new RuntimeException("K should be >= 0");  // K should not be negative
		int n = arr.length;
		K %= n;   // if K > n, modulo K by n such that K is an integer within [0,n)
		if(K == 0) return; // no need to rotate if K == 0
		
		int b[] = new int[K];
		//step 1   b[0...K-1] <-- a[0...K-1]   (both of which are of size K)
		for(int i = 0; i < K; i++){
			b[i] = arr[i];
		}
		//step 2   a[0...n-K-1] <-- a[K...n-1] (both of which are of size n-K)
		for (int i = 0; i < n-K; i++) {
			arr[i] = arr[i+K];
		}
		//step 3   a[n-K...n-1] <-- b[0...K]   (both of which are of size K)
		for (int i = 0; i < K; i++) {
			arr[i+n-K] = b[i];
		}
	}
	
	/**
	 * Achieve rotation by reversing the array for three times
	 * with the help of the function array.ReverseArray.reverseArray(...)
	 * @param arr: the array to rotate
	 * @param K: the number of positions to the left
	 * Time complexity O(n)
	 * Space complexity O(1)
	 * 
	 * Idea: For example a = {1,2,3,4,5}, shift K=2 positions to left 
	 *     first reverse the entire array a
	 *     		a' = {5, 4, 3, 2 ,1}
	 *     divide a' into two parts whose size are (n-K) and K respectively
	 *     		a_{1}' = {5, 4, 3}  a_{2}' = {2, 1}
	 *     reverse a_{1}' and a_{2}' 
	 *     		a_{1}'' = {3, 4, 5}  a_{2}'' = {1,2}
	 *     a = a_{1}'' + a_{2}'' = {3, 4, 5, 1, 2}
	 *       
	 */
	public static void rotate2(int[] arr, int K){
		if(K < 0)  throw new RuntimeException("K should be >= 0");  // K should not be negative
		int n = arr.length;
		K %= n;   // if K > n, modulo K by n such that K is an integer within [0,n)
		if(K == 0) return; // no need to rotate if K == 0	
		ReverseArray.reverseArray(arr);
		ReverseArray.reverseArray(arr,0,n-K-1);
		ReverseArray.reverseArray(arr,n-K,n-1);
	}
}
