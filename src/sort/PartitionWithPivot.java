package sort;

import java.util.Arrays;
import utils.ArrayUtils;

public class PartitionWithPivot {
	public static void main(String[] args) {
		int arr[] = //ArrayUtils.randomIntArray(10, 0, 9, 100213L);
					//ArrayUtils.randomIntArray(10, 0, 9);
				    {3, 0, 3, 2, 8, 4, 1, 0, 7, 5};
		System.out.println(Arrays.toString(arr));
		int index = partition(arr, 5);
		System.out.println(Arrays.toString(arr));
		System.out.println("right part starts from index : " + index);
	}
	
	// parition the array to two parts, the left part < pivot and the right part >= pivot
	// return the index of the first element of the right part
	public static int partition(int arr[], int pivot){
		int left = 0, right = arr.length - 1;
		while(left <= right){ //looping invaraint : arr[0...left] < pivot  arr[right...n-1]>= pivot
			while(left <= right && arr[left] < pivot) left ++;
			while(left <= right && arr[right] >= pivot) right --;
			if(left < right){
				swap(arr, left, right);
			}
		}
		return right + 1;
	}
	
	private static void swap(int arr[], int i, int j){
		int t = arr[i];  arr[i] = arr[j]; arr[j] = t;
	}
	
}
