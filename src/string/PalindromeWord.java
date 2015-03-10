package string;

/*
 * a palindrome word is word which has the property of reading the same forwards as it does backwards
 * for example:  the word racecar  is read "racecar" either from left to right or right to left
 * 
 * http://en.wiktionary.org/wiki/Appendix:English_palindromic_words
 * 
 */
public class PalindromeWord {
	public static void main(String[] args) {
		String[] test_words = {"racecar", "repaper", "ABA", "AB", "ABC", "ABCA"};
		for (String w : test_words) {
			System.out.println("word = "+ w);
			System.out.println("isPalindromeWord(w) = " + isPalindromeWord(w));
			System.out.println();
		}
	}
	
	/**
	 * @param w
	 * @return true if w is palindrome otherwise 0
	 * Time Complexity O(n)
	 * Space Complexity O(1)
	 */
	public static boolean isPalindromeWord(String w){
		char[] chs = w.toCharArray();
		
		return false;
	}
}
