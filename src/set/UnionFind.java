package set;

public interface UnionFind {
	
	boolean find(int p, int q);
	
	void unite(int p, int q);
	
	int getSize();
}
