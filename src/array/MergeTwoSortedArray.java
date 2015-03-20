package array;

import java.util.Arrays;

import utils.ArrayUtils;
import static utils.ArrayUtils.print; 

public class MergeTwoSortedArray {
	public static void main(String[] args) {
		int[] a = ArrayUtils.randomIntArray(5);
		int[] b = ArrayUtils.randomIntArray(7);
		//sort a and b 
		Arrays.sort(a);
		Arrays.sort(b);
		System.out.print("Array a :");  print(a);
		System.out.print("Array b :");  print(b);
		int[] c = mergeSortedArray(a, b);
		System.out.print("Array c :");  print(c);
	}
	
	/**
	 * @param a : one sorted array
	 * @param b : another sorted array
	 * @return a sorted array merged from them
	 */
	public static int[] mergeSortedArray(int[] a, int[] b){
		int[] c = new int[a.length + b.length];
		int pa = 0, pb = 0, pc = 0;
		while(pa < a.length && pb < b.length){
			if(a[pa] < b[pb]){
				c[pc++] = a[pa++];
			}else{
				c[pc++] = b[pb++];
			}
		}
		while(pa < a.length) c[pc++] = a[pa++];
		while(pb < b.length) c[pc++] = b[pb++];
		return c;
	}
}
