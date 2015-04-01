package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/merging-intervals/
 * 
 * Given a set of time intervals in any order, merge all overlapping intervals into one and output the result which 
 * should have only mutually exclusive intervals. 
 * 
 * Let the intervals be represented as pairs of integers for simplicity. 
 * For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}. 
 * The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}. 
 * Similarly {5, 7} and {6, 8} should be merged and become {5, 8}
 */

public class MergeIntervals {
	
	public static void main(String[] args) {
		int[][][] testcases = { 
				 {{1,3}, {5,7}, {6,8}, {2,4}  },
				 {{6,8}, {1,9}, {2,4}, {4,7}  }, 
				 {{6,8}, {1,3}, {2,4}, {4,7}  },
				 {{1,3}, {7,9}, {4,6}, {10,13}}
		};
		for (int[][] testcase : testcases) {
			List<Interval> intervals = new ArrayList<Interval>();
			for (int[] row : testcase) {
				intervals.add(new Interval(row[0], row[1]));
			}
			
			for (Interval interval : intervals) {
				System.out.print(interval+" ");
			}
			System.out.println();
			
			List<Interval> merged_intervals = mergeIntervals(intervals);
			System.out.println("after mergence : ");
			
			for (Interval interval : merged_intervals) {
				System.out.print(interval+" ");
			}
			System.out.println();
			System.out.println("=========================================");
		}
		
	}
	
	/**
	 * @param intervals
	 * @return
	 * 
	 * A simple approach is to start from the first interval and compare it with all other intervals for overlapping, 
	 * if it overlaps with any other interval, then remove the other interval from list and merge the other into the first interval. 
	 * Repeat the same steps for remaining intervals after first. This approach cannot be implemented in better than O(n^2) time.
	 * 
	 * An efficient approach is to first sort the intervals according to starting time. Once we have the sorted intervals, we can combine all intervals in a linear traversal. The idea is, in sorted array of intervals, if interval[i] doesn¡¯t overlap with interval[i-1], then interval[i+1] cannot overlap with interval[i-1] because starting time of interval[i+1] must be greater than or equal to interval[i]. Following is the detailed step by step algorithm.
	 * 1. Sort the intervals based on increasing order of starting time.
	 * 2. Push the first interval on to a stack.
	 * 3. For each interval do the following
	 *     a. If the current interval does not overlap with the stack top, push it.
	 *     b. If the current interval overlaps with stack top and ending time of current interval is more than that of stack top, update stack top with the ending time of current interval.
	 * 4. At the end stack contains the merged intervals.
	 */
	public static List<Interval> mergeIntervals(List<Interval> intervals){
		
		//sort by starting time
		Collections.sort(intervals);
		
		Stack<Interval> stack = new Stack<Interval>();
		if(intervals.size() > 0){
			stack.push(intervals.get(0));
		}
		for (int i = 1; i < intervals.size(); i++) {
			Interval itv = intervals.get(i);
			Interval top = stack.peek();
			if(itv.s <= top.e){
				if(itv.e > top.e){   // merge them elsewise top covers current interval (just ignore it
					stack.pop();
					stack.push(new Interval(top.s, itv.e));
				}
			}else{  //no overlapping, push the new interval
				stack.push(new Interval(itv.s, itv.e));
			}
		}
		return new ArrayList<Interval>(stack);
	}
	
	private static class Interval implements Comparable<Interval>{
		private int s;
		private int e;
		public Interval(int s, int e){
			this.s = s;
			this.e = e;
		}
		public String toString(){
			return "("+s +"," + e+")";
		}
		
		public int compareTo(Interval o) {
			return s - o.s;
		}
	}
}
