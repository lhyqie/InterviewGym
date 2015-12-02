package graph;

import java.util.*;

/**
 * Course Schedule 
 * 
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 */
public class CourseSchedule {
	
	public static void main(String[] args) {
		int numCourses = 2;
		int[][] prerequisites = {{1,0}};
		boolean ret = canFinish(numCourses, prerequisites);
		System.out.println(ret);
		
	}
	
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if(prerequisites == null){
			throw new IllegalArgumentException("prerequisites is null");
		}
		if(numCourses == 0 || prerequisites.length == 0) return true;
		
		//counter for number of prerequisites
		int [] counter = new int[numCourses];
		for(int i = 0; i < prerequisites.length; i++){
			counter[prerequisites[i][0]]++;
		}
		
		//store courses with zero prerequisites
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < numCourses; i++){
			if(counter[i] == 0){
				q.add(i);
			}
		}
		int numNoPre = q.size();
		
		while(!q.isEmpty()){
			int top = q.poll();
			for(int i = 0; i < prerequisites.length; i++){
				if(prerequisites[i][1] ==  top){
					counter[prerequisites[i][0]] --;
					if(counter[prerequisites[i][0]] == 0){
						numNoPre++;
						q.add(prerequisites[i][0]);
					}
				}
			}
			
		}
		
		return numNoPre == numCourses;
    }

}
