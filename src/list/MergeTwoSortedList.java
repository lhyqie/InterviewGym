package list;

import list.MyList.Node;

public class MergeTwoSortedList {
	public static void main(String[] args) {
		MyList list1 = new MyList(new Node(1, new Node(3, new Node(5, new Node(7, new Node(9)))) ));
		MyList list2 = new MyList(new Node(2, new Node(4, new Node(6, new Node(8, new Node(10)))) ));
		System.out.println("list 1 :" + list1);
		System.out.println("list 2 :" + list2);
		System.out.println("list 3 =  merge(list1, list2)");
		MyList list3 = merge(list1, list2);
		System.out.println("list 3 : " + list3);
	}
	
	/**
	 * 
	 * @param list1 : sorted linkedlist
	 * @param list2 : sorted linkedlist
	 * @return merged sorted linkedlist from list1 and list2
	 */
	public static MyList merge(MyList list1, MyList list2){
		MyList list3 = null;
		
		return list3;
	}
}
