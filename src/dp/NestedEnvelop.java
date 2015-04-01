package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * You are given an unlimited number of each of n different types of envelopes.
 * The dimensions of envelope type i are x_i ¡Á y_i.(i is in sub script) In nesting envelopes inside one another, 
 * you can place envelope A inside envelope B if and only if the dimensions A are strictly smaller than the dimensions of B. 
 * Algorithm to determine the largest number of envelopes that can be nested inside one another.
 */
public class NestedEnvelop {
	
	public static void main(String[] args) {
		
		int[][][] testcases =  { 
				{{1,1}, {2,2}, {3,3}, {4,4}, {2,10}, {5,5}, {3,10}, {6,6}, {4,8}, {5,10}, {6,8}}, 
		};
		
		for (int[][] testcase : testcases) {
			List<Envelop> envelops = new ArrayList<Envelop>();
			for (int[] row : testcase) {
				envelops.add(new Envelop(row[0], row[1]));
			}
			
			for (Envelop envelop : envelops) {
				System.out.print(envelop+" ");
			}
			System.out.println();
			System.out.println("after sorting by area");
			Collections.sort(envelops);
			for (Envelop envelop : envelops) {
				System.out.print(envelop+" ");
			}
			System.out.println();
			
			int maxDepth = findMaximumDepth(envelops);
			System.out.println("maximum depth of nested envelop is : " + maxDepth);
		}
	}
	
	/**
	 * find the maximum depth of 
	 * @param envelops
	 * @return
	 */
	public static int findMaximumDepth(List<Envelop> envelops){
		int[] c = new int[envelops.size()];
		if(envelops.size() == 0) return 0;
		c[0] = 0;
		for (int i = 1; i < envelops.size(); i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if(envelops.get(j).w <= envelops.get(i).w && envelops.get(j).h <= envelops.get(i).h){
					if(max < c[j] + 1) max = c[j] + 1;
				}
			}
			c[i] = max;
		}
		
		System.out.println(Arrays.toString(c));
		
		int max = 0;
		for (int e : c) {
			if(max < e) max = e;
		}
		return max;
	}
	
	private static class Envelop implements Comparable<Envelop>{
		
		private int w;
		private int h;
		
		public Envelop(int w, int h){
			this.w = w;
			this.h = h;
		}
		public String toString(){
			return "("+w +"," + h+")";
		}
		
		public int compareTo(Envelop o) {
			return w * h - o.w * o.h;
		}
	}
}

