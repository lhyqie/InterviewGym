package javautils;

import java.util.*;

class Interval {
	 int start;
	 int end;
	 Interval() { start = 0; end = 0; }
	 Interval(int s, int e) { start = s; end = e; }
	 @Override
	 public String toString(){
		 return "["+start+","+end+"]";
	 }
}

public class TestComparator {
	public static void main(String[] args) {
		Interval[] intervals = new Interval[]
		{new Interval(2,4), 
		 new Interval(8,10),
		 new Interval(3,7),
		 new Interval(1,2), 
		 new Interval(1,3), 
		 };
		
		for (Interval interval : intervals) {
			System.out.println(interval);
		}
		System.out.println();
		
		Arrays.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval a, Interval b) {
				if(a.start != b.start) return a.start - b.start;
				return a.end - b.end;
			}
		});
		
		for (Interval interval : intervals) {
			System.out.println(interval);
		}
	}
}
