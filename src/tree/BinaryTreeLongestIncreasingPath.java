package tree;


public class BinaryTreeLongestIncreasingPath {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
		public String toString(){return ""+val;}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.left.left = new TreeNode(3);
		root.left.left.left.left = new TreeNode(7);
		root.left.left.left.right = new TreeNode(1);//new TreeNode(5);
		root.left.left.left.right.right = new TreeNode(6);
		root.left.left.left.right.right.left = new TreeNode(7);
		root.left.left.left.right.right.right = new TreeNode(8);
		root.left.left.left.right.right.right.right = new TreeNode(9);
		
		System.out.println(LIP(root));
	}
	
	private static int maxL = 0;
	
	public static int LIP(TreeNode root){
		if(root == null) return 0;
		maxL = 0;
		LIP_helper(root, 1);
		return maxL;
	}
	
	public static void LIP_helper(TreeNode root, int L){
		if(root == null) return;
		maxL = Math.max(maxL, L);
		if(root.left != null){
			if(root.left.val <= root.val)
				LIP_helper(root.left, 1);
			else
				LIP_helper(root.left, L+1);
		}
		
		if(root.right != null){
			if(root.right.val <= root.val)
				LIP_helper(root.right, 1);
			else
				LIP_helper(root.right, L+1);
		}
	}
	
}
