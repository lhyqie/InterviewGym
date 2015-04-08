package stack;

import java.util.Stack;

/**
 * Implement a queue with two stacks
 */
public class TwoStackQueue<T> {
	Stack<T> s1 = new Stack<T>(); // for offering
	Stack<T> s2 = new Stack<T>(); // for polling
	
	public static void main(String[] args) {
		TwoStackQueue<Integer> q = new TwoStackQueue<Integer>();
		q.offer(1);
		q.offer(2);
		q.offer(3);
		System.out.println(q);
		System.out.println(q.peak());
		System.out.println(q);
		System.out.println(q.poll());
		System.out.println(q);
		q.offer(4);
		q.offer(5);
		System.out.println(q);
		q.poll();
		System.out.println(q);
		q.poll();
		System.out.println(q);
		q.poll();
		System.out.println(q);
		q.poll();
		System.out.println(q);
	}
	
	public String toString(){
		return s1.toString() + " \t " +s2.toString();
	}
	public T peak(){ 
		if(!s2.isEmpty()) return s2.peek();
		if(s1.isEmpty()) return null;
		while(!s1.isEmpty())
			s2.push(s1.pop());
		return s2.peek();
	}
	
	public boolean offer(T e){
		s1.push(e);
		return true;
	}
	public T poll(){
		if(!s2.isEmpty()) return s2.pop();
		if(s1.isEmpty()) return null;
		while(!s1.isEmpty())
			s2.push(s1.pop());
		return s2.pop();
	}
	
}
