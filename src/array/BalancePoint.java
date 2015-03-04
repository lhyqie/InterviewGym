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
		
		return -1;
	}
}
