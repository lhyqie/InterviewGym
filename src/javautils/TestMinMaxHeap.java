package javautils;

import java.util.Collections;
import java.util.PriorityQueue;

public class TestMinMaxHeap {
	public static void main(String[] args) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        int [] nums = {1,3,5,2,8,5,1};
        for (int num : nums) {
			minHeap.offer(num);
			maxHeap.offer(num);
		}
        
        while(!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");
        
        System.out.println();
        while(!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " ");
	}
}
