package string;

import java.util.HashMap;
import java.util.HashSet;

public class LongestUnrepeatedSubString {
	//refer to array.LongestUniqueSubArray
	
	// using boolean array
	/*  O(2n) -> O(n)
    public int lengthOfLongestSubstring(String s) {
        boolean[] exist = new boolean[256];
        int i = 0, maxLen = 0;
        for(int j = 0; j < s.length(); j++){
            while(exist[s.charAt(j)]){
                exist[s.charAt(i)] = false;
                i++;
            }
            exist[s.charAt(j)] = true;
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
    */
	
	//using Hashset
//	public int lengthOfLongestSubstring(String s) {
//        HashSet<Character> exist = new HashSet<Character>();
//        int i = 0, maxLen = 0;
//        for(int j = 0; j < s.length(); j++){
//            while(exist.contains(s.charAt(j))){
//                exist.remove(s.charAt(i));
//                i++;
//            }
//            exist.add(s.charAt(j));
//            maxLen = Math.max(maxLen, j - i + 1);
//        }
//        return maxLen;
//    }
	
	public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> char2index = new HashMap<Character, Integer>();
        char chs[] = s.toCharArray();
        int start = 0;
        int maxLen = 0;
        for(int i = 0; i < chs.length; i++){
            if(char2index.containsKey(chs[i]) && char2index.get(chs[i]) >= start){
        	    start = char2index.get(chs[i])  + 1;                //important
            }
            char2index.put(chs[i], i); //override if previously exist
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }
	
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("abba"));
	}
}
