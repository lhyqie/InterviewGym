package graph;

import java.util.ArrayList;
import java.util.Arrays;

public class MyGraphTest {
	public static void Test_Create_UnDirectedGraph(){
		MyGraph G = null;
		ArrayList<Integer>[] adj = null;
		String[] nodeLabels = null;
		ArrayList<String> edges = null;
		
		System.out.println("\n========================Test MyGraph(int[][] M)==================================\n");
		G = new MyUGraph(new int[][]{{0,1,1}, {1, 0, 0}, {1, 0, 0}});
		G.explain();
		G.visualize();
		
//		G = new MyUGraph(new int[][]{{0,1,1}, {1, 0, 0}, {1, 1, 0}});  // this should throw an exception as M is not symmetric

		System.out.println("\n========================Test MyGraph(int[][] M, String[] nodeLabels)==================================\n");
		G = new MyUGraph(new int[][]{{0,1,1}, {1, 0, 0}, {1, 0, 0}}, new String[]{"A","B","C"});
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(ArrayList<Integer>[] adj)==================================\n");
		adj = (ArrayList<Integer>[])new ArrayList[4];
		adj[0] = new ArrayList(Arrays.asList(1,2,3));
		adj[1] = new ArrayList(Arrays.asList(0,3));
		adj[2] = new ArrayList(Arrays.asList(0,3));
		adj[3] = new ArrayList(Arrays.asList(0,1,2));
		G = new MyUGraph(adj);
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(ArrayList<Integer>[] adj, String[] nodeLabels)==================================\n");
		adj = (ArrayList<Integer>[])new ArrayList[4];
		adj[0] = new ArrayList(Arrays.asList(1,2,3));
		adj[1] = new ArrayList(Arrays.asList(0,3));
		adj[2] = new ArrayList(Arrays.asList(0,3));
		adj[3] = new ArrayList(Arrays.asList(0,1,2));
		nodeLabels = new String[]{"A","B","C","D"};
		G = new MyUGraph(adj, nodeLabels);
		G.explain();
		G.visualize();
		
//		adj = (ArrayList<Integer>[])new ArrayList[4];
//		adj[0] = new ArrayList(Arrays.asList(1,2,3));
//		adj[1] = new ArrayList(Arrays.asList(0,3));
//		adj[2] = new ArrayList(Arrays.asList(0,1));
//		adj[3] = new ArrayList(Arrays.asList(0,1,2));
//		G = new MyUGraph(adj);      // this should throw an exception as adj is not symmetric
		
		System.out.println("\n========================Test MyGraph(ArrayList<String>[] edges)==================================\n");
		edges = new ArrayList<String>(Arrays.asList("A-B","B-C","C-D","D-E","E-F"));
		G = new MyUGraph(edges);
		G.explain();
		G.visualize();
	}
	
	public static void Test_Create_DirectedGraph(){
		MyGraph G = null;
		ArrayList<Integer>[] adj = null;
		String[] nodeLabels = null;
		ArrayList<String> edges = null;
		
		System.out.println("\n========================Test MyGraph(int[][] M)==================================\n");
		G = new MyDGraph(new int[][]{{0,1,1}, {1, 0, 0}, {0, 0, 0}});
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(int[][] M, String[] nodeLabels)==================================\n");
		G = new MyDGraph(new int[][]{{0, 1, 1}, {1, 0, 0}, {1, 1, 0}}, new String[]{"A","B","C"});
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(ArrayList<Integer>[] adj)==================================\n");
		adj = (ArrayList<Integer>[])new ArrayList[4];
		adj[0] = new ArrayList(Arrays.asList(1,2,3));
		adj[1] = new ArrayList(Arrays.asList(0));
		adj[2] = new ArrayList();
		adj[3] = new ArrayList(Arrays.asList(1));
		G = new MyDGraph(adj);
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(ArrayList<Integer>[] adj, String[] nodeLabels)==================================\n");
		adj = (ArrayList<Integer>[])new ArrayList[4];
		adj[0] = new ArrayList(Arrays.asList(1,2,3));
		adj[1] = new ArrayList(Arrays.asList(0));
		adj[2] = new ArrayList();
		adj[3] = new ArrayList(Arrays.asList(1));
		nodeLabels = new String[]{"A","B","C","D"};
		G = new MyDGraph(adj, nodeLabels);
		G.explain();
		G.visualize();
		
		System.out.println("\n========================Test MyGraph(ArrayList<String>[] edges)==================================\n");
		edges = new ArrayList<String>(Arrays.asList("A->B","B->C","C->D","D->E","E->F"));
		G = new MyDGraph(edges);
		G.explain();
		G.visualize();
	}
	
	public static void main(String[] args) {
		Test_Create_UnDirectedGraph();
		//Test_Create_DirectedGraph();
	}
}
