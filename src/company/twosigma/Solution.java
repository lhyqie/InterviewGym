package company.twosigma;

import java.util.HashSet;


public class Solution {
	
	static int max_depth = 0;
	
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

	static int friendCircles(String[] friends) {
		if(friends == null || friends.length == 0) return 0;
		int n = friends.length;
		
		QuickUnion uf = new QuickUnion(n);
		
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				if(friends[i].charAt(j) == 'Y'){
					uf.unite(i, j);
				}
			}
		}
		
		HashSet<Integer> parents = new HashSet<Integer>();
		
		for (int i = 0; i < n; i++) {
			int parent = uf.root(i);
			//System.out.println(" node " + i +" 's parent is " + parent);
			parents.add(parent);
		}
			
		return parents.size();
    }
	
	public static void FriendCircles(){
		//		String[] friends = {"YNNNN", 
		//        "NYNNN",
		//        "NNYNN",
		//        "NNNYN",
		//        "NNNNY"};
		String[] friends = {"YYNN",
				"YYYN",
				"NYYN",
				"NNNY"};
		
		int res = friendCircles(friends);
		System.out.println(res);
	}
		
	public static void main(String[] args) {
		FriendCircles();
	
	}
	
	
}
