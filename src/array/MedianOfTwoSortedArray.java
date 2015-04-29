package array;

import java.util.Arrays;


/**
 * Question: There are 2 sorted arrays A and B of size n each. 
 * Write an algorithm to find the median of the array obtained after merging the above 2 arrays(i.e. array of length 2n). 
 * The complexity should be O(log(n))
 * 
 * @author howie
 *
 */
public class MedianOfTwoSortedArray {
	
	public static void main(String[] args) {
		int a[] = null;
		int b[] = null;
		
//		a = new int[]{1, 12, 15, 26, 38};
//		b = new int[]{2, 13, 17, 30, 45};
		
//		a = new int[]{1, 2, 3, 6};
//		b = new int[]{4, 6, 8, 10};
		
//		a = new int[]{3, 7, 7, 9, 10};
//		b = new int[]{2, 3, 7, 9, 10};
	    
		a = new int[]{6, 7, 8, 9, 10};
		b = new int[]{1, 2, 3, 4, 5};
		
//		a = ArrayUtils.randomIntArray(20);
//		b = ArrayUtils.randomIntArray(20);
//		Arrays.sort(a);
//		Arrays.sort(b);
		

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
		
		System.out.println("Method 1 getMedian(a, b) = " + getMedian(a,b));
	    System.out.println("Method 2 getMedian2(a, b) = " + getMedian2(a,b));
	    
	}
	
	/**
	 * Method 2: binary search
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getMedian2(int[] a, int[] b){
		if(a.length != b.length) throw new IllegalArgumentException("a and b are not equal size");
		int n = a.length;
		return getMedian2Helper(a, b, 0, n-1 , n);
	}
	
	/**
	 * @param a
	 * @param b
	 * @param left  : left of a 
	 * @param right : right of a
	 * @param n     : size of a or b 
	 * @return
	 */
	private static double getMedian2Helper(int[] a, int[] b, int left, int right, int n){
		if (left > right) // median is not in a, then search in b
			return getMedian2Helper(b, a, 0, n-1, n);
		
		// the following ensures i+j = n-1 meaning  there are n elements in a[0...i] + b[0...j] 
		int i = (left + right)/2;   /* index of a[] */
		int j = n-i-1;  		    /* index of b[] */
	 
	    /* Recursion terminates here.*/
	    if (b[j] <= a[i] && (j == n-1 || a[i] <= b[j+1]))   //  case 1 : i == 0 && a[i] greater than all elements in b[]
	    												    //  case 2 : b[j] <= a[i] <= b[j+1] 
	    {
	        /* a[i] is decided as median 2, now select the median 1
	           (element just before a[i] in merged array) to get the
	           average of both */
	    	// two candidates : a[i-1] and b[j] 
	        if (i == 0 || b[j] > a[i-1])  // b[j] is median 1
	            return (a[i] + b[j])/2.0;
	        else
	            return (a[i] + a[i-1])/2.0; 
	    }
	 
	    /*Search in left half of ar1[]*/
	    else if (a[i] > b[j] && j != n-1 && a[i] > b[j+1])
	        return getMedian2Helper(a, b, left, i-1, n);
	 
	    /*Search in right half of ar1[]*/
	    else /* ar1[i] is smaller than both ar2[j] and ar2[j+1]*/
	        return getMedian2Helper(a, b, i+1, right, n);
	}
	
	
	//--------------------------------------------------------------------------------------------------
	/**
	 * Method 1 : comparing sub arrays
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getMedian(int[] a, int[] b){
		if(a.length != b.length) throw new IllegalArgumentException("a and b are not equal size");
		return getMedianHelper(a, b , 0, a.length - 1, 0, b.length - 1);
	}
	
	private static double getMedianHelper(int[] a, int[] b, int low1, int high1, int low2, int high2){
		if(low1 == high1){
			return  (a[low1] + b[low2]) * 1.0 /2;
		}else if(low1 +1 == high1){
			return  (Math.max(a[low1], b[low2]) + Math.min(a[high1], b[high2])) * 1.0 / 2;
		}
		
		double m1 = median(a, low1, high1);
		double m2 = median(b, low2, high2);
		if( m1 == m2 ){
			return m1;
		}else if( m1 < m2 ){
			if( (high1-low1 +1) % 2 == 0 ){
				return getMedianHelper(a, b, (low1 + high1 - 1)/2, high1, low2, (low2+high2 + 1)/2 );
			}else{
				return getMedianHelper(a, b, (low1 + high1)/2, high1, low2, (low2+high2)/2);
			}
			
		}else{
			if( (high1-low1 +1) % 2 == 0 ){
				return getMedianHelper(a, b, low1, (low1 + high1 + 1)/2, (low2+high2 - 1)/2,  high2);	
			}else{
				return getMedianHelper(a, b, low1, (low1 + high1)/2, (low2+high2)/2,  high2);	
			}
			
		}
	}
	
	/**
	 * median of arr[low...high]
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static double median(int arr[], int low, int high){
		if( (high - low + 1) % 2 != 0){
			return arr[ (low + high) /2 ];
		}else{
			return  (arr[ (low + high) /2 ] + arr[ (low + high) /2  + 1] ) * 1.0 / 2 ;
		}
	}
}
