package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class MergeSort{
	
	static int[] buffer = null;
	
	public static void main(String[] args)
	{
		int a[] = ArrayUtils.randomIntArray(20);
		System.out.println(Arrays.toString(a));
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	} 
	
	/**
	 * In place merge sort an array
	 * @param a : the array to sort
	 * Time Complexity O(nlogn)
	 * Space Complexity O(n)
	 */
	public static void mergeSort(int[] a){
		buffer = new int[a.length];
		mSort(a , 0, a.length-1);
	}
	
	/**
	 * In place merge sort an array a[low...upper]
	 * First merge sort a[low...mid]
	 * second merge sort a[mid+1 ... upper]
	 * then merge them
	 * @param a : the array
	 * @param low  :  merge sort [low...upper]
	 * @param upper :  merge sort [low...upper]
	 */
	public static void mSort(int []a, int low, int upper){
		if(low < upper){
			int mid = (low + upper)/2;
			mSort(a, low, mid);
			mSort(a, mid+1, upper);
			merge(a, low, mid, upper);
		}
	}

	/**
	 * merge a[low...mid] and a[mid+1....upper] into buffer[low....upper]
	 * then copy buffer[low....upper] back to a[low...upper]
	 * @param a   : the array
	 * @param low  
	 * @param mid
	 * @param upper
	 */
	public static void merge(int a[], int low, int mid, int upper){
		int p1 = low;
		int p2 = mid+1;
		int p3 = low;
		while(p1 <= mid && p2 <= upper){
			if(a[p1] < a[p2]){
				buffer[p3++] = a[p1++];
			}else{
				buffer[p3++] = a[p2++];
			}
		}
		while(p1 <= mid){
			buffer[p3++] = a[p1++];
		}
		while(p2 <= upper){
			buffer[p3++] = a[p2++];
		}
		// copy buffer to a[low..upper]
		for (int i = low; i <= upper; i++) {
			a[i] = buffer[i];
		}
	}
} // end class MergeSortApp
