package utils;

import java.util.Random;


public class ArrayUtils {
	/**
	 * generate an array of n random integers each of which is in [0, 10]
	 * without using seed
	 * 
	 * @param n : size of the array
	 */
	public static int[] generateRandomIntArray(int n){
		return generateRandomIntArray(n, 0, 10);
	}
	
	/**
	 * generate an array of n random integers each of which is in [min, max]
	 * without using seed
	 * 
	 * @param n : size of the array
	 * @param min : minimum value of the array
	 * @param max : maximum value of the array
	 */
	public static int[] generateRandomIntArray(int n, int min, int max){
		int arr[] = new int[n];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt((max - min) + 1) + min;
		}
		return arr;
	}
	
	/**
	 * generate an array of n random integers each of which is in [min, max]
	 * with using seed
	 * 
	 * @param n : size of the array
	 * @param min : minimum value of the array
	 * @param max : maximum value of the array
	 * @param seed : the seed for class Random
	 * @see {@link java.util.Random}
	 */
	public static int[] randomIntArray(int n, int min, int max, long seed){
		int arr[] = new int[n];
		Random rand = new Random(seed);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt((max - min) + 1) + min;
		}
		return arr;
	}
	
	public static void print(int [] arr){
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if(i == arr.length - 1){
				System.out.println("]");
			}else{
				System.out.print(", ");
			}
		}
	}
}
