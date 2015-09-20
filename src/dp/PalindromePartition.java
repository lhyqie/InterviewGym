package dp;

import java.util.*;

public class PalindromePartition {
	
	public static void main(String[] args) {
		//System.out.println(palindromePartitioning("aab"));
		System.out.println(partition("aab"));
	}
	
	
	public static ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (s == null || s.length() == 0) return result;
		Stack<String> partition = new Stack<String>();//track each possible partition
		addPalindrome(s, 0, partition, result);
		return result;
	}
	 
	private static void addPalindrome(String s, int start, Stack<String> partition,
			ArrayList<ArrayList<String>> result) {
		//stop condition
		if (start == s.length()) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}
	 
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				partition.push(str); 
				addPalindrome(s, i, partition, result);
				partition.pop();
			}
		}
	}
	 
	private static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++; right--;
		}
		return true;
	}
	
	public static List<String> palindromePartitioning(String s) {
		 
		List<String> result = new ArrayList<String>();
	 
		if (s == null)
			return result;
	 
		if (s.length() <= 1) {
			result.add(s);
			return result;
		}
	 
		int length = s.length();
	 
		int[][] table = new int[length][length];
	 
		// l is length, i is index of left boundary, j is index of right boundary
		for (int l = 1; l <= length; l++) {
			for (int i = 0; i <= length - l; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					if (l == 1 || l == 2) {
						table[i][j] = 1;
					} else {
						table[i][j] = table[i + 1][j - 1];
					}
					if (table[i][j] == 1) {
						result.add(s.substring(i, j + 1));
					}
				} else {
					table[i][j] = 0;
				}
			}
		}
	 
		return result;
	}
}
