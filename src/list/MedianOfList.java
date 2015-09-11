package list;


public class MedianOfList {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	static void printList(ListNode head){
		ListNode p = head;
		while(p != null){
			System.out.print(p.val+" ");
			p = p.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ListNode head = null;
		
		head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		//head.next.next.next.next.next = new ListNode(6);
		printList(head);
		ListNode median = findMedian(head);
		System.out.println(median.val);
		System.out.println("------------------------------------------------");
	}
	
	// if length is odd
	// 1->
	// 1->2->3->
	
	// if length is even
	// 1->2->
	// 1->2->3->4->
	
	/*
	 * if length is odd, return middle
	 * if length is even, return the right one of the middle two elements
	 */
	public static ListNode findMedian(ListNode head) {
		if(head == null) return null;
		ListNode slow = head;
		ListNode fast = head;
		while(true){
			if(fast == null || fast.next == null) break;
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;	
	}
}
