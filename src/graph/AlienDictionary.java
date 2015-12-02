package graph;

import java.util.*;

/*There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

public class AlienDictionary {
	
	 public static void main(String[] args) {
		 String [] words = {
				 "wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"
		 };
		 
		 words = new String[]{
				 "ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"
		 };
		 String ret = alienOrder(words);
		 System.out.println(ret);
	 }
	 
	 public static String alienOrder(String[] words) {
	        int[][] W = constructWeight(words);
	        int[] counter = constructCounter(words, W);
	        int total = 0;
	        for(int count: counter) if(count >= 0) total ++;
	        
	        int numNoPre = 0;
	        StringBuffer sb = new StringBuffer();
	        Queue<Integer> q = new LinkedList<Integer>();
	        //store nodes with no predecessors
	        for(int i = 0; i < 26; i++){
	            if(counter[i] == 0){
	                q.add(i);
	                sb.append((char)('a' + i));
	                numNoPre ++;
	            }
	        }
	        
	        while(!q.isEmpty()){
	            int top = q.poll();
	            for(int j = 0; j < 26; j++){
	                if(W[top][j] == 1){
	                    counter[j] --;
	                    if(counter[j] == 0){
	                        numNoPre ++;
	                        sb.append((char)('a' + j));
	                        q.offer(j);
	                    }
	                    
	                }
	            }
	        }
	        if(numNoPre != total) return "";
	        else return sb.toString();
	    }
	    
	    public  static int[][] constructWeight(String [] words){
	        int [][] W = new int[26][26];
	        for(int i = 1; i < words.length; i++){
	            String w1 = words[i-1];
	            String w2 = words[i];
	            for(int j = 0; j < w1.length() && j < w2.length(); j++){
	                if(w1.charAt(j) != w2.charAt(j)){
	                    W[w1.charAt(j)-'a'][w2.charAt(j)-'a'] = 1;    //small->big
	                    break;    
	                }
	            }
	        }
	        return W;
	    }
	    
	    public static int[] constructCounter(String []words, int [][]W){
	        //count predecessors
	        int [] counter = new int[26];
	        Arrays.fill(counter, -1);
	        for(String word : words){
	            for(int j = 0; j < word.length(); j++){
	                counter[word.charAt(j) -'a'] = 0;   
	            }
	        }
	        for(int i = 0; i < 26; i++){
	            for(int j = 0; j < 26; j++){
	                if(W[j][i] == 1) counter[i] ++;    
	            }
	        }
	        return counter;
	    }
}
