package list;

public class CopyListWithRandomPointer {
	
	public static void main(String[] args) {
		RandomListNode head = new RandomListNode(1);
		head.next = new RandomListNode(2);
		head.next.next = new RandomListNode(3);
		head.random = head.next.next;
		head.next.next.random = head;
		
		print(head);
		print(copyRandomList(head));
	}
	
	public static void print(RandomListNode head){
		while(head != null){
			System.out.print(head.label);
			if(head.random != null) System.out.print("("+head.random.label+")");
			System.out.print("-->");
			head = head.next;
		}
		System.out.println();
	}
	
	public static RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode p1 = head;
        RandomListNode p2 = null;
        RandomListNode ret = null;
        while(p1 != null){  // make a copy right after each node of original list 
            //p2 = new RandomListNode(p1.label * 10);   // value of the copied one are timed by 10 to indicate it's different list 
        	p2 = new RandomListNode(p1.label);
        	p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
        }
        p1 = head; p2 = p1.next;
        //print(head);
        
        while(true){  // assign the random pointer of copied list
            p2.random = p1.random == null ? null : p1.random.next;
            p1 = p2.next;
            if(p1 == null) break; 
            p2 = p1.next;
        }
        //print(head);
        
        // split the two lists
        p1 = head;
        p2 = head.next;
        ret = p2;
        while(true){
            p1.next = p2.next;
            p1 = p1.next;
            if(p1 == null) break;
            p2.next = p1.next;
            p2 = p1.next;
        }
        return ret;
    }
	
	public static class RandomListNode {
	      int label;
	      RandomListNode next, random;
	      RandomListNode(int x) { this.label = x; }
	};
	
}

