package sort;

import java.util.Arrays;

import utils.ArrayUtils;

public class ShellSort {
	public static void main(String[] args) {
		int[] origin = ArrayUtils.randomIntArray(20);
		int[] a = origin.clone();
		System.out.println("origin = " + Arrays.toString(origin));
		shellSort(a);
		Arrays.sort(origin);
		System.out.println("Arrays.sort(origin) =>  \n " + Arrays.toString(origin));
		System.out.println("sorted a => \n " + Arrays.toString(a));
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
