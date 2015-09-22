package tree;

public class InorderSuccessor {
	
	public static void inorder(TreeNode root){
		if(root == null) return ;
		inorder(root.left);
		System.out.print(root.val+ " ");
		inorder(root.right);
	}
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		public String toString(){return ""+val;}
	}
	
	public static void main(String[] args) {
		/*
		        6
		     2     8
		   0   4  7 9
		      3
		
		 */
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(2);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		root.left.right.left = new TreeNode(3);
		
		//TreeNode root = new TreeNode(2);
		//root.left = new TreeNode(1);
		
		inorder(root);
		System.out.println();
		TreeNode ret = inorderSuccessor(root, root.left);
		System.out.println(ret);
	}
	
	public static TreeNode leftMost(TreeNode root){
		if(root == null) return null;
		TreeNode res = root;
    	while(res.left != null) res = res.left;
    	return res;
	}
	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p == null) return null;
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                if(cur == p) {
                	if(cur.right.left == p) return cur.right;
                	else return cur.right; //leftMost(cur.right);
                }
                cur = cur.right;
            }else{
                TreeNode prev = cur.left;
                while(prev.right != null && prev.right != cur){
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = cur;  //thread to successor
                    cur = cur.left;
                }else{ //undo threading
                    if(cur == p) {
                    	return leftMost(cur.right);
                    }
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return null;
    }
}
