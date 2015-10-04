package string;

import java.util.*;

public class MinimumWindowSubstring {
	
	public static void main(String[] args) {
		System.out.println(minWindowII("abcd", "ac"));
		//System.out.println(minWindowII("adfqeradboaf23098724huhfda923hadf78adfhadfhadfaodiyfas8", "dfje89affefy8f"));
		//System.out.println(minWindowII("abc", "ac"));
		//System.out.println(minWindow("75902135791158897", "159"));
		//System.out.println(minWindowII("acbbaca", "aba"));
	}
	
	public static String minWindowII(String source, String target) {
		int need[] = new int[256];
        int found[] = new int[256];
        if(source == null || target == null || source.length() < target.length()) return "";
        if(source.length() == 0) return "";
        // calulate need
        for(int i = 0; i < target.length(); i++){
            need[target.charAt(i)] ++ ;
        }
        
        // sliding window
        int minStart = 0, minEnd = source.length();
        int start = 0, end = 0;
        int nCovered = 0;
        
        for(; end < source.length(); end ++){
            // looping invaraint : [start, end) contains target
    
            // if we need more,  move end
            while(end < source.length() && nCovered < target.length()){
                char endCh = source.charAt(end);
                if(found[endCh] < need[endCh]) nCovered++;
                found[endCh] ++;
                end++;
            }
            if(end == source.length() && nCovered < target.length() ) return "";  // s can not cover t
            
            char startCh = source.charAt(start);
            // if we cover too much, move start
            while(need[startCh] == 0 || found[startCh] > need[startCh]) {
                found[startCh]--;
                start ++;
                startCh = source.charAt(start);
            }
            
            // now nCovered == target.length()  and start can not move any further 
            // because found[startCh] == need[startch]
            if(end - start < minEnd - minStart){
                minEnd = end;
                minStart = start;
            }
            
        }
        if(end - start < minEnd - minStart){
                minEnd = end;
                minStart = start;
        }
        
        return source.substring(minStart, minEnd);
    }
	
	/*
	//http://articles.leetcode.com/2010/11/finding-minimum-window-in-s-which.html
	// there maybe duplicate in t
	// for example s = "a",  t = "aa", there is no such window 
	public static String minWindowII(String s, String t) {
		int n1 = s.length();
		int n2 = t.length();
		if(n1 < n2) return "";
		
		int needToFind[] = new int[256];
		int hasFound[] = new int[256];
		
		// count chars in t
		for(int j = 0; j < n2; j++){
			needToFind[t.charAt(j)]++;
		}
		int count = 0; //count how many chars in t are covered
		int minStart = 0, minEnd = n1;
		for(int start = 0, end = 0; end < n1; end++){
			char ch = s.charAt(end);
			// find the first substring of s[start:end] that covers t
			if(needToFind[ch] == 0) continue;
			if(hasFound[ch] < needToFind[ch]) count ++;
			hasFound[ch] ++ ;
			
			//window constraint is satisfied
			if(count == n2){
				//advance start as far right as possible
				//stop when window constraint is violated
				while(needToFind[s.charAt(start)] == 0 || 
				    hasFound[s.charAt(start)] > needToFind[s.charAt(start)]){
					if(hasFound[s.charAt(start)] > needToFind[s.charAt(start)]){
						hasFound[s.charAt(start)] -- ;
					}
					start ++;	
				}
				
				// keep track of min
				if(minEnd - minStart > end - start){
					minEnd = end;
					minStart = start;
				}
			}
		}
		
		return count == n2 ? s.substring(minStart, minEnd + 1) : "";
	}
	*/
	
	// assume no duplicate in t
	public static String minWindow(String s, String t) {
        
        HashMap<Character, List<Integer>> c2ind = new HashMap<Character, List<Integer>> ();
        for(int j = 0; j < t.length(); j++){
            char ch = t.charAt(j);
            if(!c2ind.containsKey(ch)){
                c2ind.put(ch, new ArrayList<Integer>());   
            }
        }
        
        // build index list for each ch in t
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(c2ind.containsKey(ch)){
                c2ind.get(ch).add(i);
            }
        }
        

        boolean finished = false;
        int start = -1, end = 0; 
        while(true){
            int minIndex = Integer.MAX_VALUE;
            int maxIndex = Integer.MIN_VALUE;
            char minChar = ' ';
            for(Map.Entry<Character, List<Integer>> entry : c2ind.entrySet()){
                char ch = entry.getKey();
                List<Integer> indexList = entry.getValue();
                if(indexList.size() == 0) {
                    finished = true;
                    break;
                }
                if(minIndex > indexList.get(0)) {
                    minIndex = indexList.get(0);
                    minChar = ch;
                }
                if(maxIndex < indexList.get(0)){
                    maxIndex = indexList.get(0);
                }
            }
            if(finished) break;
            
//            System.out.println(""+minIndex+","+maxIndex +"   " + start +"," + end);
//            System.out.println(c2ind);
//            System.out.println();
            
            if(start == -1 || (maxIndex - minIndex) < end - start){
                start = minIndex;
                end = maxIndex;
            }
            // throw a char from the list picked
            c2ind.get(minChar).remove(0);
            
        }
        
        if(start == -1) return "";
        else return s.substring(start, end + 1);
        
    }
}
