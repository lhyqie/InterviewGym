package random;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class RandomNumberWithWeight {
	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5};
		int [] weights = {1,2,4,8,16};
		TreeMap<Integer, Integer> cntMap = new TreeMap<Integer, Integer>(); 
		for (int i = 0; i < 10000; i++) {
			int r = random(nums,weights);
			cntMap.put(r, cntMap.get(r) == null ? 0 : cntMap.get(r) + 1);
		}
		System.out.println(cntMap);
		
		int sum = 0;
		for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
			sum += entry.getValue();
		}
		
		for (Map.Entry<Integer, Integer> entry : cntMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue()*1.0/sum);
		}
	}
	
	public static int random(int[] nums, int[] weights){
		//return nums[(int)(Math.random()*nums.length)];
		
		int []ends = new int[nums.length];
		for (int i = 0; i < ends.length; i++) {
			if(i == 0) ends[i] = weights[i];
			else ends[i] = ends[i-1] + weights[i];
		}
		System.out.println(Arrays.toString(ends));
		
		int r = (int)(Math.random()*ends[ends.length-1]);
		for (int i = 0; i < ends.length; i++) {
			if(i==0){
				if(r<ends[0]) return nums[i];
			}else{
				if(r>=ends[i-1] && r<ends[i]){
					return nums[i];
				}
			}
		}
		return Integer.MAX_VALUE; // error
		
	}
	
}
