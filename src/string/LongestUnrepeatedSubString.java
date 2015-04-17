package string;

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
	public int lengthOfLongestSubstring(String s) {
        HashSet<Character> exist = new HashSet<Character>();
        int i = 0, maxLen = 0;
        for(int j = 0; j < s.length(); j++){
            while(exist.contains(s.charAt(j))){
                exist.remove(s.charAt(i));
                i++;
            }
            exist.add(s.charAt(j));
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
}
