package number;

import java.util.*;

/**
 * Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

 * @author howie
 *
 */
public class UglyNumberII {
	public static int ugly2(int n){
		int[] f = new int[n];
	    f[0] = 1;
	    int i2 = 0, i3 = 0, i5 = 0;

	    for(int i = 1; i < n; i++) {
	        int next2 = f[i2]*2, next3 = f[i3]*3, next5 = f[i5]*5;
	        int min = Math.min(next2, Math.min(next3, next5));
	        f[i] = min;
	        if(min == next2) i2++;
	        if(min == next3) i3++;
	        if(min == next5) i5++;
	    }
	    System.out.println(Arrays.toString(f));
	    return f[n-1];
	}
	public static void main(String[] args) {
		ugly2(7);
	}
}
