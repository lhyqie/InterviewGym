package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class SelectionSort {
	public static void main(String[] args) {
		int a[] = ArrayUtils.randomIntArray(20);
		System.out.println(Arrays.toString(a));
		selectionSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void selectionSort(int[] a){
		int n = a.length;
		for (int i = 0; i < n; i++) { // place the smallest element of a[i...n-1] at a[i]
			for (int j = i+1; j < n; j++) {
				if(a[i] > a[j]) swap(a, i, j);
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}
}
