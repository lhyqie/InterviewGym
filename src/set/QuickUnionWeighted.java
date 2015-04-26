package set;

public class QuickUnionWeighted implements UnionFind {
	
	int [] ids = null;
	int [] sz = null;
	
	public QuickUnionWeighted(int n){
		this.ids = new int[n];
		this.sz = new int[n];
		for (int i = 0; i < ids.length; i++) ids[i] = i;
		for (int i = 0; i < sz.length; i++) sz[i] = 1;
	}
	
	public boolean find(int p, int q) {
		return root(p) == root(q);
	}

	public void unite(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(sz[i] < sz[j]){
			ids[i] = j;
			sz[j] += sz[i];
		}else{
			ids[j] = i;
			sz[i] += sz[j];
		}
		
	}
	
	public int root(int i){
		while (i != ids[i]) i = ids[i];
		return i;
	}

	public int getSize() {
		
		return ids.length;
	}

}
