package list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
 
//  Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
 
	ListNode(int x) {
		val = x;
		next = null;
	}
}
 
class MergeKSortedLinklist {
    
	public static void main(String[] args) {
		String [] docs = //{"69,71,73,75,77,79,81,83,85,87,89,91,93,95,99,101,103,105,107,109,111,113,115,117,119,121,123,125",
		//"18,23,28,33,38,43,48,53,58"};
		mr_reduce(docs);
	}
	
    public static void mr_reduce(String[] docs) {
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		for (int i = 0; i < docs.length; i++) {
            String[] words = docs[i].split(",");
            ListNode head = new ListNode(Integer.parseInt(words[0]));
			ListNode p = head;
            for (int j = 1; j < words.length; j++) {
				p.next = new ListNode(Integer.parseInt(words[j]));
                p = p.next;                                                
			}
            lists.add(head);
		}
		
		HashSet<Integer> values = new HashSet<Integer>();
		ListNode mergedHead = mergeKLists(lists);
		ListNode p = mergedHead;
		while(p != null){
			if(!values.contains(p.val)){
				System.out.print(p.val + " ");
				values.add(p.val);
			}
			p = p.next;
		}
		System.out.println();                                                    
	}
    
    
	public static ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists.size() == 0)
			return null;
 
		//PriorityQueue is a sorted queue
		PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b) {
						if (a.val > b.val)
							return 1;
						else if(a.val == b.val)
							return 0;
						else 
							return -1;
					}
				});
 
		//add first node of each list to the queue
		for (ListNode list : lists) {
			if (list != null)
				q.add(list);
		}
 
		ListNode head = new ListNode(0);
		ListNode p = head; // serve as a pointer/cursor
 
		while (q.size() > 0) {
			ListNode temp = q.poll();
			//poll() retrieves and removes the head of the queue - q. 
			p.next = temp;
 
			//keep adding next element of each list
			if (temp.next != null)
				q.add(temp.next);
 
			p = p.next;
		}
 
		return head.next;
	}
}