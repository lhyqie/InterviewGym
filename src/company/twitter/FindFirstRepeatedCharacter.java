package company.twitter;

import java.util.HashMap;

public class FindFirstRepeatedCharacter {
	public static void main(String[] args) {
	
		System.out.println(findFirstRepeatedChar("abcba"));
	}
	
	public static int findFirstRepeatedChar(String s){
		int minIndex = Integer.MAX_VALUE;
		HashMap<Character, Integer> ch2index = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(ch2index.containsKey(c)) {
				minIndex = Math.min(minIndex, ch2index.get(c));
			}
			else ch2index.put(c, i);
		}
		if(minIndex == Integer.MAX_VALUE) return -1;
		return minIndex;
	}
}
