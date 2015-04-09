package sort;

import java.util.Arrays;


/*
 * count the # of inversions of an array modifying the merge sort
 * 
 */
public class CountingInversionsOfArray {
	private static int inversionCnts ;
	private static int[] numbers;
	private static int[] helper;
	private static int n;
	public CountingInversionsOfArray(){
		
	}
	public static int getInversionCnts(){
		return inversionCnts;
	}
	public static void sort(int[] array) {
		inversionCnts = 0;
		numbers = array;
		n = numbers.length;
		helper = new int[n];
		mergesort(0, n - 1);
	}

	private static void mergesort(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = (low + high) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = numbers[i];
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				numbers[k++] = helper[i++];
			} else {
				numbers[k++] = helper[j++];
				inversionCnts += middle - i + 1;     //[the only modification from MergeSort ]
			}
			
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			numbers[k++] = helper[i++];
		}
		while (j <= high) {
			numbers[k++] = helper[j++];
		}
	}

	//brute-force 
	public static int brute_force_InversionCnts(int [] array){
		int cnt = 0;
		for (int j = 1; j < array.length; j++) {
			for (int i = 0; i <= j - 1; i++) {
				if( array[i] > array[j]) cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int [] array =  {100,-1,-10,1,4,7,2,1,4,1,9,7,45,3,5,4,3,9,75,1,3,31,41,11,1,};
		               //{1,3,5,2,4,6};
		System.out.println("Brute force \t\t # of inversions = " + brute_force_InversionCnts(array));
		System.out.println(Arrays.toString(array));
		CountingInversionsOfArray.sort(array);
		System.out.println(Arrays.toString(array));
		System.out.println("Modied from mergesort \t # of inversions = "+ getInversionCnts());
				
	}
}
