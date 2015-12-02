package segtree;

import java.util.Arrays;

// http://www.lintcode.com/en/problem/segmemt-tree-build-ii/				build segment tree with max
// http://www.lintcode.com/en/problem/segment-tree-modify/					change segment tree's leaf value
// http://www.lintcode.com/en/problem/segment-tree-query/					perform range maximum query
// http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/ 	range minimum query can be done similarly
// http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/      range sum query  can be done similarly too
																			//segtree.SegmentTreeNodeSum

// note that start, end is the index of the array on which the tree is built
public class SegmentTreeNodeMax {
	public int start, end, max;
	public SegmentTreeNodeMax left, right;
	public SegmentTreeNodeMax(int start, int end, int max) {
		this.start = start;
		this.end = end;
		this.max = max;
		this.left = this.right = null;
	}
	
	public static SegmentTreeNodeMax build(int[] A) {
        // write your code here
        if( A == null || A.length == 0) return null;
        return buildSubArray(A, 0, A.length - 1);
    }
    
    public static SegmentTreeNodeMax buildSubArray(int[] A, int low, int high){
        if(low > high) return null;
        // base case
        if(low == high) return new SegmentTreeNodeMax(low, high, A[low]);
        SegmentTreeNodeMax root = new SegmentTreeNodeMax(low, high, Integer.MIN_VALUE);  // dummy value for now
        
        int mid = (low + high) >>> 1;
        SegmentTreeNodeMax left = buildSubArray(A, low, mid);
        SegmentTreeNodeMax right = buildSubArray(A, mid + 1, high);
        root.max = Math.max(left.max, right.max);  //should check left ==null  right ==null,
        										   //but it is assured any node has degrees 0 (leaf) 
        										   //or 2 (internal node)
        root.left = left;
        root.right = right;
        return root;
    }
    
    public static void modify(SegmentTreeNodeMax root, int index, int value) {
        // write your code here
        if(root == null) return ;
        if(root.start > index || root.end < index) return ;
        setAndReturn(root, index, value);
    }
    
    // set the leaf value and return it
    public static int setAndReturn(SegmentTreeNodeMax root, int index, int value){
        if(root == null) return Integer.MIN_VALUE;
        if(root.start == index && root.end == index){
            root.max = value;
            return value;
        }
        int mid = (root.start + root.end) >>> 1;
        if(index <= mid){
            root.max = Math.max(setAndReturn(root.left, index, value), root.right.max);    
        }else{   // index > mid
            root.max = Math.max(setAndReturn(root.right, index, value), root.left.max);
        }
        return root.max;
    }
    
    public static int rangeMaximumQuery(SegmentTreeNodeMax root, int start, int end){
    	if(root == null) return Integer.MIN_VALUE;
    	if(end < root.start || start > root.end) return Integer.MIN_VALUE;
    	if(start <= root.start && root.end <= end ) return root.max;
    	return Math.max(rangeMaximumQuery(root.left, start, end), rangeMaximumQuery(root.right, start, end));
    }
    
    public static void main(String[] args) {
		
    	int [] A = {2, 5, 1, 4, 9, 3};
		System.out.println("Build segement tree");
    	SegmentTreeNodeMax root = build(A);
		
    	System.out.println();
    	System.out.println("perform range queries");
		for(int start = 0; start < A.length; start ++){
			for(int end = start; end < A.length; end ++){
				System.out.println("start ="+ start +" end="+end + " sub-array =" 
					+ Arrays.toString(Arrays.copyOfRange(A, start, end+1)) 
					+ " maximum of this range = " + rangeMaximumQuery(root, start, end));
			}
		}
		
		System.out.println();
		System.out.println("now change the tree a bit");
		modify(root, 3, 100);
		A[3] = 100;
		
		System.out.println();
    	System.out.println("perform range queries again");
		for(int start = 0; start < A.length; start ++){
			for(int end = start; end < A.length; end ++){
				System.out.println("start ="+ start +" end="+end + " sub-array =" 
					+ Arrays.toString(Arrays.copyOfRange(A, start, end+1)) 
					+ " maximum of this range = " + rangeMaximumQuery(root, start, end));
			}
		}
	}
}
