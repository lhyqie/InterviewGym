package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
 * @author howie
 *
 */
public class KMP {
	
	public static void main(String[] args) {
		
//		String source = "ABC ABCDAB ABCDABCDABDE";
//		String word = "ABCDABD";
		
//		String source = "acabaabaabcacaabc";
//		String word = "abaabcac";
		
		String source = "String replace Example of replacing Character Sequence";
		String word = "re";
		
		System.out.println(find_naive(source, word));
		System.out.println(find(source, word));
		
		System.out.println(Arrays.toString(genNext(word)));
	
	}
	
	/**
	 * find positions of all occurrence of word in source
	 * @param source
	 * @param word
	 * @return
	 */
	public static List<Integer> find(String source, String pattern){
		List<Integer> ret = new ArrayList<Integer>();
		int[] next = genNext(pattern);
		int pos = -1;
		while((pos = index_KMP(source, pattern, pos+1, next)) != -1){
			ret.add(pos);
		}
		return ret;
	}
	
	/**
	 * find substring starting from position pos
	 * @param source : the string to search on
	 * @param pattern : the substring to find
	 * @param pos  : starting position
	 * @param next : the array "next" generated for KMP search
	 * @return
	 */
	public static int index_KMP(String source, String pattern, int pos, int[] next){
		int i = pos; int j = 0;
		while( i < source.length() && j < pattern.length()){
			if( j == -1 || source.charAt(i) == pattern.charAt(j) ){
				i++;
				j++;
			}else{
				j = next[j];
			}
			if(j >= pattern.length()) 
				return i-pattern.length();  //find a substring match
		}
		return -1;
	}
	
	/**
	 * @param pattern : the pattern string
	 * @return the array of indices indicating the positions that pointer to the pattern
	 * string should move to if a mismatch is found between pattern string and source string
	 * 
	 * Next[j] =  -1  if  j == 0
	 *            else max{ k |  pattern[0...k-1] == pattern[j-k...j-1]}   
	 *            which is  the maximal length k 
	 *                  such that the first k chars equal to the last k chars of pattern[0...j-1]  
	 */
	public static int[] genNext(String pattern){
		int [] next = new int[pattern.length()];
		int i = 0;
		next[0] = -1;
		int j = -1;
		while(i < pattern.length() - 1){
			if(j == -1 || pattern.charAt(i) == pattern.charAt(j)){
				i++; 
				j++;
				next[i] = j;
			}
			else{
				j = next[j];
			}
		}
		return next;
	}
	
	/**
	 * naive approach to find positions of all occurrence of word in source 
	 * @param source
	 * @param word
	 * @return
	 */
	public static List<Integer> find_naive(String source, String pattern){
		List<Integer> ret = new ArrayList<Integer>();
		char[] a = source.toCharArray();
		char[] b = pattern.toCharArray();
		for (int i = 0; i < a.length; i++) {
			int j = 0;
			for (; i+j < a.length && j < b.length; j++) {
				if(a[i+j] != b[j]) break;
			}
			if( j == b.length){  // word occurs at source @ position i
				ret.add(i);
			}
		}
		return ret;
	}
}
