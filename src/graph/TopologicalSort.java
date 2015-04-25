package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class TopologicalSort {
	
	public static void main(String[] args) {
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList("sockes->shoes","undershorts->shoes","undershorts->pants","pants->shoes","pants->belt",
				"shirt->belt","shirt->tie","tie->jacket","belt->jacket"));
		MyDGraph G = new MyDGraph(edges);
		topo_sort(G);
	} 
	
	/**
	 * Topologic sort only applies to DAG (directed acyclic graph)
	 * @param G
	 */
	public static void topo_sort(MyDGraph G){
		G.DFS();
		int [] f = G.getFinishedTime();
		String[] nodeLabels = G.getNodeLabels();
		//bubble sort nodeLabels in the descending order of finished time
		System.out.println("finished time :" + Arrays.toString(f));
		System.out.println("nodes : " + Arrays.toString(nodeLabels));
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f.length - i - 1; j++) {
				if(f[j] < f[j + 1]){
					int t = f[j]; f[j] = f[j+1]; f[j+1] = t;
					String t2 = nodeLabels[j]; nodeLabels[j] = nodeLabels[j+1]; nodeLabels[j+1] = t2;
				}
			}
		}
		System.out.println();
		System.out.println("the following order is topological order");
		System.out.println();
		System.out.println("finished time :" + Arrays.toString(f));
		System.out.println("nodes : " + Arrays.toString(nodeLabels));

	}
	
}
