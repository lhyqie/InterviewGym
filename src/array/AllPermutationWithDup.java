package array;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class AllPermutationWithDup {
	public static void main(String[] args) {
		int[] nums = {1,2,2}; // 
					 //{1,2,3};
		List<List<Integer>> res = permuteUnique(nums);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
	}
	
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
        permuteUnique(res, nums, 0);
        return res;
    }
    
    static void permuteUnique(List<List<Integer>> res, int[] nums, int k) {
        if (k == nums.length) {
        	List<Integer> item = new ArrayList<Integer>();
            for (int h = 0; h < nums.length; h++) {
                item.add(nums[h]);
            }
            res.add(item);
        }
        for (int j = k; j < nums.length; j++) {
            if (isSwap(nums, k, j)) {
                swap(nums, j , k);
                permuteUnique(res, nums, k + 1);                
                swap(nums, j , k);
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j){
    	int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }
    static boolean isSwap(int[] arr, int k, int j) {
        for (int i = k; i < j; i++) {
            if (arr[i] == arr[j]) {
                return false;
            }
        }
        return true;
    }
}
