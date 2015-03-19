package string;

/**
 *  This is an interview question with Yelp
 *  Check if any anagram of a word is a palindrome
 *  for example:
 *  	word "aaaad" has an anagram "aadaa" which is a palindrome
 */
public class AnagramPalindrome {
	public static void main(String[] args) {
		String [] words = {"ad", "add", "aad", "aaad", "addd", "abcd", "abbbddc", "aaaad"};
		for (String word : words) {
			System.out.println(String.format("word = %8s, isAnagramPalindromeNaive() = %s, isAnagramPalindromeBest() = %s",
					word, isAnagramPalindromeNaive(word), isAnagramPalindromeBest(word)));
		}
	}
	
	/**
	 * Check if any anagram of a word is a palindrome
	 * Naive approach: find all anagrams, check each of them 
	 * @param word
	 * @return true if any anagram of the word is a palindrome
	 */
	public static boolean isAnagramPalindromeNaive(String word){
		
		return false;
	}
	
	/**
	 * Check if any anagram of a word is a palindrome
	 * Best approach: Are there any special property of a word whose anagram is palindrome
	 *                or think like this: given a set of characters (with possible duplicates), how to construct a palindrome
	 * @param word
	 * @return true if any anagram of the word is a palindrome
	 */
	public static boolean isAnagramPalindromeBest(String word){
		
		return false;
	}
}
