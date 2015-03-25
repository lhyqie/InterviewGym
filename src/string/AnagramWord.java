package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import com.sun.javafx.collections.MappingChange.Map;

import static utils.ArrayUtils.print ;
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
				System.out.println(String.format("word1 = %7s  \t word2 = %7s \t isAnagram1(word1, word2) = %s \t isAnagram2(word1, word2) = %s, isAnagram3(word1, word2) = %s", 
						words[i], words[j],  isAnagram1(words[i], words[j]), isAnagram2(words[i], words[j]), isAnagram3(words[i], words[j])));
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
		char[] ch1 = word1.toCharArray();
		char[] ch2 = word2.toCharArray();
		if(ch1.length != ch2.length) return false;
		Arrays.sort(ch1);
		Arrays.sort(ch2);
		//print(ch1);
		//print(ch2);
		for (int i = 0; i < ch1.length; i++) {
			if(ch1[i] != ch2[i]) return false;
		}
		return true;
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
		char[] ch1 = word1.toCharArray();
		char[] ch2 = word2.toCharArray();
		if(ch1.length != ch2.length) return false;
		int[] counts = new int[256];
		for (int i = 0; i < ch1.length; i++){ 
			counts[ch1[i]] ++ ;
		}
		for (int i = 0; i < ch2.length; i++) {
			counts[ch2[i]] -- ;
			if(counts[ch2[i]] < 0 ){
				return false;  //early detection of unequal counts
			}
		}
		for (int i = 0; i < 256; i++) {
			if(counts[i] != 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Considering only anagram words, check if two words are anagrams
	 * without assuming ASCII characters, use HashMap as counter 
	 * @param word1
	 * @param word2
	 * @return true is they are anagrams if they have the identical counts for each letter
	 */
	public static boolean isAnagram3(String word1, String word2) {
		char[] ch1 = word1.toCharArray();
		char[] ch2 = word2.toCharArray();
		if(ch1.length != ch2.length) return false;
		
		HashMap<Character, Integer> counter = new HashMap<Character, Integer>();
		for (int i = 0; i < ch1.length; i++){
			int cnt = counter.get(ch1[i]) == null ? 1 : counter.get(ch1[i]) + 1;
			counter.put(ch1[i], cnt);
		}
		
		for (int i = 0; i < ch2.length; i++) {
			if(counter.get(ch2[i]) == null){
				return false;
			}else{
				int cnt = counter.get(ch2[i]) - 1;
				if( cnt < 0){
					return false;
				}else{
					counter.put(ch2[i], cnt);
				}
				
			}
		}
		for (Entry<Character, Integer> entry : counter.entrySet()) {
			if(entry.getValue() != 0){
				return false;
			}
		}
		
		return true;
	}
	
}
