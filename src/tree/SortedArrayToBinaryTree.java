package tree;

import tree.MyBTree.Node;

public class SortedArrayToBinaryTree {
	
	public static void main(String[] args) {
		/*
		 *                5
		 *              /   \        
		 *             /     \     
		 *            2       7
		 *           / \     / \
		 *          1   3   6   8
		 *               \       \
		 *                4       9
		 */
		
		int [] arr = {1,2,3,4,5,6,7,8,9};
		MyBTree tree = toBinaryTree(arr);
		tree.print(MyBTree.PREORDER);
		
		
	}
	
	/**
	 * @param arr : sorted array
	 * @return  binary tree
	 */
	public static MyBTree toBinaryTree(int arr[]){
		MyBTree tree = new MyBTree();
		tree.root = toBinaryTreeHelper(arr, 0, arr.length - 1); 
		return tree;
	}
	
	private static Node toBinaryTreeHelper(int arr[], int begin, int end){
		if(begin > end) return null;
		if(begin == end) return new Node(arr[begin]);
		int mid  = (begin + end)/2;
		Node parent = new Node(arr[mid]);
		parent.left = toBinaryTreeHelper(arr, begin, mid - 1);
		parent.right = toBinaryTreeHelper(arr, mid + 1, end);
		return parent;
	}
	
}
