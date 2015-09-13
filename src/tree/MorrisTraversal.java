package tree;

import tree.MyBTree.Node;

public class MorrisTraversal {
	
	public static void traverse(MyBTree tree){
		Node cur = tree.root;
		while(cur != null){
			if(cur.left  == null){
				System.out.print(cur.e+ " ");
				cur = cur.right;
			}else{
				Node prev = cur.left;
				while(prev.right != null && prev.right != cur){
					prev = prev.right;
				}
				if(prev.right == null){
					prev.right = cur;  //thread binary tree,  prev is the predecessor of cur
					cur = cur.left;
				}else{ //implies prev.right != null
					prev.right = null;
				    System.out.print(cur.e +" ");
				    cur = cur.right;
				} 
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		int[] arr = {7,5,13,3,6,-9999,24,-9999,-9999,-9999,-9999,-9999,-9999,15,26};
		
		/*
		 *      	7
		 *    	   /  \
		 *        5    13
		 *       / \    \ 
		 *      3   6    24  
		 *              /  \
		 *             15   26  
		 */
		
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
		System.out.println();
		
		System.out.println("MorrisTraversal");
		MorrisTraversal.traverse(tree);
		
	}
}
