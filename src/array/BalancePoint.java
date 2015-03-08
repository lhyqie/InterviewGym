package array;

/*
 * Given an integer array arr, find a balance point t (index of element (0-based indexing) ) such that sum of elements to its left equals
 * sum of elements to its right: sum(a[0...k-1]) = sum(a[k+1...])
 * for example arr = {1,2,3,2,1}, the balance point is 2 as 1+2 == 2+1
 * note that there can be more balance points, return the one with smallest index
 * note also there may not exist any balance points, if so return -1
 */
public class BalancePoint {
	
	public static void main(String[] args)
	{
		int[] arr = {1,2,3,7,6,5,9,5,6,7,5,2,-1};  //expected 6th position
		System.out.println("(Naive) Balance point for arr is index "+BalanceNaive(arr));
		System.out.println("(Improve) Balance point for arr is index "+BalanceImprove(arr));
		System.out.println("(Best) Balance point for arr is index "+BalanceBest(arr));
	}
	
	/**
	 * Naive approach
	 * Try each point as a balance point, check the left sum and right sum.
	 * Time Complexity O(n^2)
	 * Space Complexity O(1)
	 * @param arr: the array to work on
	 * @return
	 */
	public static int BalanceNaive(int[] arr) {
		/*
		int n=arr.length;
		int i,suml,sumr;
		for(i=1; i<n; i++){
			suml=0;
			for(int j=0; j<i; j++){
				suml+=arr[j];
			}
			sumr=0;
			for(int j=i+1; j<n; j++){
				sumr+=arr[j];
			}
			if(suml==sumr)	break;
		}
		if(i==n)	return -1;
		else	return i;
		*/
		
		int n = arr.length;
		for (int checkpoint = 0; checkpoint < n; checkpoint++) {   // nested for-loops of depth 2, so Time Complexity is O(n^2) 
			int left_sum = 0;									   // only several temporal variables, Space Complexity is O(1)
			int right_sum = 0;
			for (int i = 0; i <= checkpoint; i++) {
				left_sum += arr[i];
			}
			for (int i = n - 1; i >= checkpoint; i--) {
				right_sum += arr[i];
			}
			if(left_sum == right_sum) return checkpoint;
		}
		return -1;
	}
	
	/**
	 * Improve approach
	 * Use some buffer arrays to reduce overlapped summation:
	 * When we sum arr[0...j] we could rely on the sum of arr[0...j-1] which is pre-computed.
	 * Likewise, arr[k...] can be inferred from arr[k+1...] without too much work.
	 * Time Complexity O(n)
	 * Space Complexity O(n)
	 * @param arr: the array to work on
	 * @return
	 */
	public static int BalanceImprove(int[] arr) {
		int n = arr.length;
		int[] left_sums = new int[n];   // left_sums[i] is the sum of all elements arr[0...i]
		int[] right_sums = new int[n];  // right_sums[i] is the sum of all elements arr[i...n-1]
		// three for-loops but not nested, Time Complexity is O(3n) which is the same as O(n)
		// two buffered arrays are used Space Complexity is O(2n) which is thus O(n)
		for (int i = 0; i < n; i++) {
			//left_sums[i] = ???;
		}
		for (int i = n - 1; i>= 0; i--) {
			//right_sum[i] = ???;
		}
		for (int checkpoint = 0; checkpoint < n ; checkpoint++) {
			//if(???){
			//	return checkpoint;
			//}
		}
		return -1;
	}
		
	/**
	 * Best approach
	 * Do we really need to store an array keep tracking of left sums or right sums up to a particular position?
	 * We can use single variables to reduce the redundant computation for each balance point checking.
	 * Time Complexity O(n)
	 * Space Complexity O(1)
	 * @param arr: the array to work on
	 * @return
	 */
	public static int BalanceBest(int[] arr) {
		int n = arr.length;
		int left_sum = 0;   // left_sum up to a checkpoint from left 
		int right_sum = 0;  // right_sum up to a checkpoint from right
		
		// two for-loops but not nested, Time Complexity is O(2n) which is the same as O(n)
		// only several temporal variables, Space Complexity is O(1)
		
		for (int i = n-1; i >= 0; i--) {
			right_sum += arr[i];
		}
		// now  left_sum is 0 sum(a[0..-1]) which is a empty set
		//      right_sum is sum(a[0...n-1]) which is the total sum of all array
		for (int checkpoint = 0; checkpoint < n; checkpoint++) {
			// add each a[checkpoint] left_sum 
			// substract each a[checkpoint] from right_sum
			// check if they are equal
			
		}
		return -1;
	}
}
