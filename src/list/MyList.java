package list;


public class MyList {
	
	public Node head = null;
	
	public MyList(){}
	
	public MyList(int[] arr){
		if(arr == null || arr.length == 0) return;
		head = new Node(arr[0]);
		Node p = head;
		for (int i = 1; i < arr.length; i++) {
			p.next = new Node(arr[i]);
			p = p.next;
		}
	}
	
	public MyList(Node head){ 
		this.head = head;
	}
	
	/**
	 * @return the length of the linkedlist
	 */
	public int length(){
		Node p = head;
		int cnt = 0;
		while(p != null){
			cnt ++; 
			p = p.next;
		}
		return cnt;
	}
	
	/**
	 *  make a copy of the list, 
	 *  this is a deep copy  https://www.cs.utexas.edu/~scottm/cs307/handouts/deepCopying.htm
	 * @return a deep copy of the list
	 */
	public MyList deepCopy(){
		Node p1 = this.head;
		Node head2 = new Node(p1.e);
		Node p2 = head2;
		while(p1 != null){
			p1 = p1.next;
			if(p1 == null ) p2.next = null;
			else p2.next = new Node(p1.e);
			p2 = p2.next;
		}
		
		return new MyList(head2);  //this is shallow copy, has to be fixed
	}
	
	/**
	 * in place reverse, without using recursion
	 * Time Complexity O(n)
	 */
	public void reverse1(){
		if(head == null || head.next == null) return;
		Node p = head.next;  // p iterates from the second node to last one
		head.next = null;
		while(p != null){
			Node q = p;     // q will always be the node before p in the following loop
			p = p.next;
			// insert q to the front of the first node
			q.next = head;
			head = q;
			
		}
		
	}
	
	/**
	 * in place reverse, using recursion
	 * Time Complexity O(n)
	 */
	public void reverse2(){
		head = reverse_cur(head);
	}
	
	/**
	 * Idea:  for a list of n elements (n>=2)
	 *        let p storage the old head
	 *        reverse last n-1 nodes,
	 *        let head be new head of the reversed last n-1 nodes
	 *        the tail of the last n-1 nodes will be p.next 
	 *        let tail.next points to p (current head)
	 *        let p.next be null
	 *         
	 */
	private Node reverse_cur(Node head){
		if(head == null) return null;
		if(head.next == null) return head;
		//if the list is more than 1 node
		Node p = head;
		head = reverse_cur(head.next);
		p.next.next = p;
		p.next = null;
		return head;
	}
	
	/**
	 * @return k-th element (0 based index) from head
	 * if the list has less than k+1 elements, return null
	 * Time Complexity O(n)
	 */
	public Node kFromHead(int k){
		if(k < 0) throw new RuntimeException("Param k invalid");
		Node p = head;
		int i = 0;
		while(p != null && i < k){
			p = p.next;
			i++;
		}
		if(p == null) return null;
		else return p;
	}
	
	/**
	 * @return k-th element (0 based index) from rear
	 * if the list has less than k+1 elements, return null
	 * Time Complexity O(n)
	 */
	public Node kFromRear(int k){
		if(k < 0) throw new RuntimeException("Param k invalid");
		Node p1 = kFromHead(k);
		if(p1 == null) return null;
		p1 = p1.next;
		Node p2 = head;
		while(p1 != null){
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
	
	/**
	 * @return the middle element of the list
	 * if there are even number of elements, return the first one in the middle
	 * Time Complexity O(n)
	 */
	public Node middle(){
		Node fast = head;
		Node slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			if(fast ==null || fast.next == null) break;
			slow = slow.next;
		}
		return slow;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		Node p = head;
		while(p != null){
			sb.append(p.e);
			if(p.next != null){ // if not the last element, append ", "
				sb.append(", ");
			}
			p = p.next;
		}
		sb.append("]"); 
		return sb.toString();
	}
	
	public void print(){
		System.out.println(this);
	}
		
	public static void main(String[] args) {
		
		// create a list
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		
		MyList list = new MyList(head);
		
		// test length of the list
		System.out.println(" The length of list :" + list.length());
		System.out.println();
		
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		// test copy a list
		MyList list2 = list.deepCopy();
		System.out.print(" copied list list2: ");
		System.out.print(list2);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list2)));
		
		
		System.out.println("\n Now reverse copied list list2 by calling reverse1()");
		list2.reverse1();
		
		
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		System.out.print(" copied list list2: ");
		System.out.print(list2);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list2)));
		
		MyList list3 = list.deepCopy();
		System.out.println("\n Now reverse copied list list 3by calling reverse2()");
		list3.reverse2();
		
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		System.out.print(" copied list list3: ");
		System.out.print(list3);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list3)));
		
		System.out.println("\n-------------------------------------------------------------\n");
		// create a new list with odd number of elements
		MyList list_odd = new MyList(new Node(1, new Node(2, new Node(3, new Node(4, 
				                     new Node(5, new Node(6, new Node(7))))))));
		System.out.print("list_odd : "); list_odd.print();
		// create a new list with even number of elements
		MyList list_even = new MyList(new Node(10, new Node(20, new Node(30, new Node(40, 
                new Node(50, new Node(60)))))));
		System.out.print("list_even : "); list_even.print();
		System.out.println();
		// test list kthFromHead and kthFromRear
		for (int k = 0; k < 10; k++) {
			System.out.println(k + "-th element from head of list_odd : " + list_odd.kFromHead(k));
		}
		System.out.println();
		for (int k = 0; k < 10; k++) {
			System.out.println(k + "-th element from rear of list_even : " + list_even.kFromRear(k));
		}
		// test list middle
		System.out.println("\n");
		System.out.println("middle element of list_odd : " + list_odd.middle());
		System.out.println("middle element of list_even : " + list_even.middle());
		
	}
	
	/**
	 * inner static class
	 */
	public static class Node{
		public Node(int e){
			this.e = e;
			this.next = null;
		}
		public Node(int e, Node next){
			this.e = e;
			this.next = next;
		}
		public int e;
		public Node next;
		public String toString(){
			return "MyList.Node(" + e + ")";
		}
	}
}
