package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;

class Item implements Comparable<Item>{
	
	public Item(int data, int listID){
		this.data = data;
		this.listID = listID;
	}
	
	private int data;
	private int listID;
	
	public int getData(){return this.data;}
	public int getListID(){return this.listID;}
	
	public int compareTo(Item other) {
		return this.data - other.data;
	}
	
	public String toString(){
		return "(" + data + "," + listID + ")";
	}
	
}

public class MergeKSortedList {
	
	public static void main(String[] args) {
		ArrayList<Integer>[] lists = (ArrayList<Integer>[])new ArrayList[3];
		int [] a1 = {1,3,5};
		lists[0] = new ArrayList<Integer>(Arrays.asList(1,3,5));
		lists[1] = new ArrayList<Integer>(Arrays.asList(2,2,7));
		lists[2] = new ArrayList<Integer>(Arrays.asList(3,4,6));
		
		ArrayList<Integer> mList = mergeKSortedList(lists);
		//System.out.println(mList);
		
		String[] docs = new String[]{"1,2,3", "2,3", "2,5"};
		mr_reduce(docs);
	}
	
	public static void mr_reduce(String[] docs) {
		ArrayList<Integer>[] lists = (ArrayList<Integer>[])new ArrayList[docs.length];
		for (int i = 0; i < docs.length; i++) {
			lists[i] = new ArrayList<Integer>();
			String[] words = docs[i].split(",");
			for (int j = 0; j < words.length; j++) {
				lists[i].add(Integer.parseInt(words[j]));
			}
		}
		ArrayList<Integer> mList = mergeKSortedList(lists);
		LinkedHashSet<Integer> mList2 = new LinkedHashSet<Integer>(mList);
		for (Integer integer : mList2) {
			System.out.print(integer + " ");
		}
	}
	
	public static ArrayList<Integer> mergeKSortedList(ArrayList<Integer>[] lists){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		PriorityQueue<Item> heap = new PriorityQueue<Item>();
		int[] ptrs = new int[lists.length];
		for (int i = 0; i < lists.length; i++) {
			//heap.offer(lists[i].get(0));
			heap.offer(new Item(lists[i].get(0), i));
			ptrs[i]++;
		}
		while(!heap.isEmpty()){
			Item t = heap.poll();
			ret.add(t.getData());
			int listID = t.getListID();
			if(ptrs[listID] < lists[listID].size()){
				heap.offer(new Item(lists[listID].get(ptrs[listID]), listID));
				ptrs[listID]++;
			}
		}
		return ret;
	}
}
