package list;

public class InsertionSortList {
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static void print(ListNode head){
		if(head == null) return;
		ListNode p = head;
		while(p != null){
			System.out.print(p.val+ " ");
			p = p.next;
		}
		System.out.println();
	}
	
	public static ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        
        ListNode p = head.next;  // the second node and so on
        head.next = null;   // break
        
        while(p != null){
            ListNode nextTmp = p.next;
            ListNode p2 = dummy;
            while(p2.next != null && p.val > p2.next.val){
                p2 = p2.next;
            }
            p.next = p2.next;
            p2.next = p;
            p = nextTmp;
        }
        return dummy.next;
    }
		
	public static void main(String[] args) {
		ListNode head = new ListNode(3);
		head.next = new ListNode(1);
		print(head);
		head = insertionSortList(head);
		print(head);
	}
}
