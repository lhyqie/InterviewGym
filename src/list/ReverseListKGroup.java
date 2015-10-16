package list;

public class ReverseListKGroup {
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		int k = 2;
		print(head);
		head = reverseKGroup(head, k);
		print(head);
		
	}
	
	static void print(ListNode head){
		while(head != null) {
			System.out.print(head.val+" ");
			head = head.next;
		}
		System.out.println();
	}
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        int i = 1;
        ListNode prev = dummy;
        ListNode p = prev.next;
        while(p!=null){
            while(i < k  && p != null){
                i++;
                p = p.next;
            }
            if(p == null) return dummy.next;
            else{
                ListNode prevNext = prev.next;
                ListNode pNext = p.next;
                p.next = null;
                reverse(prev.next);
                prev.next = p;
                prevNext.next = pNext;
                prev = prevNext;
                p = prev.next;
                i = 1;
            }
        }
        return dummy.next;
    }
    
    public static ListNode reverse(ListNode head){
        if(head == null) return null;
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null){
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }
}
