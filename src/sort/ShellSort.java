package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class ShellSort {
	public static void main(String[] args) {
		int a[] = ArrayUtils.randomIntArray(20);
		System.out.println(Arrays.toString(a));
		shellSort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void shellSort(int[] a) {
		int n = a.length;
		for (int gap = n/2; gap > 0; gap = gap == 2 ? 1 : (int) (gap /2.2)) {
			for (int j = gap; j < n; j++) {
				int t = a[j];
				int i = j;
				for (; i>=gap && t < a[i-gap]; i-=gap) {
					a[i] = a[i-gap];
				}
				a[i] = t;
			}
		}
		
	}
	
}
