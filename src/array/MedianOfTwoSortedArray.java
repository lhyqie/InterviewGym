package array;

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
		int[] a = {1, 12, 15, 26, 38};
	    int[] b = {2, 13, 17, 30, 45};
		
//		int[] a = {1, 2, 3, 6};
//	    int[] b = {4, 6, 8, 10};
	    
//	    for (int i = 0; i < a.length; i++) {
//			System.out.println(median(a, 0, i));
//		}
	    
	    System.out.println(getMedian(a,b));
	    
	}
	
	public static double getMedian(int[] a, int[] b){
		if(a.length != b.length) throw new IllegalArgumentException("a and b are not equal size");
		return getMedianHelper(a, b , 0, a.length - 1, 0, b.length - 1);
	}
	
	public static double getMedianHelper(int[] a, int[] b, int low1, int high1, int low2, int high2){
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
