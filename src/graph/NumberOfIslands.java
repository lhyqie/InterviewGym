package graph;

import java.util.HashSet;

import set.UnionFind;

public class NumberOfIslands {
	 
	public static int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0) return 0;
        
        int M = grid.length;
        int N = grid[0].length;
        
        QuickUnion qu = new QuickUnion(M * N);
        
        for(int r = 0; r < M; r ++){
            for(int c = 0; c < N; c++){
                if(grid[r][c] == '1'){
                    if(r + 1 < M && grid[r+1][c] == '1'){
                    	qu.unite(r*N + c, (r+1) * N +c);
                    }
                    if(c+1 < N  && grid[r][c+1] == '1'){
                    	qu.unite(r*N + c, r * N + c + 1);
                    }
                }
            }
        }
        
        HashSet<Integer> set = new HashSet<Integer>();
        for(int r = 0; r < M; r ++){
            for(int c = 0; c < N; c++){
                if(grid[r][c] == '1'){
                	set.add(qu.root(r*N+c));
                }
            }
        }
        return set.size();
	}
	
	static class QuickUnion{
		
		int [] ids = null;
		
		public QuickUnion(int n){
			this.ids = new int[n];
			for (int i = 0; i < ids.length; i++) ids[i] = i;
		}
		
		public boolean find(int p, int q){
			return root(p) == root(q);
		}
		
		public void unite(int p, int q){
			int i = root(p);
			int j = root(q);
			ids[i] = j;
		}
		
		public int root(int i){
			while (i != ids[i]) i = ids[i];
			return i;
		}
		
		public int getSize(){
			return this.ids.length;
		}
		
	}

}
