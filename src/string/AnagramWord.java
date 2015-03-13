package string;

/**
 * 
 * Anagram is a word or phrase formed by rearranging the letters of another word or phrase
 * http://wordsmith.org/anagram/
 * for example: animal, manila, lamina are anagrams to each other
 */
public class AnagramWord {
	
	public static void main(String[] args) {
		String[] words = {"animal", "manila", "lamina", "limes", "miles", "smile", "slime"}; 
		for (int i = 0; i < words.length; i++) {
			for (int j = i+1; j < words.length; j++) {
				System.out.println(String.format("word1 = %7s  \t word2 = %7s \t isAnagram1(word1, word2) = %s \t isAnagram2(word1, word2) = %s", 
						words[i], words[j],  isAnagram1(words[i], words[j]), isAnagram2(words[i], words[j])));
			}
		}
		
	}
	

	/**
	 * Considering only anagram words, check if two words are anagrams
	 * Idea: If two words are anagram, they should be the same after their letters sorted 
	 * Time Complexity: O(nlogn)  because sorting is O(nlogn) comparing is O(n). so overall is O(nlogn)
	 * @param word1
	 * @param word2
	 * @return true is they are anagrams else false
	 * Reference: http://stackoverflow.com/questions/605891/sort-a-single-string-in-java
	 */
	public static boolean isAnagram1(String word1, String word2){
		
		return false;
	}
	
	/**
	 * Considering only anagram words, check if two words are anagrams
	 * Idea: assuming letter in words are ASCII characters  http://www.asciitable.com/
	 *       use an integer array of size 256 to hold counts for each 256 letters 
	 * Time Complexity: O(n)  Counting is O(n)  comparing is O(n) thus overall is O(n)
	 * @param word1
	 * @param word2
	 * @return true is they are anagrams if they have the identical counts for each letter
	 */
	public static boolean isAnagram2(String word1, String word2) {
		
		return false;
	}
	
	/**
	 * Considering only anagram words, check if two words are anagrams
	 * without assuming ASCII characters, use HashTable as counter 
	 * @param word1
	 * @param word2
	 * @return true is they are anagrams if they have the identical counts for each letter
	 */
	/*public static Object isAnagram3(String word1, String word2) {
		
		return null;
	}
	*/
}
