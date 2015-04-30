package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class InsertionSort {
	public static void main(String[] args) {
		int a[] = ArrayUtils.randomIntArray(10);
		System.out.println(Arrays.toString(a));
		insertionSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void insertionSort(int[] a){
		int n = a.length;
		for (int j = 1; j < n; j++) { // looping invariant a[0...j-1] is sorted, insert j to a[0...j] to make it still sorted
 			int i = 0;
			while( i < j && a[i] <= a[j]) i++;
			if(a[i] > a[j]){
				int t = a[j];
				for (int k = j ; k >= i+1; k--) {
					a[k] = a[k-1];
				}
				a[i] = t;
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}
}
