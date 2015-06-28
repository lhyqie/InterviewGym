package tree;

import java.util.LinkedList;
import java.util.Queue;



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
		System.out.print("Levelorder :");
		tree.print(MyBTree.LEVELORDER);
		tree.visualize();
		System.out.println("depth = "+ tree.depth());
		System.out.println();
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
		System.out.print("Levelorder :");
		tree.print(MyBTree.LEVELORDER);
		System.out.println();
		tree.visualize();
		System.out.println("depth = "+ tree.depth());
		
		
	}
	
	
	public final static int PREORDER = 1;
	public final static int INORDER = 2;
	public final static int POSTORDER = 3;
	public final static int LEVELORDER = 4;
	
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
			case LEVELORDER: printLevelorder(root); break;
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
	
	public void printLevelorder(Node root){
		if(root == null) return;
		Queue<Node> Q = new LinkedList<Node>();
		Q.offer(root);
		while(!Q.isEmpty()){
			Node t = Q.poll();
			System.out.print(t.e + " ");
			if(t.left != null) Q.offer(t.left);
			if(t.right != null) Q.offer(t.right);
		}
	}
	
	public void visualize(){
		Visualizer.visualize(this);
	}
	
	public int depth(){
		return depthHelper(this.root);
	}
	
	public int depthHelper(Node p){
		if(p == null) return 0;
		int leftDepth = depthHelper(p.left);
		int rightDepth = depthHelper(p.right);
		return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;
	}
	
	public static class Node{
		public Node left;
		public Node right;
		public int e;
		
		public Node(int e){
			this.e = e;
		}
		
		public Node(int e, Node left, Node right){
			this.e = e;
			this.left = left;
			this.right = right;
		}
	}
}

