package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Undirected graph
 * @author howie
 *
 */
public class MyGraph {
	
	public static final int DIRECTED = 1;
	public static final int UNDIRECTED = 2;
	
	String[] nodeLabels = null;
	int[][] M = null;
	ArrayList<Integer>[] adj = null;
	int n = 0; // # of nodes
	
	/**
	 * @param M : adjacency matrix 
	 * nodeLabels will be set to index of nodes
	 * adjacency list is inferred 
	 */
	public MyGraph(int[][] M){
		assert M.length == M[0].length;  // M is sqaure matrix
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
	public MyGraph(int[][] M, String[] nodeLabels){
		assert M.length == M[0].length;  // M is sqaure matrix
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
	 */
	public MyGraph(ArrayList<Integer>[] adj){
		this.n = adj.length;
		this.adj = adj;
		this.nodeLabels = new String[n];
		for (int i = 0; i < n; i++) {
			nodeLabels[i] = ""+i;
		}
	}
	
	/**
	 * @param adj : adjacency list
	 * @param nodeLabels : label for nodes
	 */
	public MyGraph(ArrayList<Integer>[] adj, String[] nodeLabels){
		this.n = adj.length;
		this.adj = adj;
		this.nodeLabels = nodeLabels;
	}
	
	/**
	 * build graph from a list of edges
	 * each edge is denoted by "nodeLabel1->nodeLabel2"
	 * @param edges
	 */
	public MyGraph(ArrayList<String> edges){
		TreeSet<String> nodeSet = new TreeSet<String>();
		TreeMap<String, Integer> node2id = new TreeMap<String, Integer>();
		for (String edge : edges) {
			String[] tokens = edge.split("->");
			nodeSet.add(tokens[0]);
			nodeSet.add(tokens[1]);
		}
	}
	
	public void visualize(int mode){
		Visualizer.visualize(this, mode);
	}
	
	public void explain(){
		System.out.print("Graph details :");
		System.out.print("\t totally " + this.n +" nodes");
		System.out.println("\t nodeLabels :" + Arrays.toString(nodeLabels));
		System.out.println("------------------Adj Matrix-----------------------");
		printAdjMatrix();
		System.out.println("------------------Adj List-----------------------");
		printAdjList();
	}
	
	public void printAdjMatrix(){
		if(this.M == null){
			System.out.println("Adj Matrix is null");
		}else{
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(M[i][j]+" ");
				}
			}
		}
		System.out.println();
	}
	
	public void printAdjList(){
		if(this.adj == null){
			System.out.println("Adj list is null");
		}else{
			for (int i = 0; i < n; i++) {
				System.out.println(adj[i]);
			}
		}
	}
	
	public static void Test_Create_MyGraph(){
//		System.out.println("\n========================Test MyGraph(int[][] M)==================================\n");
//		MyGraph G = new MyGraph(new int[][]{{0,1,1}, {1, 0, 0}, {1, 0, 0}});
//		G.explain();
//		//G.visualize(MyGraph.DIRECTED);
//		G.visualize(MyGraph.UNDIRECTED);
		
//		System.out.println("\n========================Test MyGraph(int[][] M, String nodeLabels)==================================\n");
//		MyGraph G = new MyGraph(new int[][]{{0,1,1}, {1, 0, 0}, {1, 0, 0}}, new String[]{"A","B","C"});
//		G.explain();
//		//G.visualize(MyGraph.DIRECTED);
//		G.visualize(MyGraph.UNDIRECTED);
		
		System.out.println("\n========================Test MyGraph(ArrayList<Integer>[])==================================\n");
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[4];
		adj[0] = new ArrayList(Arrays.asList(1,2,3));
		adj[1] = new ArrayList(Arrays.asList(0,3));
		adj[2] = new ArrayList(Arrays.asList(1,3));
		adj[3] = new ArrayList(Arrays.asList(0,2));
		MyGraph G = new MyGraph(adj);
		G.explain();
		G.visualize(MyGraph.UNDIRECTED);
		//G.visualize(MyGraph.DIRECTED);
	}
	
	public static void main(String[] args) {
		Test_Create_MyGraph();
	}
}
