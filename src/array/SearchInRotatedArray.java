package array;

public class SearchInRotatedArray {

	public static void main(String[] args) {
		int A[] = null;
		int target;
		int targetIndex;
		
		A = new int[]{ 3, 1 };
		target = 1;
		targetIndex = search(A, target);
		System.out.println("solution 1: target at index : " + targetIndex);
		targetIndex = search2(A, target);
		System.out.println("solution 2: target at index : " + targetIndex);
		System.out.println();
		
		
		A = new int[]{1,2,3,4,5,6,7,8,9};
		target = 9;
		targetIndex = search(A, target);
		System.out.println("solution 1: target at index : " + targetIndex);
		targetIndex = search2(A, target);
		System.out.println("solution 2: target at index : " + targetIndex);
		System.out.println();
		
		A = new int[]{9,5,6,7,8,9,9,9,9,9,9};
		target = 8;
		targetIndex = search(A, target);
		System.out.println("solution 1: target at index : " + targetIndex);
		targetIndex = search2(A, target);
		System.out.println("solution 2: target at index : " + targetIndex);
		System.out.println();
	}
	
	// Solution 1: my two step solution
	public static int findMinimum(int[] A) { // assume no dups
		int left = 0, right = A.length - 1;
		while (left < right) {
			int mid = (left + right) >>> 1;
			if (A[mid] > A[right]) { // minimum must be in range [mid + 1,right]
				left = mid + 1;
			} else{ // A[mid] < A[right]
				right = mid; // minimum still possible in mid
			}
		}
		return left;
	}

	public static int search(int[] A, int target) {
		if (A == null || A.length == 0)
			return -1;
		int k = findMinimum(A);
		//System.out.println("minimum at index : " + k);
		int n = A.length;
		// A[0...n-1] is rotated array
		// A[k...n-1+k] is the sorted array
		int left = k, right = n - 1 + k;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (A[mid % n] == target)
				return mid % n;
			else if (A[mid % n] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
	
	// Solution 2: zhiyuan's solution
	public static int search2(int[] a, int target) {
			int left = 0;
			int right = a.length - 1;
			while (left <= right) {
				int mid = (left + right) >>> 1;
				if (a[mid] == target)
					return mid;
				if (a[mid] >= a[left]) {
					if (a[mid] >= target && target >= a[left]) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				} else {
					if (a[right] >= target && target >= a[mid]) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
			}
			return -1;
	}
	
	
	
	/*
	 * old complex solution public int search(int[] A, int target) { //
	 * IMPORTANT: Please reset any member data you declared, as // the same
	 * Solution instance will be reused for each test case. if(A == null ||
	 * A.length == 0) return -1; int low = 0, high = A.length - 1; while(low <=
	 * high){ int mid = (low + high)/2; if(A[mid] == target) return mid;
	 * if(A[low] <= A[mid]){ // no reset point on left side or array is not
	 * rotated(reset point at 0) if(A[mid] < target) { // A[low] <= A[mid] <
	 * target low = mid + 1; }else if (A[low] <= target && target < A[mid]) { //
	 * A[low] <= target < A[mid] high = mid - 1; }else{ //target <= A[low] <
	 * A[mid] low = mid + 1; } }else{ // no reset point on right side, A[mid] <=
	 * A[high] if(target < A[mid]){ //target < A[mid] <= A[high] high = mid -1;
	 * }else if(A[mid] < target && target <= A[high]){ // A[mid] < A[high] <=
	 * target low = mid + 1;
	 * 
	 * }else{ // A[mid] <= A[high] < target high = mid - 1; }
	 * 
	 * } } return -1; }
	 */
}