package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class BubbleSort {
	public static void main(String[] args)
	{
		int a[] = ArrayUtils.randomIntArray(20);
		System.out.println(Arrays.toString(a));
		bubbleSort(a);
		System.out.println(Arrays.toString(a));
	} 
	
	public static void bubbleSort(int[] a){
		int n = a.length;
		for (int i = 0; i < n-1; i++) {  // place the largest element of a[0...n-1-i] at a[n-1-i] 
			for (int j = 1; j <= n-1-i; j++) {
				if(a[j-1] > a[j]) swap(a, j-1, j);
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}
}
