package javautils;

import java.util.*;


public class TestPriorityQueue {
	public static void main(String[] args) {
		PriorityQueue<Item> pq = new PriorityQueue<Item>(new PQComparatorASC());
		pq.add(new Item(30, 3));
		pq.add(new Item(20, 2));
		pq.add(new Item(50, 5));
		pq.add(new Item(100, 10));
		System.out.println(pq);
		
		PriorityQueue<Item> pq2 = new PriorityQueue<Item>(new PQComparatorDESC());
		pq2.add(new Item(30, 3));
		pq2.add(new Item(20, 2));
		pq2.add(new Item(50, 5));
		pq2.add(new Item(100, 10));
		System.out.println(pq2);
			
	}
	
	static class Item{
        
		public Item(int value, int time){
            this.value = value; 
            this.time = time;
        }
        int value;
        int time;
        
		@Override
		public String toString() {
			return "Item [value=" + value + ", time=" + time + "]";
		}
    }
	
	static class PQComparatorASC implements Comparator<Item>{
		@Override
		public int compare(Item o1, Item o2) {
			// TODO Auto-generated method stub
			return o1.time - o2.time;
		}
		
	}
	
	static class PQComparatorDESC implements Comparator<Item>{
		@Override
		public int compare(Item o1, Item o2) {
			// TODO Auto-generated method stub
			return -o1.time + o2.time;
		}
		
	}
}
