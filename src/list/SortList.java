package list;

public class SortList {
	
	public static void main(String[] args) {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		SortList app = new SortList();
		app.sortList(head);
	}
    
	public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode mid = getMiddle(head);
        
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
    
    public ListNode getMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;  // if using fast = head;  leading to deadloop
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(left != null && right != null){
            if(left.val < right.val){
                cur.next = left;
                left = left.next;
            }else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }
    
    static class ListNode {
    	int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}