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
	
	public static void Test_Graph_BFS() {
		ArrayList<String> edges = null;
		MyUGraph G = null;
	
		// test one connected component
		edges = new ArrayList<String>(Arrays.asList("r-s","v-r","s-w","w-t","w-x","t-x","t-u","x-u","x-y","u-y"));
		G = new MyUGraph(edges);
		G.explain();
		G.visualize();
		System.out.println();
		G.BFS();
		
		// test two disjoint connected component
		System.out.println();
		edges = new ArrayList<String>(Arrays.asList("0-1","1-2","2-3","4-5","5-6","6-4"));
		G = new MyUGraph(edges);
		G.explain();
		G.visualize();
		System.out.println();
		G.BFS();
		
		// test on directed graph
		edges = new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3","3->1","4->3","4->5","5->5"));
		MyDGraph G2 = new MyDGraph(edges);
		G2.explain();
		G2.visualize();
		System.out.println();
		G2.BFS();
	}
	
	public static void Test_Graph_DFS() {
		ArrayList<String> edges = null;
		MyDGraph G = null;
		edges = new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3","3->1","4->3","4->5","5->5"));
		G = new MyDGraph(edges);
		G.explain();
		G.visualize();
		System.out.println();
		G.DFS();
		
	}
	
	public static void Test_DAG(){
		ArrayList<String> edges = null;
		MyDGraph G = null;
		
//		edges = new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3","3->1"));
//		G = new MyDGraph(edges);
//		G.explain();
//		//G.visualize();
//		System.out.println(" G is DAG ? ");
//		System.out.println(G.isDAG());
		
		edges = new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3"));
		G = new MyDGraph(edges);
		G.explain();
		//G.visualize();
		System.out.println(" G is DAG ? ");
		System.out.println(G.isDAG());
		
	}
	
	public static void Test_Graph_Tranpose(){
		ArrayList<String> edges = null;
		MyDGraph G = null;
		edges = new ArrayList<String>(Arrays.asList("0->1","0->2","1->2","2->3","3->1","4->3","4->5","5->5"));
		G = new MyDGraph(edges);
		G.explain();
		G.visualize();
		
		MyGraph G2 = G.getTranpose();
		G2.explain();
		G2.visualize();
		
	}
	
	public static void main(String[] args) {
		//Test_Create_UnDirectedGraph();
		//Test_Create_DirectedGraph();
		//Test_Graph_BFS();
		//Test_Graph_DFS();
		//Test_DAG();
		Test_Graph_Tranpose();
	}
}
