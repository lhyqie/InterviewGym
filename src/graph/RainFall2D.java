package graph;

import utils.MatrixUtils;


/**
 * 
 * @author howie
 *
 *  N x M cuboids were put on a rectangular mesh comprising NxM fields, one cuboid on each field. The base of each cuboid covers one field and its surface equals to one square inch. Cuboids on adjacent fields adhere one to another so close that there are no gaps between them. A heavy rain pelted on the construction so that in some areas puddles of water appeared.
	Given the heights of all cuboids in the rectangular mesh
	Your task is to
	write a function that prints to the standard output (stdout) the maximal water volume which may gather in puddles after the rain.
	Note that your function will receive the following arguments:
	m
	which is an integer representing the width M of the rectangular mesh
	heights
	which is an array of integers representing the heights of all cuboids in the rectangular mesh. The first M elements represent the heights of the first mesh line. The next M elements represent the second line and so on.
	Data constraints
	The mesh width and height will not exceed 100 elements
	the heights of the cuboids will be in the [0 .. 10,000] range
	Example
	Input	Output
	M: 6
	heights:
	3 3 4 4 4 2
	3 1 3 2 1 4
	7 3 1 6 4 1
	5
 */

public class RainFall2D {
	private static final int EDGE = 0;
	
	public static void main(String[] args) {
		int m = 6;
		Integer [] heights = {3,3,4,4,4,2,
		 				  3,1,3,2,1,4,
		 				  7,3,1,6,4,1};
		int sum = rain(m, heights);
		System.out.println(sum);
	}
	
	public static void update(int[][] V, int [][] C, int r, int c, int source){
		if(r <0 || r >= V.length || c < 0 || c >= V[0].length) return;
		if(V[r][c] == 0 || C[r][c] == 1) return ;
		C[r][c] = 1;
		if( V[r][c] > source){
			return;
		}else{
			V[r][c] = source;
			update(V, C, r-1, c, source);
			update(V, C, r, c-1, source);
			update(V, C, r+1, c, source);
			update(V, C, r, c+1, source);
		}
		
	}
	
	
	public static int search(int[][] M, int[][] V, int [][]C, int r, int c, int source){
		if(r <0 || r >= M.length || c < 0 || c >= M[0].length) return 0;
		if(M[r][c] == EDGE) return 0;
		C[r][c] = 1;
		if( M[r][c] > source){
			return M[r][c];
		}else{
			int p1 = 99, p2 = 99, p3 = 99, p4 = 99;
			if(C[r-1][c] != 1){
				p1 = search(M, V, C, r-1, c, source);
				if(p1 == 0) return 0;
			}
			if(C[r][c-1] != 1){
				p2 = search(M, V, C, r, c-1, source);
				if(p2 == 0) return 0;
			}
			if(C[r+1][c] != 1){
				p3 = search(M, V, C, r+1, c, source);
				if(p3 == 0) return 0;
			}
			if(C[r][c+1] != 1){
				p4 = search(M, V, C, r, c+1, source);
				if(p4 == 0) return 0;
			}
			return Math.min(Math.min(p1, Math.min(p2,p3)), p4);
		}
	}
	
	public static int rain(Integer m, Integer[] heights) {
        int [][] M = MatrixUtils.createMatrix(heights.length/m+2, m+2, EDGE);
        for (int i = 1; i < M.length-1; i++) {
        	for (int j = 1; j < M[i].length-1; j++) {
				M[i][j] = heights[(i-1)*m+(j-1)];
			}
		}
	    
//        MatrixUtils.print(M);
//        System.out.println();
        
        int [][] V = MatrixUtils.createMatrix(M.length, M[0].length);
        int [][] C = MatrixUtils.createMatrix(M.length, M[0].length);
        
        for (int r = 1; r < M.length-1; r++) {
			for (int c = 1; c < M[r].length-1; c++) {
        		resetC(C);
				int ret = search(M, V, C, r, c, M[r][c]); 
				if(ret != 0){
					V[r][c] = ret;
				}
			}
        }
        
//        System.out.println();
//        MatrixUtils.print(V);
        
        for (int r = 0; r < V.length; r++) {
        	for (int c = 0; c < V[r].length; c++) {
        		resetC(C);
        		//r = 2;
        		//c = 4;
        		update(V, C, r, c, V[r][c]);
        	}
		}
        
//        System.out.println();
//        MatrixUtils.print(V);
        
        
        int sum = 0;
        for (int r = 1; r < M.length-1; r++) {
			for (int c = 1; c < M[r].length-1; c++) {
				if(V[r][c] > 0){
					sum += V[r][c] - M[r][c];
				}
			}
        }
        return sum;

    }	
	

	private static void resetC(int[][]C){
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[i].length; j++) {
				C[i][j] = 0;
			}
		}
	}
	
}
