package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * assume no duplicates
 */
public class AllPermutationNoDup {
	
	public static void main(String[] args) {
		int[] nums =  //{1,2,3};
						{1,2,2};
		List<List<Integer>> res = permute(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
	}
	
	
	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		search(res, nums, 0);
		return res;
    }
    
    public static void search(List<List<Integer>> res, int[] nums, int k){
        if(k == nums.length) {
        	List<Integer> row = new ArrayList<Integer>();
            for (int num : nums) {row.add(num);}
            res.add(row);
            return;
        }
        search(res, nums, k+1);  // no swapping
        for(int j = k+1; j < nums.length; j++){
            swap(nums, k, j);             //swap with nums after it at index j
            search(res, nums, k+1);
            swap(nums, k, j);			  //undo the swap
        }
    }
    
    private static void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
