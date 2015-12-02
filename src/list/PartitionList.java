package list;

/*
 * Given a linked list and a value x, partition it such that all nodes less than x come 
 * before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionList {
	
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
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(2);
		printList(head);
		
		ListNode newHead = partition(head, 3);
		printList(newHead);
	}
	
	public static ListNode partition(ListNode head, int x) {
        ListNode L1 = new ListNode(Integer.MAX_VALUE);
        ListNode L2 = new ListNode(Integer.MAX_VALUE);
        ListNode p = head, p1 = L1, p2 = L2;
        while(p != null){
            if(p.val < x){
                p1.next = p;
                p1 = p;
            }else{
                p2.next = p;
                p2 = p;
            }
            p = p.next;
        }
        //when p== null,  the while loop ends and p1, p2 point to the last element in L1, L2 
        p1.next = null;
        p2.next = null;   //don't forget to break, otherwise there maybe cycle in the list
        
        if(L1.next == null){  //check if L1 is empty
            return L2.next;  
        }else{
            p1.next = L2.next;
            return L1.next;
        }
    }
	
}
