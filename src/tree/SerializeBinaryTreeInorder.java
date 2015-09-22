package tree;

public class SerializeBinaryTreeInorder {
	
	public static void main(String[] args) {
		//TODO
	}
	
	
	static class TreeNode{
		TreeNode(char val){
			this.val = val;
		}
		char val;
		TreeNode left;
		TreeNode right;
	}
	
	public static void inorder(TreeNode root){
		if(root == null) return ;
		inorder(root.left);
		System.out.print(root.val+ " ");
		inorder(root.right);
	}
}
