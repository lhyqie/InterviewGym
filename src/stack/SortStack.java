package stack;

import java.util.Stack;

/*
 * sort stack without using other data structure
 * 
 * use another stack is OK, similar to insert sort
 */
public class SortStack {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(4);
		s.push(5);
		s.push(1);
		s.push(3);
		s.push(2);
		s.push(2);
		
		s = sort(s);
		System.out.println(s);
	}
	
	public static Stack<Integer> sort(Stack<Integer> s){
		Stack<Integer> r = new Stack<Integer>();
		while(!s.isEmpty()){
			int e = s.pop();
			if(r.isEmpty() || r.peek() < e){
				r.push(e);
			}else{
				while( !r.isEmpty() && r.peek() > e){
					s.push(r.pop());
				}
				r.push(e);
			}
		}
		return r;
	}
}
