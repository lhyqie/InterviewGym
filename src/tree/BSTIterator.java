package tree;

import java.util.*;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

public class BSTIterator {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
		public String toString(){return ""+val;}
	}
		
	private Stack<TreeNode> s = new Stack<TreeNode>();
	private Stack<TreeNode> s2 = new Stack<TreeNode>();
	
	private void exploreLeftChildren(TreeNode root) {
		TreeNode current = root;
		while (current.left != null) {
			s.push(current.left);
			current = current.left;
		}
	}

	public BSTIterator(TreeNode root) {
		if (root != null) {
			s.push(root);
			exploreLeftChildren(root);
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !s.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode current = s.pop();
		int ret = current.val;
		if(current.right != null) {
			s.push(current.right);
			exploreLeftChildren(current.right);
		}
		return current.val;
	}
	
	public void in_order_recursive(TreeNode root){
		if(root == null) return;
		in_order_recursive(root.left);
		System.out.print(root.val + " ");
		in_order_recursive(root.right);
	}
	
	public void in_order_traversal_iterative(TreeNode root) {
		TreeNode current = root;
		while (!s2.isEmpty() || current != null) {
			if (current != null) {
				s2.push(current);
				current = current.left;
			} else {
				current = s2.pop();
				System.out.print(current.val + " ");
				current = current.right;
			}
		}
	}

	public static void main(String[] args) {
		/*
				         5
					2        8 
				 1     4   6    9
				     3      7
				
		*/
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		root.right.left.right = new TreeNode(7);

		/**
		 * Your BSTIterator will be called like this: BSTIterator i = new
		 * BSTIterator(root); while (i.hasNext()) v[f()] = i.next();
		 */
		BSTIterator iter = new BSTIterator(root);
		iter.in_order_recursive(root);
		System.out.println();
		iter.in_order_traversal_iterative(root);
		System.out.println();
		while (iter.hasNext()) {
			System.out.print(iter.next()+ " ");
		}
		
	}
}
