package string;

import java.util.List;

import utils.FileUtils;

public class PalindromeSentence {
	
	public static void main(String[] args) {
		List<String> palindrome_sents = FileUtils.readLines("data/palindrome_sents.txt");
		for (String sent : palindrome_sents) {
			System.out.println("sent = "+ sent);
			System.out.println("isPalindromeSentence() = " + isPalindromeSentence(sent));
			System.out.println();
		}
	}
	
	/**
	 * Check if a sentence is a palindrome sentence (case insensitive, punctuation ignored) 
	 * <p>
	 * For example:
	 *     "A Toyota." without punctuation and lowercased is "atoyota" which is a palindrom word
	 * Use the following built-in String method as well as string.PalindromWord() to save some efforts
	 * <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#toLowerCase()">
	 * http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#toLowerCase()</a>
	 * <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#replaceAll(java.lang.String,%20java.lang.String)">
	 * http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#replaceAll(java.lang.String,%20java.lang.String)
	 * </a>
	 * <p>
	 * Also refer to use of regular expression in the second post of the thread in stackoverflow<br>
	 * <a href="http://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java">
	 * http://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java</a>
	 * @param sent : a string as a sentence containing punctuation
	 * @return true if sent is a palindrome sentence otherwise false <br>
	
	 */
	public static boolean isPalindromeSentence(String sent) {
		return false;
	}
	
}
