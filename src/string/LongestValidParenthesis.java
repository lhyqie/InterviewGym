package string;

import java.util.*;

public class LongestValidParenthesis {
	
	public static int longestValidParentheses(String s) {
        
        // for example s        ())()())
        //             flag     1 -1 0 1 -1 1 -1 -1
        //             find the longest non-zero sequence
        
        int flag[] = new int[s.length()];
        // flag[i] == 0  means   invalid,
        // flag[i] == 1  means   valid '(' 
        // flag[i] == -1 means   valid or ')'
        
        Stack<Integer> stack = new Stack<Integer>(); //storages the index of '('
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                stack.push(i);
            }else{ // ch == ')'
                if(!stack.isEmpty()) {
                    flag[stack.pop()] = 1 ;
                    flag[i] = -1;
                }
            }
        }
        
        //find the longest non-zero sequence
        int maxLen = 0;
        int start = 0;
        int end = 0;
        while(true){
            while(start < flag.length && flag[start] == 0) start ++;
            if(start == flag.length) break;
            end = start;
            while(end < flag.length && flag[end] != 0) end ++;
            maxLen = Math.max(maxLen, end-start);
            start = end;
            if(end == flag.length) break;
        }
        return maxLen;
    }
	
	public static void main(String[] args) {
		
		System.out.println(longestValidParentheses("())"));
	}
}
