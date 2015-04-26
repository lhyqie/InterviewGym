package set;

public class QuickFind implements UnionFind {
	
	int [] ids = null;
	
	public QuickFind(int n){
		this.ids = new int[n];
		for (int i = 0; i < ids.length; i++) ids[i] = i;
	}
	
	public boolean find(int p, int q) {
		return ids[p] == ids[q];
	}

	public void unite(int p, int q) {
		int pid = ids[p];
		for (int i = 0; i < ids.length; i++) {
			if(ids[i] == pid){
				ids[i] = ids[q];
			}
		}
	}

	public int getSize() {
		return ids.length;
	}
}
