package segtree;

// http://www.geeksforgeeks.org/interval-tree/

// The start value of an interval is used as key to maintain order in BST

/*
 *  1) Add an interval
	2) Remove an interval
	3) Given an interval x, find if x overlaps with any of the existing intervals.
 */
public class IntervalTreeNode {
	int start;
	int end;
	int max;
	IntervalTreeNode left;
	IntervalTreeNode right;
	
	public IntervalTreeNode(int start, int end){
		this.start = start;
		this.end = end;
		this.max = end;
		this.left = this.right = null;
	}
	
	public static void inorder(IntervalTreeNode root)
	{
	    if (root == null) return;
	 
	    inorder(root.left);
	    System.out.println("[" + root.start + "," + root.end +"] max =" + root.max);
	 
	    inorder(root.right);
	}
	
	public static IntervalTreeNode insert(IntervalTreeNode root, int start, int end){
		if(root == null) 
			return new IntervalTreeNode(start, end);
		if(start < root.start){ // If root's low value is smaller, then new interval goes to left subtree
			root.left = insert(root.left, start, end);
		}else{ 					// else, new node goes to right subtree.
			root.right = insert(root.right, start, end);
		}
		// Update the max value of this ancestor if needed
	    if (root.max < end)
	        root.max = end;
	    return root;
	}
	
	
	public static boolean isOverlap(int s1, int e1, int s2, int e2)
	{
	    if (s1 > e2 || e1 < s2) return false;
	    return true;
	}
	
	// The main function that searches a given interval i in a given an interval Tree. 
	// note this function only returns one overlapped
	public static IntervalTreeNode findOneOverlap(IntervalTreeNode root, int start, int end)
	{
	    // Base Case, tree is empty
	    if (root == null) return null;
	 
	    // If given interval overlaps with root
	    if (isOverlap(root.start, root.end, start, end)){
	    	return root;
	    }       
	 
	    // If left child of root is present and max of left child is
	    // greater than or equal to given interval, then i may
	    // overlap with an interval is left subtree
	    if (root.left != null && root.left.max >= start)
	        return findOneOverlap(root.left, start, end);
	    // Else interval can only overlap with right subtree
	    return findOneOverlap(root.right, start, end);
	}
	
	//note this function print all overlapped intervals
	public static void printAllOverlapIntervals(IntervalTreeNode root, int start, int end){
		if(root == null) return;
		// If given interval overlaps with root
	    if (isOverlap(root.start, root.end, start, end)){
	    	System.out.println("["+root.start+","+root.end+"]");
	    }  
	    // if left tree not null and left tree max larger than start, there is overlap
	    if(root.left != null && root.max >= start){
	    	printAllOverlapIntervals(root.left, start, end);
	    }
	    // if right tree not null and right tree start less than end, there is overlap
	    if(root.right != null && root.right.start <= end){
	    	printAllOverlapIntervals(root.right, start, end);
	    }
	    
	}
	
	public static void main(String[] args) {
		int[][] intervals = {{15,20},{10,30},{17,19},{5,20},{12,15},{30,40}};
		IntervalTreeNode root = null;
		for(int i = 0; i < intervals.length; i++){
			root = insert(root, intervals[i][0], intervals[i][1]);
		}
		System.out.println("Inorder traversal of constructed Interval Tree is : ");
		inorder(root);
		
		System.out.println();
		int[][] searchIntervals = {{6,7},{100,200}, {15,15}, {15,18}};
		for(int i = 0; i < searchIntervals.length; i++){
			System.out.print("search interval [" + searchIntervals[i][0] + ","+ searchIntervals[i][1] +"]");
			IntervalTreeNode res = findOneOverlap(root, searchIntervals[i][0], searchIntervals[i][1]);
		    if (res == null)
		        System.out.println("\t\tNo Overlapping Interval");
		    else
		    	System.out.println("\t\tOverlaps with [" + res.start + ","+ res.end +"]");
		}
		System.out.println("----------------------------");
		System.out.println("print all overlapps with [15,18]");
		printAllOverlapIntervals(root, 15, 18);
		System.out.println();
		System.out.println("print all overlapps with [20,100]");
		printAllOverlapIntervals(root, 20, 100);
	}
}
