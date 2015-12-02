package string;

import java.util.*;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. 
 * Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].
 *
 */
public class AnagramPalindromeII {
	
	public static void main(String[] args) {
		
		List<String> res = null;
		
		res = generatePalindromes("aabb");
		for (String row : res) {
			System.out.println(row);
		}
		System.out.println("---------------------------");
		
		res = generatePalindromes("abc");
		for (String row : res) {
			System.out.println(row);
		}
		System.out.println("---------------------------");
		res = generatePalindromes("aaaaa");
		for (String row : res) {
			System.out.println(row);
		}
		
		System.out.println("---------------------------");
		res = generatePalindromes("122a221");
		for (String row : res) {
			System.out.println(row);
		}
		
		
	}
	
	public static List<String> generatePalindromes(String s) {
        
        List<String> res = new ArrayList<String>();
         
        HashMap<Character, Integer> ch2cnt = new HashMap<Character, Integer>();
        for(char ch : s.toCharArray()){
            ch2cnt.put(ch, ch2cnt.get(ch) == null ? 1: ch2cnt.get(ch) + 1);
        }
        int odd_cnt = 0;
        Character oddNumberedChar = null;
        List<Character> candidateChars = new ArrayList<Character>();
        for(Map.Entry<Character,Integer> entry : ch2cnt.entrySet()){
            char ch = entry.getKey();
            int cnt = entry.getValue();
            if(cnt % 2 != 0) {
                odd_cnt ++;
                oddNumberedChar = ch;
            }
            while(cnt >= 2){
            	cnt -=2;
            	candidateChars.add(ch);
            }
            
        }
        System.out.println(candidateChars);
        char[] chs = new char[candidateChars.size()];
        for(int i = 0; i < candidateChars.size(); i++) chs[i] = candidateChars.get(i);
        
        if(odd_cnt > 1) return new ArrayList<String>(); 
        
        List<List<Character>> allP = permute(chs);
        for(List<Character> aper : allP){
            StringBuffer sb = new StringBuffer();
            for(char ch: aper) sb.append(ch);
            if(odd_cnt == 1){
                res.add(sb.toString() + oddNumberedChar + sb.reverse().toString());
            }else{
            	res.add(sb.toString() + sb.reverse().toString());
            }
        }
        return res;   
    }
    
    
    public static List<List<Character>> permute(char[] chs) {
		List<List<Character>> res = new ArrayList<List<Character>>();
		search(res, chs, 0);
		return res;
    }
    
    // do not allow duplicate
    public static void search(List<List<Character>> res, char[] chs, int k){
        if(k == chs.length) {
        	List<Character> row = new ArrayList<Character>();
            for (char ch : chs) {row.add(ch);}
            res.add(row); 
            return;
        }
        for(int j = k; j < chs.length; j++){
        	if(!canSwap(chs, k, j)) continue;  // no dup
            swap(chs, k, j);             //swap with chs after it at index j
            search(res, chs, k+1);
            swap(chs, k, j);			 //undo the swap
        }
    }
    
    private static void swap(char[] chs, int i, int j){
        char t = chs[i];
        chs[i] = chs[j];
        chs[j] = t;
    }
    
    private static boolean canSwap(char[] arr, int k, int j) {
        for (int i = k; i < j; i++) {
            if (arr[i] == arr[j]) {
                return false;
            }
        }
        return true;
    }
}
