package array;

import java.util.*;

public class AllSubsetWithDup {
	public static void main(String[] args) {
		int[] arr =  {1,2,2,3}; //{1,2,3};
		List<List<Integer>> res = getAllSubsetWithDup(arr);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
	}
	

    public static List<List<Integer>> getAllSubsetWithDup(int nums[]){
		Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        search(res, nums, 0, path);
        return res;
	}
	
	private static void search(List<List<Integer>> res, int[] nums, int k, ArrayList<Integer> path) {
        res.add(new ArrayList<Integer>(path));
        for(int i = k; i < nums.length; i++){
            if( i > k && nums[i-1] == nums[i]){
                continue;   // skip duplicates
            }
            path.add(nums[i]);  //choose nums[i]
            search(res, nums,  i+ 1, path); 
            path.remove(path.size() - 1);          //undo choosing
        }
    }
    
}
