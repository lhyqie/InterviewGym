package recursion;

import java.util.Stack;

/**
 * @author howie
	You are given a square grid of size dxd. It contains each of the numbers 1..n, where n = d^2.
	The numbers each occur once and only once, in arbitrary order. 
	Find the length of the longest sequence of adjacent consecutive numbers in the grid.
	For example,
	d=3
	1 2 9
	5 3 8
	4 6 7
	The longest is [6, 7, 8, 9], which has length 4.
 */
public class LongestConsecutiveSequenceInMatrix {
	
	public static String longest = null;
	
	public static void main(String[] args) {
		
		int [][] M = {
				{1,2,9},
				{5,3,8},
				{4,6,7}
		};
		
		findLongestConsecutiveSequence(M);
	}
	
	private static void resetVisited(boolean [][]visited){
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	/*
	 * find the increasing consecutive sequence
	 */
	public static void findLongestConsecutiveSequence(int [][] M){
		longest = new String("");
		Stack<Integer> path = new Stack<Integer>();
		boolean [][] visited = new boolean[M.length][];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = new boolean[M[i].length];
		}
		for (int r = 0; r < M.length; r++) {
			for (int c = 0; c < M.length; c++) {
				resetVisited(visited);
				System.out.println("searching at position " + r +"," + c);
				search(r, c, M, visited, path);
			}
		}
		System.out.println("\n\n The longest increasing consecutive sequence in the matrix is : " + longest);
		
	}
	
	private static void search(int r, int c, int[][] M, boolean[][] visited, Stack<Integer> path){
		if(r < 0 || r>= M.length) return; //off board
		if(c < 0 || c>= M.length) return; //off board
		if(path.isEmpty()){
			path.add(M[r][c]);
			visited[r][c] = true;
		}
		else if(visited[r][c]) return;  // already visited
		else{
			if(M[r][c] == path.peek() + 1){  // next element
				path.add(M[r][c]);
				visited[r][c] = true;
			}else{
				return;
			}
		}
		
		System.out.println(path);
		if(path.size() > longest.length()) longest = path.toString();
		
		// search 4 directions
		search(r+1, c, M , visited, path);
		search(r, c+1, M , visited, path);
		search(r-1, c, M , visited, path);
		search(r, c-1, M , visited, path);
		
		visited[r][c] = false;
		path.pop();
	}
	
	
}
