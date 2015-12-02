package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class QuickSort {
	public static void main(String[] args) {
		int arr[] = //ArrayUtils.randomIntArray(10, 0, 9, 100213L);
					ArrayUtils.randomIntArray(10, 0, 9);
			    	//{3, 0, 3, 2, 8, 4, 1, 0, 7, 5};
		System.out.println(Arrays.toString(arr));
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int arr[]){
		qSort(arr, 0, arr.length - 1);
	}
	
	private static void qSort(int arr[], int left, int right){
		if(left >= right) return;
		int part = partition(arr, left, right, arr[right]);
		swap(arr, part, right);  //make arr[part] == pivot
		qSort(arr, left, part-1);
		qSort(arr, part+1, right);
	}
	
	public static int partition(int arr[], int left, int right, int pivot){
		while(left <= right){ //looping invaraint : arr[0...left] < pivot  arr[right...n-1]>= pivot
			while(left <= right && arr[left] < pivot) left ++;
			while(left <= right && arr[right] >= pivot) right --;
			if(left < right){
				swap(arr, left, right);
			}
			//System.out.println("left = " + left +" right = " + right);
			//System.out.println(Arrays.toString(arr));
		}
		return right + 1;
	}
	
	private static void swap(int arr[], int i, int j){
		int t = arr[i];  arr[i] = arr[j]; arr[j] = t;
	}
}
