package random;

import java.util.Arrays;

public class RandomPickSubArray {
	public static void main(String[] args) {
		int a [] = {1,2,3,4,5,6,7,8,9};
		System.out.println(Arrays.toString(a));
		pickRandomSubSetArray(a, 5);
		System.out.println();
		pickRandomKNumbers(a,5);
	}

	public static int rand(int lower , int higher){
		return lower + (int)(Math.random() * (higher - lower + 1)) ;
	}
	private static void pickRandomSubSetArray(int[] a, int m) {
		if(m > a.length) return;
		for (int i = 0; i < m; i++) {
			int index = rand(i, a.length-1);
			int tmp = a[index];
			a[index] = a[i];
			a[i] = tmp;
			System.out.println(a[i]);
		}
	}
	
	//
	public static void pickRandomKNumbers(int a[], int k)
	{
	    for(int i = 0; i < k; i++){
	        //random an index ranIndex and swap a[i] with a[ranIndex]
	        int ranIndex = random(i, a.length-1);
	        swap(a, i, ranIndex);
	        System.out.println(a[i]);
	    }
	}    

	private static void swap(int a[], int i, int j){
	    int t = a[i]; a[i]=a[j]; a[j] = t;
	}

	// random a number between low and high, inclusive
	private static int random(int low, int high)
	{
	    return   (int)(Math.random()*(high-low+1)) + low;
	}
}
