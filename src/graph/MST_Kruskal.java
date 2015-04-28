package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import set.QuickUnionWeighted;

public class MST_Kruskal {
	
	public static void main(String[] args) {
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList("A-B:4","B-C:8","C-D:7","D-E:9","E-F:10","F-G:2","G-H:1","H-I:7"
				,"A-H:8","B-H:11","C-I:2","C-F:4","D-F:14","G-I:6"));
		MyUGraph G = new MyUGraph(edges);
		G.explain();
		printMST(G);
	}
	
	public static void printMST(MyUGraph G){
		ArrayList<Edge> weights = new ArrayList<Edge>();
		for(int i = 0; i < G.adj.length; i++){
			for (int j : G.adj[i]) {
				if(i < j){
					weights.add(new Edge(i, j, G.M[i][j]));
				}
			}
		}
		System.out.println("weights before sort");
		System.out.println(weights);
		System.out.println("weights after sort");
		Collections.sort(weights);
		System.out.println(weights);
		
		System.out.println("\nEdges of MST : ");
		QuickUnionWeighted uf = new QuickUnionWeighted(G.n);
		
		for (Edge edge : weights) {
			if(!uf.find(edge.left, edge.right)){
				System.out.println(edge);
				uf.unite(edge.left, edge.right);
			}
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int left;
		int right;
		int weight;
		public Edge(int left, int right, int weight) {
			this.left = left;
			this.right = right;
			this.weight = weight;
		}
		public String toString(){
			return ""+left+"-"+right+":"+weight;
		}
		public int compareTo(Edge other) {
			return weight - other.weight;
		}	
	}
}
