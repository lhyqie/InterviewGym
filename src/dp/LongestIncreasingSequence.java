package dp;

/**
 * there is still a O(nlogn) solution 
 * 
 * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 * 
 */
public class LongestIncreasingSequence {
	
	/**
	 * O(n^2) solution with Dynamic Programming
	 * @param a
	 * @return
	 */
	public static int findLongestIncreasingSequence(int[] a){
		int c[] = new int[a.length];
		c[0] = 1;
		for (int j = 1; j < c.length; j++) {
			for (int i = 0; i < j; i++) {
				if(a[i] < a[j] && c[i] + 1 > c[j]){
					c[j] = c[i] + 1;
				}
			}
		}
		
		int max = 0;
		for (int e : c) {
			if(max < e) max = e;
		}
		return max;
		
		
	}
	
	public static void main(String[] args) {
		int[] a = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
				  //{1,1,1}; 
				  //{1, 1, 2, 2, 3, 3, 3, 5, 6, 7, 7, 9};
		System.out.println(findLongestIncreasingSequence(a));
	}
}
