package array;

import java.util.HashMap;


public class LongestUniqueSubArray {
	
	public static void main(String[] args) {
		Integer[] a =  {1, 2, 3, 3, 1, 2, 3, 6, 9, 5, 7, 7};
	   	
		System.out.println(get_longest_sequence1(a));
		System.out.println(get_longest_sequence2(a));
		
	}
	
	/**
	 * 
	 * @param numbers
	 * @return
	 * O(n^2) for worst case
	 */
	public static int get_longest_sequence1(Integer[] numbers) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		//map from the number to its index
		int max = 0;
		int start = 0, end = 0;
		for (int i = 0; i < numbers.length; i++) {
			if(map.containsKey(numbers[i])){
				end = i - 1;
				//System.out.println("start =" + start +" end ="+end + " len = "+ (end - start + 1) );
				max = Math.max(max, end - start + 1);
				i = map.get(numbers[i]) + 1;    // back to the next index of the first number of the conflicted two
				start = i;
				map.clear();
				map.put(numbers[i], i);
			}else{
				map.put(numbers[i], i);
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param numbers
	 * @return
	 * O(n) because i and j both scan the array once
	 */
	public static int get_longest_sequence2(Integer[] numbers) {
		boolean [] exist = new boolean[256];
		int i = 0 , maxLen = 0;
		for (int j = 0; j < numbers.length; j++) {
			while(exist[numbers[j]]){
				exist[numbers[i]] = false;
				i++;
			}
			exist[numbers[j]] = true; 
			maxLen = Math.max(maxLen, j - i + 1);
		}
		return maxLen;
	}
	
}
