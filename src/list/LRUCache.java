package list;

import java.util.*;

// code from crack the code interview (but did not pass leetcode)
public class LRUCache {
    int maxCacheSize = 0;
    HashMap<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();
    private LinkedListNode listHead = null;
    private LinkedListNode listTail = null;
    
    public LRUCache(int capacity) {
        this.maxCacheSize = capacity;
    }
    
    public int get(int key) {
    	LinkedListNode node = map.get(key);
    	if(node == null) return -1;
    	if(node != listHead){
    		removeFromLinkedList(node);
    		insertAtFrontOfLinkedList(node);
    	}
    	return node.value;
    }
    
    private void insertAtFrontOfLinkedList(LinkedListNode node) {
		if(listHead == null){
			listHead = node;
			listTail = node;
		}else {
			listHead.prev = node;
			node.next = listHead;
			listHead = node;
		}
	}

	private void removeFromLinkedList(LinkedListNode node) {
		if(node == null) return ;
		if(node.prev != null) node.prev.next = node.next;
		if(node.next != null) node.next.prev = node.prev;
		if(node == listTail) listTail = node.prev;
		if(node == listHead) listHead = node.next;
	}

	private boolean removeKey(int key){
		LinkedListNode node = map.get(key);
		removeFromLinkedList(node);
		map.remove(key);
		return true;
	}
	
	public void set(int key, int value) {
		removeKey(key);
		
		if(map.size() >= maxCacheSize && listTail != null){
			removeKey(listTail.key);
		}
		
		//insert new node
		LinkedListNode node = new LinkedListNode(key, value);
		insertAtFrontOfLinkedList(node);
		map.put(key, node);
    }
    
    private static class LinkedListNode{
    	private LinkedListNode next, prev;
    	public int key;
    	public int value;
		public LinkedListNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
    }
}