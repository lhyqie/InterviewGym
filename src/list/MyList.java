package list;

public class MyList {
	
	private Node head = null;
	
	MyList(){}
	
	MyList(Node head){ 
		this.head = head;
	}
	
	public void print(){
		// mimic toString() method, print the list 
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
			sb.append(p.e+",");
			p = p.next;
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		
		MyList list = new MyList(head);
		System.out.println(list);
	
		list.print();
		
	}
	
	/**
	 * inner static class
	 */
	private static class Node{
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
