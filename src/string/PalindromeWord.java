package string;

/*
 * a palindrome word is a word which has the property of reading the same forwards as it does backwards
 * for example:  the word racecar  is read "racecar" either from left to right or right to left
 * 
 * http://en.wiktionary.org/wiki/Appendix:English_palindromic_words
 * 
 * this version is case sensitive
 */
public class PalindromeWord {
	public static void main(String[] args) {
		String[] test_words = {"racecar", "repaper", "ABa", "AB", "ABC", "ABCA"};
		for (String w : test_words) {
			System.out.println("word = "+ w);
			System.out.println("isPalindromeWord(w) = " + isPalindromeWord(w));
			System.out.println();
		}
	}
	
	/**
	 * check if a word is palindrome (case sensitive)
	 * @param w
	 * @return true if w is palindrome otherwise 0
	 * Time Complexity O(n)
	 * Space Complexity O(1)
	 */
	public static boolean isPalindromeWord(String w){
		char[] chs = w.toCharArray();
		int i;
		for(i = 0; i < chs.length; i++){
			if(chs[i] != chs[chs.length-i-1]) return false;
		}
		return true;
	}
}
