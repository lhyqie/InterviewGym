package recursion;

import java.util.*;

public class CombinationSumIII {
	
	public static void main(String[] args) {
		CombinationSumIII app = new CombinationSumIII();
		List<List<Integer>> ret = app.combinationSum3(3, 9);
//		for (List<Integer> list : ret) {
//			System.out.println(list);
//		}
	}
	
	List<List<Integer>> res = null;
	
    public List<List<Integer>> combinationSum3(int k, int n) {
    	res = new ArrayList<List<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        search(n, k, 0, path);
        return res;
    }
    
    void search(int n, int k, int i, Stack<Integer> path){
        if(i == k - 1){
            int sum = 0;
            for(int num : path) sum += num;
            int last = n - sum;
            if(last > path.get(path.size()-1) && last <= 9 && !path.contains(last) && path.size() == k -1){
                path.push(last);
                List<Integer> newone = new ArrayList<Integer>(path);
                Collections.sort(newone);
                System.out.println(newone);
                res.add(newone);
                path.pop();
            }
        }else{
        	int next = 1;
        	if(path.size() >0) next = path.get(path.size()-1);
            for(int j = next; j <= 9; j++){
                if(!path.contains(j) && path.size() < k ){
                    path.push(j);
                    search(n, k, i+1, path);
                    path.pop();
                }
            }
        }
    }
}
