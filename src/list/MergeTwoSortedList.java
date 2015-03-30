package list;

import list.MyList.Node;

public class MergeTwoSortedList {
	public static void main(String[] args) {
		MyList list1 = new MyList(new Node(1, new Node(2, new Node(3, new Node(7))) ));
		MyList list2 = new MyList(new Node(2, new Node(4, new Node(6, new Node(8, new Node(10)))) ));
		System.out.println("list 1 :" + list1);
		System.out.println("list 2 :" + list2);
		System.out.println("list 3 =  merge(list1, list2)");
		MyList list3 = merge(list1, list2);
		System.out.println("list 3 : " + list3);
		System.out.println("since list 3 is in-place merge, list1 and list2 are dirty");
		System.out.println("list 1 :" + list1);
		System.out.println("list 2 :" + list2);
	}
	
	/**
	 * In place merge, list1 and list2 will be destroyed
	 * @param list1 : sorted linkedlist
	 * @param list2 : sorted linkedlist
	 * @return merged sorted linkedlist from list1 and list2
	 */
	public static MyList merge(MyList list1, MyList list2){
		Node p1 = list1.head;
		Node p2 = list2.head;
		Node head = new Node(-1);  //dummy node which will be thrown away in the end
		Node p3 = head;
		while( p1 != null && p2 != null){
			if(p1.e < p2.e){
				p3.next = p1;
				p1 = p1.next;
			}else{
				p3.next = p2;
				p2 = p2.next;
			}
			p3 = p3.next;
		}
		while(p1 != null){
			p3.next = p1;
			p1 = p1.next;
			p3 = p3.next;
		}
		while(p2 != null){
			p3.next = p2;
			p2 = p2.next;
			p3 = p3.next;
		}
		return new MyList(head.next);  // dummy node is escaped  
	}
}
