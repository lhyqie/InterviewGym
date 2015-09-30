package tree;

public class RemoveNodeinBST {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(3);
		root.right.right.right = new TreeNode(4);
		root.right.right.right.right = new TreeNode(5);
		
		removeNode(root, 3);
		
		inOrder(root);
		
	}
	
	private static  TreeNode par = null;
    
	public static TreeNode removeHelper(TreeNode root, int value){     
        if(root == null) return null;
        if(root.val == value){
            if(root.left == null && root.right == null) return null;
            if(root.left != null) {
                rightMost(root.left).right = root.right;  
                if(par == null) {     // root is removed
                    root = root.left;
                }else{
                    if(par.left == root){
                        par.left = root.left;
                    }else{
                        par.right = root.left;
                    }
                }
            }else if(root.right != null){
                if(par == null) {     // root is removed
                    root = root.right;
                }else{
                    if(par.left == root){
                        par.left = root.right;
                    }else{
                        par.right = root.right;
                    }
                }
            }
        }else if(root.val < value){
            par = root;
            removeHelper(root.right, value);
        }else{
            par = root;
            removeHelper(root.left, value);
        }
        return root;
	}
	
    public static TreeNode removeNode(TreeNode root, int value) {
		par = null;   
        return removeHelper(root, value);
    }
    
    public static void inOrder(TreeNode root){
    	if(root == null) return ;
    	inOrder(root.left);
    	System.out.print(root.val+" ");
    	inOrder(root.right);
    }
    public static TreeNode rightMost(TreeNode root){
        if(root == null) return null;
        while(root.right != null){
            root = root.right;
        }
        return root;
    }
    
	static  class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
}
