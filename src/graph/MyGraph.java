package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * abstract class graph
 * @author howie
 *
 */
public abstract class MyGraph {
	
	String[] nodeLabels = null;
	int[][] M = null;
	ArrayList<Integer>[] adj = null;
	int n = 0; // # of nodes
	
	public void visualize(){
		Visualizer.visualize(this);
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

}
