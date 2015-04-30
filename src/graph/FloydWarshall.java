package graph;

import java.util.ArrayList;
import java.util.Arrays;

import utils.MatrixUtils;

public class FloydWarshall {
	
	public static void main(String[] args) {
		ArrayList<String> edges = new ArrayList<String>(Arrays.asList(
				"1->2:3","3->2:4","2->5:7","2->4:1","1->3:8","4->1:2","1->5:-4","4->3:-5","5->4:6"));
		MyDGraph G = new MyDGraph(edges);
		G.explain();
		G.visualize();
		compute(G);
	}

	public static void compute(MyDGraph G) {
		int n = G.n;
		int [][][] D = new int[n+1][n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j){
					D[0][i][j] = 0;
				}else if(G.M[i][j] == 0){
					D[0][i][j] = 9999 ; //Integer.MAX_VALUE;
				}else{
					D[0][i][j] = G.M[i][j];
				}
			}
		}
		System.out.println("\nD[0] = ");
		MatrixUtils.print(D[0]);
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					D[k+1][i][j] = Math.min(D[k][i][j], D[k][i][k] + D[k][k][j]);    
				}
			}
			System.out.println("\nD["+(k+1)+"] = ");
			MatrixUtils.print(D[k+1]);
		}
	}
	
}
