package set;

public class UnionFindTest {
	public static void main(String[] args) {
		
		int [][] sets = {{0}, {1}, {2, 3, 9}, {5, 6}, {7}, {4, 8}};
		int n = 10;
		
		//QuickFind uf = new QuickFind(n);
		//QuickUnion uf = new QuickUnion(n);
		//QuickUnionWeighted uf = new QuickUnionWeighted(n);
		QuickUnionWeightedWithPathCompression uf = new QuickUnionWeightedWithPathCompression(n);
		
		for (int[] set : sets) {
			for (int j = 1; j < set.length; j++) {
				uf.unite(set[j-1], set[j]);
			}
		}

		for (int i = 0; i < uf.getSize(); i++) {
			for (int j = i+1; j < uf.getSize(); j++) {
				System.out.println(""+ i +" and " + j + " are in the same set ? " + uf.find(i, j));
			}
		}
		
	}
}
