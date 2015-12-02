package tree;

/*
 * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 */
public class RestoreBinaryTree {
	
	
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(3);
		root.right = new TreeNode(1);
		
		printInOrder(root);
		System.out.println();
		
		recoverTree(root);
		printInOrder(root);
	}
	
	public static void printInOrder(TreeNode root){
		if(root == null) return;
		printInOrder(root.left);
		System.out.print(root.val+" ");
		printInOrder(root.right);
	} 
	
	
	// use morris traversal
    // O(1)的解法就是
    // Inorder traverse, keep the previous tree node,
    // Find first misplaced node by
    // if ( current.val < prev.val )
    //   Node first = prev;
    
    // Find second by
    // if ( current.val < prev.val )
    //   Node second = current;
    
    // After traversal, swap the values of first and second node. Only need two pointers, prev and current node. O(1) space.

	public static void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev  = null, par = null;
        boolean found = false; 
        
        while(cur != null){
            if(cur.left == null){
                //print cur.val
                // compare cur and cur.right
                if (par != null && par.val > cur.val){
                	if(!found){
                		found = true;
                		first = par;
                	}
                    second = cur;
                }
                par = cur; 
                cur = cur.right;
            }else{
                prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = cur;
                    cur = cur.left;
                }else{  // prev.right != null
                    prev.right = null;
                    //print cur.val;
                    // compare prev and cur
                    if(prev.val > cur.val){
                    	if(!found){
                    		first = prev;
                    		found = true;
                    	}
                        second = cur;
                    }
                    par = cur;
                    cur = cur.right;
                }
            }
        }
        if(first != null && second != null){
            int t= first.val;
            first.val = second.val;
            second.val = t;
        }
    }
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
