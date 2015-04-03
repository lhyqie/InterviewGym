package tree;

import number.Number;

public class MyBTree {

	public static void main(String[] args) {
		/*
		 *      1
		 *     / \
		 *    2   3
		 */
		int[] arr = {1,2,3};
		MyBTree tree = new MyBTree(arr);
		System.out.print("Preoder :");
		tree.print(MyBTree.PREORDER);
		System.out.println();
		System.out.print("Inorder :");
		tree.print(MyBTree.INORDER);
		System.out.println();
		System.out.print("Postorder :");
		tree.print(MyBTree.POSTORDER);
		System.out.println();
		
		/*
		 *      	1
		 *    	   / \
		 *        2   3
		 *             \ 
		 *              4  
		 *             / \
		 *            5   6  
		 */
		int[] arr2 = {1,2,3,-9999,-9999,-9999,4,-9999,-9999,-9999,-9999,-9999,-9999,5,6};
		tree = new MyBTree(arr2);
		System.out.print("Preoder :");
		tree.print(MyBTree.PREORDER);
		System.out.println();
		System.out.print("Inorder :");
		tree.print(MyBTree.INORDER);
		System.out.println();
		System.out.print("Postorder :");
		tree.print(MyBTree.POSTORDER);
		System.out.println(); 
	}
	
	
	public final static int PREORDER = 1;
	public final static int INORDER = 2;
	public final static int POSTORDER = 3;
	
	private final static int TERMINATOR = -9999; 
	
	public Node root;
	
	public MyBTree(){
		root = null;
	}
	
	/**
	 * construct a binary tree from integer array
	 * -9999 indicates a null node 
	 * @param arr : the array
	 */
	public MyBTree(int[] arr){
		root = buildTreeFromArray(arr, 0);
	}
	
	/**
	 * helper function to build binary tree from integer array
	 * -9999 indicates a null node 
	 * @param arr  : the array
	 * @param index : position of building node
	 * @return  the node representing the subtree 
	 */
	private Node buildTreeFromArray(int[] arr, int index){
		if(index >= arr.length || arr[index] == TERMINATOR) return null;
		Node parent = new Node(arr[index]);
		parent.left = buildTreeFromArray(arr, 2 * index + 1);     // left child of a[index] is a[2*index+1] 
		parent.right = buildTreeFromArray(arr, 2 * index + 2);    // right child of a[index] is a[2*index+2]
		return parent;		
	}
	
	public void print(int order){
		switch(order){
			case PREORDER: printPreorder(root); break;
			case INORDER: printInorder(root); break;
			case POSTORDER: printPostorder(root); break;
		}
	}
	
	public void printPreorder(Node root){
		if(root == null) return;
		System.out.print(root.e + " ");
		printPreorder(root.left);
		printPreorder(root.right);
	}
	
	public void printInorder(Node root){
		if(root == null) return;
		printInorder(root.left);
		System.out.print(root.e + " ");
		printInorder(root.right);		
	}

	public void printPostorder(Node root){
		if(root == null) return;
		printPostorder(root.left);
		printPostorder(root.right);
		System.out.print(root.e + " ");
	}

	private static class Node{
		
		public Node left;
		public Node right;
		
		public Node(int e){
			this.e = e;
		}
		
		public Node(int e, Node left, Node right){
			this.e = e;
			this.left = left;
			this.right = right;
		}
		
		private int e;		
	}
}

