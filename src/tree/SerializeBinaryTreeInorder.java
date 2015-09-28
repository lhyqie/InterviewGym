package tree;

import java.util.*;

public class SerializeBinaryTreeInorder {
	
	static class TreeNode{
		TreeNode(int val){
			this.val = val;
		}
		int val;
		TreeNode left;
		TreeNode right;
	}
	

	public static void inorder(TreeNode root){
		if(root == null) return ;
		inorder(root.left);
		System.out.print(root.val+ " ");
		inorder(root.right);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		System.out.print("Inorder of original tree : ");
		inorder(root);
		System.out.println();
		
		String ser = serialize(root);
		System.out.println("serailized tree :" + ser);
		TreeNode root2 = deserialize(ser);
		System.out.print("Inorder of deserialized tree : ");
		inorder(root2);
		System.out.println();
	}
	
	public static final TreeNode NULL = new TreeNode(Integer.MIN_VALUE);
    
    public static String serialize(TreeNode root) {
        // write your code here
        if(root == null) return "";
        List<String> buffer = new ArrayList<String>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            if(top == NULL){
                buffer.add("#");
            }else{
                buffer.add(""+top.val);
                if(top.left == null) q.offer(NULL);
                else q.offer(top.left);
                if(top.right == null) q.offer(NULL);
                else q.offer(top.right);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < buffer.size(); i++){
            sb.append(buffer.get(i));
            if( i < buffer.size() - 1) sb.append(",");
        }
        return sb.toString();
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public static TreeNode deserialize(String data) {
        // write your code here
        if(data.length() == 0) return null;
        String[] tokens = data.split(",");
        int p = 0;
        TreeNode root = new TreeNode(Integer.parseInt(tokens[p++]));
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            if(p == tokens.length) break;
            if(tokens[p].equals("#")){
                top.left = null;
            }else{
                top.left = new TreeNode(Integer.parseInt(tokens[p]));
                q.offer(top.left);
            }
            p++;
            if(tokens[p].equals("#")){
                top.right = null;
            }else{
                top.right = new TreeNode(Integer.parseInt(tokens[p]));
                q.offer(top.right);
            }
            p++;
        }
        return root;
    }
    
	
	
	
}
