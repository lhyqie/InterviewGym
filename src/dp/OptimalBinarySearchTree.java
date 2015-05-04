package dp;

import utils.MatrixUtils;

/**
 * Introduction to algorithms second edition page 357
 * @author howie
 *
 */
public class OptimalBinarySearchTree {
	
	public static void main(String[] args) {
		double [] p = {0.15, 0.10, 0.05, 0.10, 0.20};
		double [] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
		opt_bst(p, q);
	}
	
	public static void opt_bst(double []p, double []q){
		int n = p.length;
		double [][] e = new double[n+1][n+1];
		double [][] w = new double[n+1][n+1];
		for (int j = 0; j < n+1; j++) {    //w[i][j]  means sum of probabilities of subtree of nodes[i...j-1] where i in [0...n-1]   j  in [1...n]
										   // when i == j,  w[i][j] is the probabilities q[j]
			w[j][j] = q[j];				   //e[i][j] means the expected cost for optimal subtree nodes[i...j-1]  where i in [0...n-1]   j  in [1...n]
			e[j][j] = q[j];                // when i == j,  e[i][j] is the probabilities q[j]
		} 
		for (int L = 1; L <= n; L++) {              // number of key nodes in the tree   1...n
			for (int i = 0; i <= n-L; i++) {        // starting key node  i              0...n-1   ......   0...0
				int j = i+L-1+1;                    // ending key node    j              1...n     ......   n...n
				//System.out.println("i="+i+" j="+j);
				e[i][j] = Double.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j-1] + q[j];
				
				for (int r = i; r <= j-1; r++) {  // root node
					double t = e[i][r] + e[r+1][j] + w[i][j];
					if( t < e[i][j]){
						e[i][j] = t;
					}
				}
			}
		}
		
		MatrixUtils.print(w);
		System.out.println();
		MatrixUtils.print(e);
		System.out.println();
		System.out.println("optimal expected cost =" + e[0][n]);
	}
}
