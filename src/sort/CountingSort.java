package sort;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args) {
		int a[] = {3,1,3,4,1,3,2,7,1,5,3,1,8,9,1};
		int b[] = new int[a.length];
		System.out.println(Arrays.toString(a));
		countingSort(a,b,9);
		System.out.println(Arrays.toString(b));
	}
	/*
	 *  b is the output
	 *  k is the maximum element in a, assume 0 is the minimum
	 */
	public static void countingSort(int a[], int b[], int k){
		
		//c[i] is the accumulated sum, meaning how many numbers whose value <= a[i]
		int c[] = new int[k+1]; 			// 0 ... k
		for (int i = 0; i < a.length; i++) {
			c[a[i]] ++;
		}
		for (int j = 1; j < c.length; j++) {
			c[j] = c[j-1] + c[j];
		}
		System.out.println(Arrays.toString(c));
		
		for (int i = 0; i < a.length; i++) {
			b[--c[a[i]]] = a[i];
		}
	}
}
