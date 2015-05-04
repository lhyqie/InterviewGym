package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class SelectionSort {
	public static void main(String[] args) {
		int[] origin = ArrayUtils.randomIntArray(20);
		int[] a = origin.clone();
		System.out.println("origin = " + Arrays.toString(origin));
		selectionSort(a);
		Arrays.sort(origin);
		System.out.println("Arrays.sort(origin) =>  \n " + Arrays.toString(origin));
		System.out.println("sorted a => \n " + Arrays.toString(a));
	}
	
	public static void selectionSort(int[] a){
		int n = a.length;
		for (int i = 0; i < n; i++) { // place the smallest element of a[i...n-1] at a[i]
			int minIndex = i;
			int min = Integer.MAX_VALUE;
			for (int j = i+1; j < n; j++) {
				if(min > a[j]){
					minIndex = j;
					min = a[j];
				}
			}
			swap(a, i, minIndex);
		}	
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}
}
