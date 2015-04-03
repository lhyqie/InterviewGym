package recursion;

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
		int [] arr = {1,2,3,4};
		int number = allPossibleSequence(arr);
		System.out.println("There are totally " + number + " ways" );
	}
	
	/**
	 * print all possible sequences of elements in order of the time when they leave the stack
	 * @param arr : the sequence of elements
	 * @return the number of all possible sequences
	 */
	public static int allPossibleSequence(int []arr){
		
		return 0;
	}
	
}
