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
			System.out.println(String.format("word = %8s, isAnagramPalindrome() = %s",
					word, isAnagramPalindrome(word)));
		}
	}
	
	/**
	 * Check if any anagram of a word is a palindrome
	 * Best approach: Are there any special property of a word whose anagram is palindrome
	 *                or think like this: given a set of characters (with possible duplicates), how to construct a palindrome
	 * @param word
	 * @return true if any anagram of the word is a palindrome
	 * 
	 * Hit:  compute the counts for each letter.
	 *       if there are no odd counts or only one odd count for all letters, one of the anagram of the word is palindrome
	 */
	public static boolean isAnagramPalindrome(String word){
		int[] counts = new int[256];
		for (char c : word.toCharArray()) {
			counts[c] ++; 
		}
		int odd_cnt = 0;
		for (int i = 0; i < 256; i++) {
			if(counts[i] % 2 != 0) odd_cnt ++;
		}
		if(odd_cnt <= 1) return true;
		else return false;
	}
}
