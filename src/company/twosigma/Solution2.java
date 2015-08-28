package company.twosigma;

import java.util.*;

public class Solution2 {
	static class comp implements Comparator<String> {
		  public int compare(String o1, String o2) {
		    if (o1.length() > o2.length()) {
		      return 1;
		    } else if (o1.length() < o2.length()) {
		      return -1;
		    } else {
		      return 0;
		    }
		  }
		}
	
	public static void main(String[] args) {
		StringChain();
	}
	
	public static void StringChain(){
		String[] w = {"bda", "a", "b", "ba", "bca", "bdca"};
		int res = longest_chain(w);
		System.out.println(res);
	}
	
	static int longest_chain(String[] w) {
		Arrays.sort(w, new comp());
		//System.out.println(Arrays.toString(w));
		int n = w.length;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < n; i++){
			map.put(w[i], 1);
		}
		for(int i = 0; i < n; i++){
			String word = w[i];
			for(int j = 0; j < word.length(); j++){
				String tmp = word.substring(0, j) + word.substring(j+1);
				//System.out.print("->" + tmp+ " ");
				if(map.containsKey(tmp)){
					map.put(w[i], map.get(tmp)+1);
				}
			}
			//System.out.println();
		}
		
		int max = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    int value = entry.getValue();
		    max = Math.max(max, value);
		}
		
		return max;
	}
}
