package list;

import list.ReverseList.ListNode;

public class ReverseListII {
	
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
		ListNode head = new ListNode(3);
		head.next = new ListNode(5);
		printList(head);
		head = reverseBetween(head, 1, 2);
		printList(head);
	}
	
	public static ListNode reverseBetween(ListNode head, int m, int n) {
        
        if(m >= n || m <= 0 || n <= 0 || head == null) return head;
        
        // add a dummy node ,  the original m-th node is (m+1)-th, the orignal n-th node is (n+1)-th
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;        
        
        ListNode before = dummy, after = null;
        int cnt = 0; 
        ListNode p1 = head, p2 = head;
        while(p1 != null){ // position p1
            cnt++;
            if(cnt == m) break;
            before = p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        if(p1 == null)  return head;  // size of list < m
        cnt--;
        while(p2 != null){
            cnt++;
            if(cnt == n) break;
            p2 = p2.next;
        }
        if(p2 == null) return head; //size of list < n
        after = p2.next;
        
        // break "before"-> m-th node    dummy->....->before->null  
        before.next = null;
        // break n-th node -> "after"    p1->....->p2->null      after->....->null
        p2.next = null;
        
        ListNode subList = reverseList(p1);                 // p2->...->p1->null
        before.next = subList;
        p1.next = after;
        
        return dummy.next;
    }
    
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null){
            ListNode nextOne = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextOne;
        }
        return prev;
    }
}
