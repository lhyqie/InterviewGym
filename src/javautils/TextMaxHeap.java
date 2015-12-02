package javautils;

import java.util.*;

public class TextMaxHeap {
	public static void main(String[] args) {
		
		PriorityQueue <Integer> pq = new PriorityQueue<Integer>( new Comparator<Integer>(){
			@Override
			public int compare(Integer n1, Integer n2) {
				return n2-n1;
			}
		});
		
		pq.add(3); pq.add(1); pq.add(0);  
		
		while(!pq.isEmpty()){
			System.out.println(pq.poll());
		}
		
	}
}
