package list;


public class MyList {
	
	private Node head = null;
	
	MyList(){}
	
	MyList(Node head){ 
		this.head = head;
	}
	
	/**
	 * @return the length of the linkedlist
	 */
	public int length(){
		return 0;
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
	 */
	public void reverse1(){
		
	}
	
	/**
	 * in place reverse, using recursion
	 */
	public void reverse2(){
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		Node p = head;
		while(p != null){
			sb.append(p.e);
			if(p.next != null){ // if not the last element, append ", "
				sb.append(", ");
			}else{              // otherwise append "]" 
				sb.append("]"); 
			}
			p = p.next;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		
		MyList list = new MyList(head);
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		MyList list2 = list.deepCopy();
		System.out.print(" copied list : ");
		System.out.print(list2);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list2)));
		
		
		System.out.println("\n Now reverse copied list by calling reverse1()");
		list2.reverse1();
		
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		System.out.print(" copied list : ");
		System.out.print(list2);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list2)));
		
		System.out.println("\n Now reverse copied list by calling reverse2()");
		list2.reverse2();
		
		System.out.print(" original list : ");
		System.out.print(list);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list)));
		
		System.out.print(" copied list : ");
		System.out.print(list2);
		System.out.print(" addrees at JVM : ");
		System.out.println(Integer.toHexString(System.identityHashCode(list2)));
		
	}
	
	/**
	 * inner static class
	 */
	public static class Node{
		Node(int e){
			this.e = e;
			this.next = null;
		}
		Node(int e, Node next){
			this.e = e;
			this.next = next;
		}
		int e;
		Node next;
	}
}
