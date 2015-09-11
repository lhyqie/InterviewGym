package dp;

import java.util.*;

public class FactorCombinations {
	
	 public static void main(String[] args) {
		 FactorCombinations app = new FactorCombinations();
		 //List<List<Integer>> ret = app.getFactors(4);
		 List<List<Integer>> ret = app.getFactors(64);
		 for (List<Integer> list : ret) {
			System.out.println(list);
		}
	 }
	 
	 public List<List<Integer>> getFactors(int n) {
	        Set<List<Integer>> ret = new LinkedHashSet<List<Integer>>();
	        if(n <= 1 || isPrime(n)) return new ArrayList<List<Integer>>();
	        HashMap<Integer, Set<List<Integer>>> factorMap = new HashMap<Integer, Set<List<Integer>>>();
	        for(int i = 0; i * i < n; i++){
	        	if(isPrime(i)){
	        		Set<List<Integer>> e = new LinkedHashSet<List<Integer>>();
	        		List<Integer> row = new ArrayList<Integer>();
	        		row.add(i);
	        		e.add(row);
	        		factorMap.put(i, e);
	        	}
	        }
	        ret = getF(factorMap, n);
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        for (List<Integer> list : ret) {
				if(list.size() > 1){
					res.add(list);
				}
			}
	        return res;
	    }
	    
	    public Set<List<Integer>> getF(HashMap<Integer, Set<List<Integer>>> factorMap, int n){
	        Set<List<Integer>> ret = new LinkedHashSet<List<Integer>>();
	        if(factorMap.containsKey(n)) return factorMap.get(n);
	        for(int i = 2; i * i <= n ;i ++){
	            if(n % i == 0){
	                int j = n / i;
	                
	                List<Integer> row = new ArrayList<Integer>();
	                row.add(i); row.add(j);
	                ret.add(row);
	                
	                row = new ArrayList<Integer>();
	                row.add(n);
	                ret.add(row);
	                
	                Set<List<Integer>> fs1 = getF(factorMap, i);
	                Set<List<Integer>> fs2 = getF(factorMap, j);
	                
                    for(List<Integer> f1 : fs1){
                        for(List<Integer> f2 : fs2){
                            row = new ArrayList<Integer>(f1);
                            row.addAll(f2);
                            Collections.sort(row);
                            ret.add(row);
                        }
                    }
	            }
	        }
	        factorMap.put(n, ret);
	        return ret;
	    }
	    
	    public boolean isPrime(int n){
	        for(int i = 2; i * i <= n; i++){
	            if(n % i == 0) return false;
	        }
	        return true;
	    }
}
