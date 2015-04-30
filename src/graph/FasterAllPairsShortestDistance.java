package graph;

import java.util.ArrayList;
import java.util.Arrays;

import utils.MatrixUtils;

public class FasterAllPairsShortestDistance {
	
	public static void main(String[] args) {
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList(
				"1->2:3","3->2:4","2->5:7","2->4:1","1->3:8","4->1:2","1->5:-4","4->3:-5","5->4:6"));
		MyDGraph G = new MyDGraph(edges);
		G.explain();
		G.visualize();
		compute(G);
	}
	
	private static void compute(MyGraph G) {
		int n = G.n;
		int[][] L = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j){
					L[i][j] = 0;
				}else if(G.M[i][j] == 0){
					L[i][j] = 9999 ; //Integer.MAX_VALUE;
				}else{
					L[i][j] = G.M[i][j];
				}
			}
		}
		int m = 1;
		System.out.println("m = "+ m);
		MatrixUtils.print(L);
		while(m < n){
			L = extend_shortest_path(L, L);
			m *= 2;
			System.out.println("m = "+ m);
			MatrixUtils.print(L);
		}
		System.out.println("final all pairs shortest distance is :");
		MatrixUtils.print(L);
	}
	
	private static int[][] extend_shortest_path(int [][] L, int[][] W){
		int n = L.length;
		int[][] L2 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				L2[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < n; k++) {
					L2[i][j] = Math.min(L2[i][j], L[i][k] + W[k][j]);
				}
			}
		}
		return L2;
	}
}
