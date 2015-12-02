package list;


public class PalindromeList {
	
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
	
	public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        // if original list is odd sized,        head: 1->2->3  return 2
        // if original list is even sized,       head: 1->2->3->4  return 3
        ListNode median = findMedian(head);   
        
        //reverse the sub list from median node    
        // 1->2->3           head: 1->2->null        median: 3->2->null
        // 1->2->3->4        head: 1->2->3->null     median: 4->3->null
        median = reverseList(median);
        
        // size(head) == size(median) or size(head) == size(median) + 1
        ListNode p1 = head;
        ListNode p2 = median;
        while(p2 != null){  // since the second half is shorter, we just need to ensure p2 != null
            if(p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
    
    public ListNode reverseList(ListNode head) {
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
    
    /*
	 * if length is odd, return middle
	 * if length is even, return the right one of the middle two elements
	 */
    public ListNode findMedian(ListNode head) {
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
