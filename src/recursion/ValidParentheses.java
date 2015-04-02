package recursion;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class ValidParentheses {

	public static void main(String[] args) {
		int number = printValidParentheses(3);
		System.out.println("There are totally " + number + " ways" );
	}
	
	/**
	 * print all all possible valid parentheses for n ('s and n )'s
	 * @param n : n pairs of ('s and )'s
	 * @return the number of all possible valid parentheses
	 * This number is Catalan number =  Choose(2n, n) / (n+1)
	 */
	public static int printValidParentheses(int n){
		Stack<Character> s = new Stack<Character>();
		AtomicInteger counter = new AtomicInteger(0);
		helper(s, counter, n, n);
		return counter.intValue();
	}
	
	/**
	 * recursive helper function that prints all all possible valid parentheses for n ('s and n )'s 
	 * @param s :  buffer stack storing the current search path
	 * @param counter : count the total number of solutions
	 * @param left : the remaining ('s
	 * @param right : the remaining )'s
	 * Note left <= right should be maintained
	 */
	private static void helper(Stack<Character> s, AtomicInteger counter, int left, int right){
		if(left < 0 || right < 0) return ; // this should not happen
		if(left > right) return ; // this should not happen
		//here implies 0 <= left <= right
		
		if(right == 0) {          // this implies 0 == left == right
			counter.incrementAndGet();
			System.out.println(s); // one search path is complete 
		}
		
		// begin to explore different search options
		
		if(left == right){  // 0 <= left == right , we can only use (
			s.push('(');
			helper(s, counter, left - 1, right);
			s.pop();
		}else{              // 0 <= left < right, we can either use ( or )
			s.push('(');	//first try (
			helper(s, counter, left - 1, right);    
			s.pop();		//cancel the first try
			s.push(')');    //then  try )   [there are independent]
			helper(s, counter, left, right - 1);    
			s.pop();		//cancel the second try
		}
	}

}
