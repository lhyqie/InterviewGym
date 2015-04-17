package recursion;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * given a sequence of elements 
 * their time for entering the stack is the order in the sequence
 * but their time leaving the stack can be different
 * 
 * give all possible output sequences
 * 
 * hint: refer to ValidParentheses  ( is equivalent to push  and  ) is equivalent to pop
 */
public class StackPushPopSequence {

	public static void main(String[] args) {
		int [] arr = {1,2,3};
		int number = allPossibleSequence(arr);
		System.out.println("There are totally " + number + " ways" );
	}
	
	/**
	 * print all possible sequences of elements in order of the time when they leave the stack
	 * @param arr : the sequence of elements
	 * @return the number of all possible sequences
	 * 
	 * another approach is similar to ValidParentheses.
	 * treat ( as push, ) as pop
	 *  
	 */
	public static int allPossibleSequence(int []arr){
		Stack<Integer> s = new Stack<Integer>();
		Stack<Integer> path = new Stack<Integer>();
		AtomicInteger counter=new AtomicInteger(0);
		helper(arr, 0, s, path, counter);
		return counter.intValue();
	}

	private static void helper(int[] arr, int pos, Stack<Integer> s, Stack<Integer> path, AtomicInteger counter) {
		if(path.size() == arr.length){
			System.out.println(path);
			counter.incrementAndGet();
		}else if(pos == arr.length){
			path.add(s.pop());
			helper(arr, pos, s, path, counter);
			path.pop();
		}else{
			if(s.isEmpty()){
				Stack<Integer> copy = (Stack<Integer>) s.clone();
				s.push(arr[pos]);
				helper(arr, pos + 1, s, path, counter);
				s = copy;
			}else{
				Stack<Integer> copy = ((Stack<Integer>) s.clone());
				s.push(arr[pos]);
				helper(arr, pos + 1, s, path, counter);
				s = copy;
				
				path.add(s.pop());
				helper(arr, pos, s, path, counter);
				path.pop();
			}
		}
	}
}
