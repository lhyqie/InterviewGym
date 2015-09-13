package sort;


import cern.colt.Arrays;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the 
 * sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.

 */
public class KthLargestNumber {
	
	public static void main(String[] args) {
		int[] nums = {3,2,1,5,6,4};
		int k = 2; 
		System.out.println(Arrays.toString(nums));
		int index = findKthLargest(nums, k);
		System.out.println(Arrays.toString(nums));
		System.out.println("find at index : " + index);
	}
	
	public static int findKthLargest(int[] nums, int k) {
		return findKthLargest(nums, 0, nums.length - 1, k);
	}

	public static int findKthLargest(int[] nums, int left, int right, int k) {
		int part = partition(nums, left, right);
		swap(nums, part, right); // make sure nums[part] == pivot which is
									// arr[right]
		System.out.println(Arrays.toString(nums) + " part =" + part);
		if (part == nums.length - k)
			return nums[part];
		if (part < nums.length - k) {
			return findKthLargest(nums, left + 1, right, k);
		} else { // part > nums.length - k
			return findKthLargest(nums, left, right - 1, k);
		}
	}

	public static int partition(int[] nums, int left, int right) {
		int pivot = nums[right];
		while (left <= right) {
			while (left <= right && nums[left] < pivot)
				left++;
			while (left <= right && nums[right] >= pivot)
				right--;
			if (left < right) {
				swap(nums, left, right);
			}
		}
		return right + 1;
	}

	private static void swap(int arr[], int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
}
