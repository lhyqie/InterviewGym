package graph;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Undirected Graph
 * @author howie
 *
 */
public class MyUGraph extends MyGraph {
	/**
	 * @param M : adjacency matrix 
	 * nodeLabels will be set to index of nodes
	 * adjacency list is inferred 
	 */
	public MyUGraph(int[][] M){
		assert M.length == M[0].length;  // M is sqaure matrix
		if(!checkValid(M)) throw new IllegalArgumentException("M is not symmetric");
		this.n = M.length;
		this.M = M;
		this.nodeLabels = new String[n];
		for (int i = 0; i < n; i++) {
			nodeLabels[i] = ""+i;
		}
		adj = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(M[i][j] == 1){
					adj[i].add(j);
				}
			}
		}
	}
	
	/**
	 * @param M : adjacency matrix 
	 * @param nodeLabels : label for nodes
	 * adjacency list is inferred 
	 */
	public MyUGraph(int[][] M, String[] nodeLabels){
		assert M.length == M[0].length;  // M is sqaure matrix
		if(!checkValid(M)) throw new IllegalArgumentException("M is not symmetric");
		this.n = M.length;
		this.M = M;
		this.nodeLabels = nodeLabels;
		adj = (ArrayList<Integer>[])new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(M[i][j] == 1){
					adj[i].add(j);
				}
			}
		}
	}
	
	/**
	 * @param adj : adjacency list
	 * nodeLabels will be set to index of nodes
	 * note: adjacency matrix is not inferred (assuming matrix is sparse)
	 */
	public MyUGraph(ArrayList<Integer>[] adj){
		if(!checkValid(adj)) throw new IllegalArgumentException("adj is not symmetric");
		this.n = adj.length;
		this.adj = adj;
		this.nodeLabels = new String[n];
		this.M = new int[n][n];
		for (int i = 0; i < n; i++) {
			nodeLabels[i] = ""+i;
		}
		for (int i = 0; i < this.adj.length; i++) {
			for (int j : this.adj[i]) {
				M[i][j] = 1;
			}
		}
	}
	
	/**
	 * @param adj : adjacency list
	 * @param nodeLabels : label for nodes
	 * note: adjacency matrix is not inferred (assuming matrix is sparse)
	 */
	public MyUGraph(ArrayList<Integer>[] adj, String[] nodeLabels){
		if(!checkValid(adj)) throw new IllegalArgumentException("adj is not symmetric");
		this.n = adj.length;
		this.adj = adj;
		this.nodeLabels = nodeLabels;
		this.M = new int[n][n];
		for (int i = 0; i < this.adj.length; i++) {
			for (int j : this.adj[i]) {
				M[i][j] = 1;
			}
		}
	}
	
	/**
	 * build graph from a list of edges
	 * each edge is denoted as "nodeLabel1-nodeLabel2"
	 * @param edges
	 * adjacency list is inferred 
	 * adjacency matrix is also inferred
	 */
	public MyUGraph(ArrayList<String> edges){
		TreeSet<String> nodeSet = new TreeSet<String>();
		TreeMap<String, Integer> node2id = new TreeMap<String, Integer>();
		for (String edge : edges) {
			String[] tokens = edge.split("-");
			if(tokens.length != 2) { throw new IllegalArgumentException("format of each edges must be node1-node2 "); }
			if(tokens[1].contains(":")){ //weighted graph
				String[] pieces = tokens[1].split(":");
				if(pieces.length != 2) {throw new IllegalArgumentException("format of each edges must be node1-node2 ");}
				nodeSet.add(tokens[0]);
				nodeSet.add(pieces[0]);
			}else{ //unweighted graph
				nodeSet.add(tokens[0]);
				nodeSet.add(tokens[1]);
			}
			
		}
		
		this.n = nodeSet.size();
		this.adj = (ArrayList<Integer>[])new ArrayList[n];
		this.nodeLabels = new String[n];
		this.M = new int[n][n];
		
		int id = 0;
		for (String node : nodeSet) {
			node2id.put(node, id);
			nodeLabels[id] = node;
			id++;
		}
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for (String edge : edges) {
			String[] tokens = edge.split("-");
			int node_id1 = -1, node_id2 = -1;
			int weight = 1;
			if(tokens[1].contains(":")){ //weighted graph
				String[] pieces = tokens[1].split(":");
				node_id1 = node2id.get(tokens[0]);
				node_id2 = node2id.get(pieces[0]);
				weight = Integer.parseInt(pieces[1]);
			}else{ //unweighted graph
				node_id1 = node2id.get(tokens[0]);
				node_id2 = node2id.get(tokens[1]);
			}
			
			M[node_id1][node_id2] = weight;
			M[node_id2][node_id1] = weight;
			if(!adj[node_id1].contains(node_id2)) {
				adj[node_id1].add(node_id2);
			}
			if(!adj[node_id2].contains(node_id1)) {
				adj[node_id2].add(node_id1);
			}
		}
	}
	
	/**
	 * check whether adjacency matrix M is symmetric 
	 * @param M
	 * @return
	 */
	private boolean checkValid(int [][] M){
		for (int i = 0; i < M.length; i++) {
			for (int j = i+1; j < M[i].length; j++) {
				if(M[i][j]  != M[j][i]){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * check whether adjacency list adj is symmetric 
	 * @param M
	 * @return
	 */
	private boolean checkValid(ArrayList<Integer>[] adj){
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].size(); j++) {
				int neighbor = adj[i].get(j);
				if(!adj[neighbor].contains(i)){
					return false;
				}
			}
		}
		return true;
	}
}
