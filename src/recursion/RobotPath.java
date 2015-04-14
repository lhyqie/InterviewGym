package recursion;

import java.util.Stack;

public class RobotPath {

	/*
	 *  (m+1) x (n+1) matrix
	 *  
	 *  x y is the postion
	 *  
	 *  robot can only go down or go right
	 *  
	 *  the # of paths is C(m+n,n)
	 */
	
	public static void printPath(int m, int n , int x, int y, Stack<String> paths){
		if(x > m || y > n) return;
		if(x == m && y == n){
			for (String direction : paths) {
				System.out.print(direction + " ");
			}
			System.out.println();
		}else{
			if(x < m){
				paths.push("->");
				printPath(m, n, x+1 , y, paths);
				paths.pop();
			}
			if(y < n){
				paths.push("¡ý");
				printPath(m, n, x , y + 1, paths);
				paths.pop();
			}
		}
	}
	
	public static void main(String[] args) {
		printPath(3,3,0,0,new Stack<String>());
	}
}
