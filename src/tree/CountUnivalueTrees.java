package tree;

import tree.BSTIterator.TreeNode;

public class CountUnivalueTrees {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
		public String toString(){return ""+val;}
	}
	
	static int count = 0;
	    
    public static int countUnivalSubtrees(TreeNode root) {
        count = 0;    
        univalue(root);
        return count;
    }
    
    public static boolean univalue(TreeNode root){
        if(root == null) return false;
        if(root.left == null && root.right == null) {
            count ++ ;
            return true; // leave
        }
        boolean ret = true;
        if(root.left != null){
        	if(!univalue(root.left)) ret = false;
        	if(root.left.val != root.val) ret = false;
        }
        if(root.right != null){
        	if(!univalue(root.right)) ret = false;
        	if(root.right.val != root.val) ret = false;
        }
        if(ret) count ++ ;
        return ret;
    }
    
    public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(5);
		root.right.right = new TreeNode(5);
		
		System.out.println(countUnivalSubtrees(root));
	}
	
}
