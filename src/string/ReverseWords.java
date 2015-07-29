package string;

import java.util.Stack;

public class ReverseWords {
	public static String reverseWords(String s) {
	    char[] chs = s.toCharArray();
	    int start = 0, end = 0;
	    Stack<Character> stack = new Stack<Character>();
	    while(start < chs.length){
	        //while(start < chs.length && !Character.isLetterOrDigit(chs[start]) ) start++; //get to the first non-space character
	    	while(start < chs.length && chs[start] == ' ' ) start++; //get to the first non-space character
	        end = start + 1;
	        while(end < chs.length && chs[end] != ' ') end++;   
	        //copy
	        if(start < chs.length){
	        	for(int i = end -1 ; i >= start; i--) stack.push(chs[i]);
	        }
	        stack.push(' ');
	        start = end;
	    }
	    StringBuffer sb = new StringBuffer();
	    while(!stack.isEmpty()) sb.append(stack.pop());
	    return sb.toString().trim();
	}

	private static void reverse(char[] chs, int low, int high){
	    while(low < high){
	        char t = chs[low];
	        chs[low] = chs[high];
	        chs[high] = t;
	    
	        low ++; high--;
	    }
	}
	
	public static void main(String [] args){
		System.out.println(reverseWords("hi!"));
		System.out.println(reverseWords(" ab  ba  12  34 134"));
	}
}


