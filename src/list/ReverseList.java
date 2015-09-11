package list;


public class ReverseList {

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
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		printList(head);
		head = reverseListIterative(head);
		printList(head);
		head = reverseListIterative2(head);
		printList(head);
		head = reverseListRecursive(head);
		printList(head);
	}
	
	public static ListNode reverseListRecursive(ListNode head) {
		if(head == null) return null;
		ListNode rest = reverseListRecursive(head.next);
		if(rest != null) {
			head.next.next = head;
			head.next = null;
			return rest;
		}else{
			return head;
		}
    }
	
	public static ListNode reverseListIterative2(ListNode head){
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null){
			ListNode tmp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = tmp;
		}
		return prev;
	}
	
	public static ListNode reverseListIterative(ListNode head){
		if(head == null || head.next == null) return head;
		ListNode p = head.next;
		head.next = null;  // break the second element
		while(p != null){
			//insert before
			ListNode nextOne = p.next;
			p.next = head;
			head = p;
			p = nextOne;
		}
		return head;
	}
	
}
