package array;

import static utils.ArrayUtils.print;

/**
 * find the max consecutive sum of an array
 * 
 * for example in {1,2,3,4,-100,0,4,5}   1+2+3+4 = 10 is the maximum sum of a consecutive elements
 * however     in {1,2,3,4,-100,101,4,-1} 101+4 = 105 is the maximum sum of a consecutive elements
 *
 */

public class MaxConsecutiveSum {
	
	public static void main(String[] args) {
		int a[] = {1,2,3,4,-100,0,4,5};
		//int a[] = {1,2,3,4,-100,101,4,-1};
		//int a[] = {1,2,-1,3,5,0,-100,-8,2,9,100,-20};
		print(a);
		System.out.println("max consecutive sum = " + maxConsecutiveSum(a));	
	}
	
	/**
	 * 
	 * @param a:  the input array
	 * @return    the max consecutive sum
	 */
	public static int maxConsecutiveSum(int[] arr) {
		int max = 0;
		int accSum = 0; // the accumalative sum, accSum is let to be >= 0
		for (int e : arr) {
			if(accSum + e <0){   
				// in this case  e < 0 , accSum >= 0,  |accSum| < |e|
				// should not choose e, reset accSum to be 0 
				accSum = 0;
			}else{ // accSum + e >= 0  doesn't mean e > 0,  but element after e may > 0 and increase accSum overall
				accSum += e;
				max = Math.max(max, accSum);
			}
		}
		return max;
	}
}


