package sort;

/**
 * How many significant subsequences are in this sequence, where the number of heads are not less than
 *  the number of tails?
 */
public class SubstringsHeadsGTETails {
	
	private static int[] helper;
	private static int[] sum;
	private static int nInversions;
	
	public static void main(String[] args) {
		char[] chs = "HTHTTH".toCharArray(); 
		System.out.println("Naive Approach : " + nSubstringsWithHeadsGTETails_naive(chs));
		System.out.println("Advanced Approach : " +nSubstringsWithHeadsGTETails_advanced(chs));
	}
	
	/**
	 * @param chs
	 * @return
	 * Time Complexity O(nlogn)
	 *
	 *    	 For example
	 *    H  T  H  T  T  H
	 * 0  1  0  1  0 -1  0 
	 * 
	 * Hint:
	 * let H be 1, T be -1
	 * construct a cumulative sum
	 * sum[0] = 0
	 * sum[i] = chs[0] + ... chs[i-1]
	 * 
	 * the # of heads of substring chs[i:j] = sum[j] - sum[i]
	 * the total # of substrings whose # heads >= # tails =  sum.length * (sum.length - 1) / 2  - # of inversions
	 * refer to sort.CountingInversionsOfArray.java
	 */
	public static int nSubstringsWithHeadsGTETails_advanced(char[] chs) {
		nInversions = 0;
		sum = new int[chs.length + 1];
		sum[0] = 0;
		for (int i = 1; i < sum.length; i++) {
			 if(chs[i-1] == 'H'){
				 sum[i] = sum[i-1] + 1;
			 }else{ // 'T'
				 sum[i] = sum[i-1] - 1;
			 }
		}
		helper = new int[sum.length];
		mergesort(0, sum.length - 1);
		return sum.length * (sum.length - 1) / 2 - nInversions;
	}

	private static void mergesort(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
		if (low < high) {
			// Get the index of the element which is in the middle
			int middle = (low + high) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private static void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			helper[i] = sum[i];
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				//System.out.println("i = "+ i + " j = "+j + " \t" + helper[i] + " " + helper[j]);
				sum[k++] = helper[i++];
				//total_count ++ ;
			} else {
				sum[k++] = helper[j++];
				nInversions +=  middle -i + 1; 
			}
			
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			sum[k++] = helper[i++];
		}
		while (j <= high) {
			sum[k++] = helper[j++];
		}
	}

	
	/**
	 * cnts[i] = the # of heads in substring s[0....i-1]
	 * the # of heads in substring s[i...j] = cnts[j+1] - cnts[i] 
	 * For example
	 *     H  T  H  T  T  H
	 *  0  1  0  1  0 -1  0 
	 * 
	 * @param chs : the input string
	 * @return the number of substrig whose "H" are not less than the number of "T"?
	 * Time Complexity O(n^2)
	 */
	public static int nSubstringsWithHeadsGTETails_naive(char []chs){
		int total_cnt = 0;
		int[] cnts = new int[chs.length + 1];
		cnts[0] = 0;
		for (int i = 1; i < cnts.length; i++) {
			 if(chs[i-1] == 'H'){
				 cnts[i] = cnts[i-1] + 1;
			 }else{ // 'T'
				 cnts[i] = cnts[i-1] - 1;
			 }
		}
		
		//System.out.println(Arrays.toString(cnts));
		
		for (int i = 0; i < cnts.length; i++) {
			for (int j = i+1; j < cnts.length; j++) {
				if(cnts[j] - cnts[i] >= 0 ){
					//System.out.println("i =" + i + ", j=" + j);
					total_cnt ++;
				}
			}
		}
		return total_cnt;
	}
}
