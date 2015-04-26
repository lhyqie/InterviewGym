package set;


/**
 * assume the element sets are 0...n-1 and ids.length = n
 * @author howie
 *
 */
public class QuickUnion implements UnionFind{
	
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
