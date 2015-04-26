package graph;

import java.util.ArrayList;
import java.util.Arrays;

import set.QuickUnion;

/**
 * Introduction to algorithms, MIT, third edition  page 563
 * @author howie
 *
 */
public class ConnectedComponent {
	
	public static void main(String[] args) {
		ArrayList <String> edges = new ArrayList<String>(Arrays.asList("a-b","b-c","c-a","b-d","e-f","e-g","h-i"));
		MyUGraph G = new MyUGraph(edges);
		G.explain();
		System.out.println();
		get_component(G);
	}
	
	public static void get_component(MyUGraph G){
		QuickUnion uf = new QuickUnion(G.n);
		for (int i = 0; i < G.adj.length; i++) {
			for (int j : G.adj[i]) {
				uf.unite(i, j);
			}
		}
		for (int i = 0; i < G.n; i++) {
			int parent = uf.root(i);
			System.out.println(""+i+"-th node is " + G.nodeLabels[i] + " whose set representative is "+ parent +"-th node " + G.nodeLabels[parent]);
		}
	}
	
	static class Node{
		
	}
}
