package tree;

public class SerializeBinaryTree {
	
	static int buffer[] = new int[100];
	static int nNodes = 0;
	static int idx = 0;
	public static void main(String[] args) {
		//	   		30
		// 		 20     40
		//   10       35   50 
		
		Tree theTree = new Tree(); // make a tree
		theTree.insert(30,0.0);
		theTree.insert(20,0.0);
		theTree.insert(40,0.0);
		theTree.insert(10,0.0);
		theTree.insert(35,0.0);
		theTree.insert(50,0.0);
		
		serialize(theTree);
		for (int i = 0; i < nNodes; i++) {
			System.out.print(buffer[i]+" ");
		}
		System.out.println();
		
		Tree anotherTree = deserialize();
		anotherTree.preOrder(anotherTree.getRoot());
		System.out.println();
		anotherTree.inOrder(anotherTree.getRoot());
		System.out.println();
		anotherTree.postOrder(anotherTree.getRoot());
		System.out.println();
	}
	
	public static void serialize(Tree tree){
		if(tree == null) return;
		Node root = tree.getRoot();
		if(root == null) return;
		nNodes = 0;
		serializeHelper(tree.getRoot());
	}
	
	public static void serializeHelper(Node root){
		if(root == null) {
			buffer[nNodes++] = -1;
		}else{
			buffer[nNodes++] = root.getIData();
			serializeHelper(root.getLeftChild());
			serializeHelper(root.getRightChild());
		}
	}
	
	public static Tree deserialize(){
		Tree tree = new Tree();
		idx = 0;
		Node root = deserializeHelper();
		tree.setRoot(root);
		return tree;
	}
	
	public static Node deserializeHelper(){
		if(idx >= nNodes) return null;
		Node root = null;
		if(buffer[idx] == -1){
			idx++;
			return null;
		}else{
			root = new Node(buffer[idx++], 0.0);
			Node leftChild = deserializeHelper();
			Node rightChild = deserializeHelper();
			root.setLeftChild(leftChild);
			root.setRightChild(rightChild);
		}
		return root;
	}
	
	// ----------------------------------------------------
	static public class Node{
		int iData; // data used as key value
		double fData; // other data
		Node leftChild; // this node's left child
		Node rightChild; // this node's right child
		public Node(int iData, double fData){
			this.iData = iData;
			this.fData = fData;
		}
		public void displayNode()
		{
			
		}
		public String toString(){
			return ""+iData;
		}
		public void setLeftChild(Node leftChild){
			this.leftChild = leftChild;
		}
		public void setRightChild(Node rightChild){
			this.rightChild = rightChild;
		}
		public int getIData(){return iData;}
		public Node getLeftChild(){return leftChild;}
		public Node getRightChild(){return rightChild;}
	}
	
	static public class Tree{
		private Node root; // the only data field in Tree
		public Node getRoot(){return root;}
		public void setRoot(Node root){this.root = root;}
		public int depth(Node root){
			if(root == null) return 0;
			int lDepth = depth(root.leftChild);
			int rDepth = depth(root.rightChild);
			return (lDepth < rDepth ? rDepth : lDepth) +1;
		}
		public Node find(int key)
		{
			Node cur = root;
			while(cur != null){
				if(cur.iData == key) return cur;
				else if(key < cur.iData) cur = cur.leftChild;
				else if(key > cur.iData) cur = cur.rightChild;
			}
			return null;
		}
		
		public void insert(int id, double dd)
		{
			Node node = new Node(id, dd);
			if(root == null) root = node;
			else{
				Node cur = root;
				Node parent = null;
				while(true){
					parent = cur;
					if(node.iData < cur.iData){
						cur = cur.leftChild;
						if(cur == null) {
							parent.leftChild = node;
							break;
						}
					}else{ // >=
						cur = cur.rightChild;
						if(cur == null){
							parent.rightChild = node;
							break;
						}
					}
				}
			}
		}
		
		public void preOrder(Node localRoot){
			if(localRoot != null){
				System.out.print(localRoot.iData+ " ");
				preOrder(localRoot.leftChild);
				preOrder(localRoot.rightChild);
			}
		}
		public void inOrder(Node localRoot){
			if(localRoot != null){
				inOrder(localRoot.leftChild);
				System.out.print(localRoot.iData+ " ");
				inOrder(localRoot.rightChild);
			}
		}
		public void postOrder(Node localRoot){
			if(localRoot != null){
				postOrder(localRoot.leftChild);			
				postOrder(localRoot.rightChild);
				System.out.print(localRoot.iData+ " ");
			}
		}
		
		public boolean delete(int key)
		{
			// step 0, find the node, its parent and the leftChild indicator
			Node delNode = root;
			Node parent = null;
			boolean leftChild = false;
			while(delNode.iData != key){
				parent = delNode;
				if(delNode.iData < key){
					delNode = delNode.rightChild;
					leftChild = false;
				}else if(delNode.iData > key){ 
					delNode = delNode.leftChild;
					leftChild = true;
				}
				if(delNode == null) break;
				
			}
			// step 1 : case 1 not found
			if(delNode == null) return false;
			// step 2 : case 2 delNode has no children
			else if(delNode.leftChild == null && delNode.rightChild == null){ 	
				if(delNode == root){
					root = null;
				}else{
					if(leftChild){
						parent.leftChild = null;
					}else{
						parent.rightChild = null;
					}
				}
			}
			// step 3: case 3 delNode has only one child
			else if(delNode.leftChild == null){
				if(root == delNode) root = delNode.rightChild;
				else if(leftChild) parent.leftChild = delNode.rightChild;
				else parent.rightChild = delNode.rightChild;
			}else if(delNode.rightChild == null){
				if(root == delNode) root = delNode.leftChild;
				else if(leftChild) parent.leftChild = delNode.leftChild;
				else parent.rightChild = delNode.leftChild;
			}
			// step 4: case 4 delNode has both left and right children
			else{
				// find the successor and successorParent
				Node successor = delNode;
				Node successorParent = delNode;
				Node cur = delNode.rightChild;
				while(cur != null){
					successorParent = successor;
					successor = cur;
					cur = cur.leftChild;
				}
				if(successor != delNode.rightChild){
					successorParent.leftChild = successor.rightChild; //delNode must not have leftChild
					successor.rightChild = delNode.rightChild;
				}
				if(delNode == root){
					root = successor;
				}else if(leftChild){
					parent.leftChild = successor;
				}else parent.rightChild = successor;
				
				successor.leftChild = delNode.leftChild;
			}
			return true;
		}
		public Node findSuccessor(Node node){
			if(node == null) return null;
			if(node.rightChild == null) return null;
			Node parentCur = null;
			Node cur = node.rightChild;
			while(cur != null){
				parentCur = cur;
				cur = cur.leftChild;
			}
			return parentCur;
		}
	// various other methods
	} // end class Tree
}
