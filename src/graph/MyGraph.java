package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * abstract class graph
 * @author howie
 *
 */
public abstract class MyGraph {
	
	String[] nodeLabels = null;
	int[][] M = null;				 // adjacent matrix, also representing connections and weights
	ArrayList<Integer>[] adj = null; // adjacent list, representing connections
	int n = 0; // # of nodes
	
	protected final int WHITE = 1;  // unvisited
	protected final int GRAY = 2;   // discovered
	protected final int BLACK = 3;  // explored
	
	protected int color [] = null;
	protected int d[] = null;        // discovered time-stamp
	protected int f[] = null;        // finished  time-stamp
	protected int parent[] = null;   // parent in the discovery path
	
	public String[] getNodeLabels(){
		return nodeLabels;
	}
	
	public int[] getFinishedTime(){
		return f;
	}
	
	public void visualize(){
		Visualizer.visualize(this);
	}
	
	/**
	 * @return : the tranpose of the graph
	 */
	public MyGraph getTranpose(){
		ArrayList<Integer>[] adj = (ArrayList<Integer>[])(new ArrayList[this.n]);
		for (int i = 0; i < this.n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < this.adj.length; i++) {
			for (Integer j : this.adj[i]) {
				adj[j].add(i);
			}
		}
		String[] nodeLabels = this.nodeLabels.clone();
		if(this instanceof MyDGraph)
			return new MyDGraph(adj, nodeLabels);
		else if(this instanceof MyUGraph)
			return new MyUGraph(adj, nodeLabels);
		else
			throw new RuntimeException("getTranpose not specified for this subclass of MyGraph");
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
				System.out.println();
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
	
	/**
	 * run BFS at each node
	 */
	public void BFS(){
		color =  new int[n];
		d =  new int[n];
		parent =  new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = WHITE;
			d[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		for (int i = 0; i < n; i++) {
			BFS_at(i);
		}
		System.out.println("discovered time : " +Arrays.toString(d));
		System.out.println("parent : " + Arrays.toString(parent));
		System.out.println();
	}
	
	/**
	 * run BFS_at certain node if it is undiscovered
	 * @param startNodeIndex
	 */
	private void BFS_at(int startNodeIndex){
		if(startNodeIndex < 0 || startNodeIndex >= n) throw new IllegalArgumentException("startNodeIndex invalid!");
		if(color[startNodeIndex] != WHITE) return;  
		
		color[startNodeIndex] = GRAY;
		d[startNodeIndex] = 0;
		parent[startNodeIndex] = -1;
		
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(startNodeIndex);
		while(!Q.isEmpty()){
			int u = Q.poll();
			for (Integer v : adj[u]) {
				if(color[v] == WHITE){
					color[v] = GRAY;
					d[v] = d[u] + 1;
					parent[v] = u;
					Q.offer(v);
				}
			}
			color[u] = BLACK;
			System.out.print(u+ ":" +nodeLabels[u]+ " ");
			System.out.println(" Q = " + Q);
		}
	}
	
	/**
	 * run DFS at each node
	 */
	public void DFS(){
		color = new int[n];
		d = new int[n];
		f = new int[n];
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			color[i] = WHITE;
			d[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		AtomicInteger time = new AtomicInteger(-1);
		for (int i = 0; i < n; i++) {
			DFS_at(i, time);
		}
		System.out.println();
		System.out.println("discovered time : " +Arrays.toString(d));
		System.out.println("finished time : " +Arrays.toString(f));
		System.out.println("parent : " + Arrays.toString(parent));
		System.out.println();
	}
	
	/**
	 * recursively visit node
	 */
	public void DFS_at(int id, AtomicInteger time){
		if(color == null){   // sometime users may call DFS_at directly
			color = new int[n];
			d = new int[n];
			f = new int[n];
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				color[i] = WHITE;
				d[i] = Integer.MAX_VALUE;
				parent[i] = -1;
			}
		}
		if(id < 0 || id >= n) throw new IllegalArgumentException("id invalid!");
		if(color[id] != WHITE) return;
		color[id] = GRAY;
		d[id] = time.incrementAndGet();
		for (int v : adj[id]) {
			if(color[v] == WHITE){
				DFS_at(v, time);
			}
		}
		color[id] = BLACK;
		f[id] = time.incrementAndGet();
		System.out.print(id+ ":" +nodeLabels[id]+ " ");
	}

}
