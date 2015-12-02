package recursion;

import java.util.Stack;

public class KsizeSubset {
	
	public static void main(String[] args) {
		findKsizeSubset(5, 2);
		System.out.println();
		findKsizeSubset(10, 1);
	}
	
	public static void findKsizeSubset(int n, int k){
		if( n <=0 || k <= 0 ) return;
		Stack<Integer> buffer = new Stack<Integer>();
		search(n, k, 0, buffer);
	}
	
	public static void search(int n, int k, int cur, Stack<Integer> buffer){
		if(cur == n){
			if(k == 0) System.out.println(buffer);
		}else{
			buffer.push(0);
			search(n, k, cur+1, buffer); // do not choose 
			buffer.pop();
			buffer.push(1);
			search(n, k-1, cur+1, buffer);
			buffer.pop();
		}
	}
}
