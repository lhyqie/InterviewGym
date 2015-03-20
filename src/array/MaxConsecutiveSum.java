package array;

import java.util.Arrays;
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
		
		return max;
	}
}

