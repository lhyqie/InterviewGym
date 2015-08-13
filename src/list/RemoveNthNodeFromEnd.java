package list;

public class RemoveNthNodeFromEnd {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
		
		
	}

	static void print(ListNode head){
		if(head == null) return ;
		System.out.print(head.val+"->");
		print(head.next);
		
	}
	
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null)
			return null;
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy;
		ListNode q = p;
		// move q n times.
		int i = 0;
		while (q != null && i < n+1) {
			q = q.next;
			i++;
		}
		if (i < n+1) {
			return dummy.next;
		} else {
			while(q != null){
				p = p.next;
				q = q.next;
			}
			// when q is null, p.next is the node to be removed
			p.next = p.next.next;
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		print(head);
		System.out.println();
		ListNode head2 = removeNthFromEnd(head, 2);
		print(head2);
	}
}
