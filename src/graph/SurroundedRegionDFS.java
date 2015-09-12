package graph;

/*
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

 */


// this implementation is valid but stack overflow due to too manys recursions
public class SurroundedRegionDFS {
	
	public static void print(char [] arr){
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if(i == arr.length - 1){
				System.out.println("]");
			}else{
				System.out.print(", ");
			}
		}
	}
	
	public static void print(char[][] arr){
		for (int i = 0; i < arr.length; i++) {
			print(arr[i]);
		}
	}
	public static void main(String[] args) {
//		char[][] board = {
//							{'X','X','X','X'},
//							{'X','O','O','X'},
//							{'X','X','O','X'},
//							{'X','O','X','X'}
//						 };
		
		char[][] board = {{'O','O'}, {'O','O'}};
		
		print(board);
		System.out.println();
		solve(board);
		print(board);
		/*
		int [][] sets = {{0}, {1}, {2, 3, 9}, {5, 6}, {7}, {4, 8}};
		int n = 10;
		
		UnionFind uf = new UnionFind(n);
		
		for (int[] set : sets) {
			for (int j = 1; j < set.length; j++) {
				uf.unite(set[j-1], set[j]);
			}
		}

		for (int i = 0; i < uf.getSize(); i++) {
			for (int j = i+1; j < uf.getSize(); j++) {
				if(uf.find(i, j))
					System.out.println(""+ i +" and " + j + " are in the same set");
			}
		}
		*/
		
	}
	
	
	public static void solve(char[][] board) {
		if(board == null || board.length == 0) return;
		int m = board.length;
		int n = board[0].length;
		boolean visited[][] = new boolean[m][n];
		UnionFind uf = new UnionFind(m * n + 1);  //special one as the super root
		for(int c = 0;  c < n; c++){
			if(board[0][c] == 'O'){
				uf.unite(m*n, toId(n, 0, c));
				DFS(board, visited, uf, 0, c, m, n);    // top row
			}
			if(board[m-1][c] == 'O'){
				uf.unite(m*n, toId(n, m-1, c));
				DFS(board, visited, uf, m-1, c, m, n);  // bottom row
			}
			
		}
		for(int r = 0;  r < m; r++){
			if(board[r][0] == 'O'){
				uf.unite(m*n, toId(n, r, 0));
				DFS(board, visited, uf, r, 0, m, n);	   // left col
				
			}
			if(board[r][n-1] == 'O'){
				uf.unite(m*n, toId(n, r, n-1));
				DFS(board, visited, uf, r, n-1, m, n);  // right col
			}
		}
		
		for (int i = 0; i < uf.getSize(); i++) {
			for (int j = i+1; j < uf.getSize(); j++) {
				if(uf.find(i, j))
					System.out.println(""+ i +" and " + j + " are in the same set");
			}
		}
		
		for(int r = 0; r < m; r++){
			for(int c = 0; c < n; c ++){
				int id = toId(n, r, c);
				if(!uf.find(m*n, id) && board[r][c] == 'O'){
					board[r][c] = 'X';
				}
			}
		}
    }
	
	private static void DFS(char[][] board, boolean[][] visited, UnionFind uf, int r, int c, int m, int n){
		if(visited[r][c]) return;
		visited[r][c] = true;
		int id1 = toId(n, r, c);
		// go up
		if(r-1 >= 0 && board[r-1][c] == 'O'){
			int id2 = toId(n, r-1, c);
			uf.unite(id1, id2);
			DFS(board, visited, uf, r-1, c, m, n);
		}
		// go down
		if(r+1 < m && board[r+1][c] == 'O'){
			int id2 = toId(n, r+1, c);
			uf.unite(id1, id2);
			DFS(board, visited, uf, r+1, c, m, n);
		}
		// go left
		if(c-1 >= 0 && board[r][c-1] == 'O'){
			int id2 = toId(n, r, c-1);
			uf.unite(id1, id2);
			DFS(board, visited,  uf, r, c-1, m, n);
		}
		// go right
		if(c+1 < n  && board[r][c+1] == 'O'){
			int id2 = toId(n, r, c+1);
			uf.unite(id1, id2);
			DFS(board, visited, uf, r, c+1, m, n);
		}

	}
	
	
	private static int toId(int n , int r, int c){
		return r * n + c;
	}
	
	
	
	static class UnionFind{
		int n;
		int [] par = null;
		public UnionFind(int n){
			this.n = n;
			par = new int[n];
			for(int i = 0; i < n; i++) par[i] = i;
		}
		
		public boolean find(int i, int j){
			return root(i) == root(j);
		}
		
		public void unite(int i, int j){
			int root_i = root(i);
			int root_j = root(j);
			par[root_j] = root_i;
		}
		
		public int root(int i){
			while(par[i] != i){
				i = par[i];
			}
			return i;
		}
		
		public int getSize(){
			return n;
		}
	}
}
