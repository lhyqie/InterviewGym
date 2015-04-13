package array;

import java.util.HashMap;


public class LongestUniqueSubArray {
	
	public static void main(String[] args) {
		Integer[] a =  {1, 2, 3, 3, 1, 2, 3, 6, 9, 5, 7, 7};
	   	
		System.out.println(get_longest_sequence(a));
		
	}
	
	public static int get_longest_sequence(Integer[] numbers) {
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
}
