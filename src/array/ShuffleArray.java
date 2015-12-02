package array;

import java.util.Arrays;
import utils.ArrayUtils;

public class ShuffleArray {
	public static void main(String[] args) {
		int[] arr = ArrayUtils.randomIntArray(5);
		System.out.println(Arrays.toString(arr));
		permutate(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 * in-place permutate
	 */
	public static void permutate(int [] arr){
		for (int i = 0; i < arr.length - 1; i++) {
			int j = random(i, arr.length);
			swap(arr, i, j);
		}
	}
	
	private static void swap(int[] arr, int i, int j){
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t; 
	}
	
	/**
	 * random a number in [min, max)
	 * @param min
	 * @param max
	 * @return
	 */
	private static int random(int min, int max){
		return (int)(Math.random() * (max - min)) + min;
	}
}
