package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MST_Prim {
	public static void main(String[] args) {
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList("A-B:4","B-C:6","C-D:7","D-E:9","E-F:10","F-G:2","G-H:1","H-I:7"
				,"A-H:8","B-H:11","C-I:2","C-F:4","D-F:14","G-I:6"));
		MyUGraph G = new MyUGraph(edges);
		G.explain();
		System.out.println();
		printMST(G, 0);
	}
	
	static class DistanceNode implements Comparable<DistanceNode>{
		int id;
		int distance;
		public DistanceNode(int id, int distance) {
			this.id = id;
			this.distance = distance;
		}
		public int compareTo(DistanceNode other) {
			return distance - other.distance;
		}
		
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DistanceNode other = (DistanceNode) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}
	
	
	public static void printMST(MyUGraph G, int startNodeIndex){
		PriorityQueue<DistanceNode> pQ = new PriorityQueue<DistanceNode>();
		DistanceNode[] nodeRefers = new DistanceNode[G.n];
		int[] parent = new int[G.n];
		for (int i = 0; i < G.n; i++) {
			parent[i] = -1;
			int distance = Integer.MAX_VALUE;
			nodeRefers[i] = new DistanceNode(i, distance); 
			pQ.add(nodeRefers[i]);
		}
		while(!pQ.isEmpty()){
			int u = pQ.poll().id;
			for (int v : G.adj[u]) {
				if(pQ.contains(new DistanceNode(v, -1))
						&& nodeRefers[v].distance > G.M[u][v]){
					nodeRefers[v].distance = G.M[u][v];
					parent[v] = u;
					// java PriorityQueue doesnot support heapify for a certain node
					// assume we can, we just reconstruct pQ in O(logn)
					ArrayList<DistanceNode> tmp = new ArrayList<DistanceNode>();
					while(!pQ.isEmpty()) tmp.add(pQ.poll()); 
					pQ.clear(); 
					for (DistanceNode node : tmp) pQ.add(node);
				}
			}
		}
		for (int i = 0; i < parent.length; i++) {
			if( parent[i] == -1) continue;
			System.out.println(""+G.nodeLabels[i] + "-" + G.nodeLabels[parent[i]] + ":" + G.M[i][parent[i]]);
		}
	}
}
