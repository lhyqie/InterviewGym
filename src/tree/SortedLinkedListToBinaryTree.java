package tree;

import tree.MyBTree;
import list.MyList;


/**
 * http://www.geeksforgeeks.org/sorted-linked-list-to-balanced-bst/
 * or
 * http://www.programcreek.com/2013/01/leetcode-convert-sorted-list-to-binary-search-tree-java/
 * 
 * Given a Singly Linked List which has data members sorted in ascending order. 
 * Construct a Balanced Binary Search Tree which has same data members as the given Linked List.
 *
 *  Method 1 (Simple)
	Following is a simple algorithm where we first find the middle node of list and make it root of the tree to be constructed.
	
	1) Get the Middle of the linked list and make it root.
	2) Recursively do same for left half and right half.
	       a) Get the middle of left half and make it left child of the root
	          created in step 1.
	       b) Get the middle of right half and make it right child of the
	          root created in step 1.
	
	Time complexity: O(nLogn) where n is the number of nodes in Linked List.
	
	See this forum thread for more details.
	
	Method 2 (Tricky) 
	The method 1 constructs the tree from root to leaves. In this method, we construct from leaves to root. 
	The idea is to insert nodes in BST in the same order as the appear in Linked List, so that the tree can be constructed in O(n) time complexity.
	We first count the number of nodes in the given Linked List. Let the count be n. 
	After counting nodes, we take left n/2 nodes and recursively construct the left subtree. 
	After left subtree is constructed, we allocate memory for root and link the left subtree with root. 
	Finally, we recursively construct the right subtree and link it with root.
	While constructing the BST, we also keep moving the list head pointer to next so that we have the appropriate pointer in each recursive call.
	Following is C implementation of method 2. The main code which creates Balanced BST is highlighted.
 */

public class SortedLinkedListToBinaryTree {
	
	private static MyList.Node head = null;

	public static void main(String[] args) {
		MyList list = new MyList(new int[]{1,2,3,4,5,6,7,8,9});
		list.print();
		System.out.println(list.length());
		MyBTree tree = toBinaryTree(list);
		tree.print(MyBTree.PREORDER);
	}
	
	/**
	 * @param list : sorted list
	 * @return binary tree converted
	 */
	public static MyBTree toBinaryTree(MyList list){
		head = list.head;
		MyBTree tree = new MyBTree();
		tree.root = toBinaryTreeHelper(0, list.length()-1);
		return tree;
	}
	
	/**
	 * @param head : head of list corresponding to the subtree
	 * @param n   :  size of list corresponding to the subtree
	 * @return the sub-tree
	 */
	private static MyBTree.Node toBinaryTreeHelper(int start, int end){
		if (start > end) return null;
		int mid = (start + end) / 2;
		MyBTree.Node left = toBinaryTreeHelper(start, mid - 1);
		MyBTree.Node parent = new MyBTree.Node(head.e);
		parent.left = left;
		//move forward
		head = head.next;
		parent.right = toBinaryTreeHelper(mid + 1, end);
		return parent;
	}
}
