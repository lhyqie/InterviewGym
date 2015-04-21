package array;

import java.util.Arrays;

import utils.ArrayUtils;

public class MedianOfTwoSortedArrayII {
	public static void main(String[] args) {
		int a[] = null;
		int b[] = null;
		
//		a = new int[] {1, 3, 4};
//	    b = new int[] {2, 3, 5, 5, 5};
	    
//	    a = new int[] {2, 3, 3, 3, 6, 7, 7, 9, 10};
//	    b = new int[] {10};
		
//		a = new int[] {9, 10};
//		b = new int[] {};
	    
	    int n1 = (int)(Math.random() * 10);
		int n2 = (int)(Math.random() * 10); // 10 - n1;		
		a = ArrayUtils.randomIntArray(n1, 0, 10);
		b = ArrayUtils.randomIntArray(n2, 0, 10);
		Arrays.sort(a);
		Arrays.sort(b);
		
		System.out.println("a[] = " + Arrays.toString(a));
		System.out.println("b[] = " + Arrays.toString(b));
		
	    int c[] = new int[a.length + b.length];
	    for (int i = 0; i < a.length; i++) c[i] = a[i];
	    for (int i = 0; i < b.length; i++) c[i+a.length] = b[i];
		Arrays.sort(c);
		System.out.print("Ground truth is = ");
		if(c.length % 2 != 0){
			System.out.println(c[c.length/2]);
		}else{
			System.out.println((c[c.length/2] + c[c.length/2-1])/2.0);
		}
	    
	    System.out.println(getMedian(a, b));
	}
	
	/**
	 * binary search
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getMedian(int[] a, int[] b){
		if(a.length + b.length == 0) throw new IllegalArgumentException("no elements in a or b");
		int n = (a.length + b.length)/2;
		if(a.length == 0){
			if(b.length % 2 != 0) return b[b.length/2];
			else return (b[b.length/2-1] + b[b.length/2])/2.0;
		}
		if(b.length == 0){
			if(a.length % 2 != 0) return a[a.length/2];
			else return (a[a.length/2-1] + a[a.length/2])/2.0;
		}
		return getMedianHelper(a, b, 0, a.length-1, n);
	}
	
	/**
	 * @param a
	 * @param b
	 * @param left  : left of a 
	 * @param right : right of a
	 * @param n     : half the size of 
	 * @return
	 */
	private static double getMedianHelper(int[] a, int[] b, int left, int right, int n){
		if (left > right) // median is not in a, then search in b
			return getMedianHelper(b, a, 0, b.length-1, n);
		
		// the following ensures i+j = n-1 meaning  there are n elements in a[0...i] + b[0...j] 
		int i = (left + right)/2;   /* index of a[] */
		int j = n-i-1;  		    /* index of b[] */
		if(j < 0) { 
			if(a[i] <= b[0]){         // each element of a[] is <= each element of b[]
				if((a.length + b.length) % 2 != 0) return a[n];
				else return (a[n-1] + a[n])/2.0;
			}
			else{
				return getMedianHelper(a, b, left, i-1, n);
			}
		}else if(j >= b.length){
			return getMedianHelper(a, b, i+1, right, n);
		}
		/* Recursion terminates here.*/
		else if(j == b.length - 1){
			if(b[j] <= a[i]){  //a[i] is median 2
				if((a.length + b.length) % 2 != 0){
					return a[i];
				}else{
					if (i == 0 || b[j] > a[i-1])  // b[j] is median 1
			            return (a[i] + b[j])/2.0;
			        else
			            return (a[i] + a[i-1])/2.0; 
				}
				
			}else{
				return getMedianHelper(a, b, i+1, right, n);
			}
		}
	    else if (b[j] <= a[i] && a[i] <= b[j+1])   //  case 1 : i == 0 && a[i] greater than all elements in b[]
	    										   //  case 2 : b[j] < a[i] <= b[j+1] 
	    {	
	    	/* a[i] is decided as median 2, now select the median 1
	           (element just before a[i] in merged array) to get the
	           average of both */
	    	if( (a.length + b.length) % 2 != 0 ){
	    		return a[i];
	    	}else{
	    		// two candidates : a[i-1] and b[j] 
	    		if (i == 0 || b[j] > a[i-1])  // b[j] is median 1
		            return (a[i] + b[j])/2.0;
		        else
		            return (a[i] + a[i-1])/2.0; 
	    	}
	    }
	 
	    /*Search in left half of ar1[]*/
	    else if (a[i] > b[j] && j != n-1 && a[i] > b[j+1])
	        return getMedianHelper(a, b, left, i-1, n);
	 
	    /*Search in right half of ar1[]*/
	    else /* ar1[i] is smaller than both ar2[j] and ar2[j+1]*/
	        return getMedianHelper(a, b, i+1, right, n);
	}
	
}
