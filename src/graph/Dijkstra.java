package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	
	public static void main(String[] args) {
		
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList(
				"s->t:10","s->y:5","t->x:1","t->y:2","x->z:4","z->x:6","y->t:3","y->x:9","y->z:2","z->s:7"));
		MyDGraph G = new MyDGraph(edges);
		G.explain();
		G.visualize();
		
		printSingleSourceDistance(G, 0);
	}	
	static class DistanceNode implements Comparable<DistanceNode>{
		
		int id;
		int distance = Integer.MAX_VALUE;
		int parent = -1;
		
		public DistanceNode(int id) {
			this.id = id;
		}
		
		public int compareTo(DistanceNode o) {
			return distance - o.distance;
		}
	}
	
	public static void relatex(MyGraph G, PriorityQueue<DistanceNode> pQ, DistanceNode[] nodeRefers,  int u, int v){
		DistanceNode uNode = nodeRefers[u];
		DistanceNode vNode = nodeRefers[v];
		if(vNode.distance > uNode.distance + G.M[u][v]){
			vNode.distance = uNode.distance + G.M[u][v];
			vNode.parent = u;
		}
		// heapify priority queue pQ
		ArrayList<DistanceNode> tmp = new ArrayList<DistanceNode>();
		while(!pQ.isEmpty()) tmp.add(pQ.poll()); 
		pQ.clear(); 
		for (DistanceNode node : tmp) pQ.add(node);
	}
	
	public static void printSingleSourceDistance(MyGraph G, int source){
		PriorityQueue<DistanceNode> pQ = new PriorityQueue<DistanceNode>();
		DistanceNode[] nodeRefers = new DistanceNode[G.n];
		for (int i = 0; i < G.n; i++) {
			nodeRefers[i] = new DistanceNode(i);
			if(i == source) nodeRefers[i].distance = 0;
			pQ.offer(nodeRefers[i]);
		}
		while(!pQ.isEmpty()){
			DistanceNode uNode = pQ.poll();
			int u = uNode.id;
			for (int v : G.adj[u]) {
				relatex(G, pQ, nodeRefers, u, v);
			}
		}
		
		for (int i = 0; i < nodeRefers.length; i++) {
			System.out.print(String.format("Distance from %s to %s is %d, path:", G.nodeLabels[source], G.nodeLabels[i], nodeRefers[i].distance));
			int p = nodeRefers[i].parent;
			StringBuffer path = new StringBuffer();
			path.append(G.nodeLabels[i]);
			while(p!= -1){
				path.append(G.nodeLabels[p]);
				p = nodeRefers[p].parent;
			}
			System.out.println(path.reverse().toString());
		}
	}
	
}
