package array;

import java.util.ArrayList;
import java.util.List;

public class AllSubSetNoDup {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		List<List<Integer>> res = getAllSubset(arr);
		for (List<Integer> list : res) {
			System.out.println(list);
		}
	}
	
	public static List<List<Integer>> getAllSubset(int arr[]){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int n = arr.length;
		int m = 1 << n;
		for(int i = 0; i < m; i++){
			List<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < n ; j++){
				if(  (( i >> j) & 1) != 0){
					row.add(arr[j]);
				}
			}
			res.add(row);
		}
		return res;
	}
}
