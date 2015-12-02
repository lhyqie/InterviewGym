package segtree;

import java.util.Arrays;

//note that start, end is the index of the array on which the tree is built
public class SegmentTreeNodeSum {
	public int start, end, sum;
	public SegmentTreeNodeSum left, right;
	public SegmentTreeNodeSum(int start, int end, int sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
		this.left = this.right = null;
	}
	
	public static SegmentTreeNodeSum build(int[] A) {
        // write your code here
        if( A == null || A.length == 0) return null;
        return buildSubArray(A, 0, A.length - 1);
    }
    
    public static SegmentTreeNodeSum buildSubArray(int[] A, int low, int high){
        if(low > high) return null;
        // base case
        if(low == high) return new SegmentTreeNodeSum(low, high, A[low]);
        SegmentTreeNodeSum root = new SegmentTreeNodeSum(low, high, Integer.MIN_VALUE);  // dummy value for now
        
        int mid = (low + high) >>> 1;
        SegmentTreeNodeSum left = buildSubArray(A, low, mid);
        SegmentTreeNodeSum right = buildSubArray(A, mid + 1, high);
        root.sum = left.sum + right.sum;  //should check left ==null  right ==null,
        										   //but it is assured any node has degrees 0 (leaf) 
        										   //or 2 (internal node)
        root.left = left;
        root.right = right;
        return root;
    }
    
    public static void modify(SegmentTreeNodeSum root, int index, int value) {
        // write your code here
        if(root == null) return ;
        if(root.start > index || root.end < index) return ;
        setAndReturn(root, index, value);
    }
    
    // set the leaf value and return it
    public static int setAndReturn(SegmentTreeNodeSum root, int index, int value){
        if(root == null) return Integer.MIN_VALUE;
        if(root.start == index && root.end == index){
            root.sum = value;
            return value;
        }
        int mid = (root.start + root.end) >>> 1;
        if(index <= mid){
            root.sum = setAndReturn(root.left, index, value) + root.right.sum;    
        }else{   // index > mid
            root.sum = setAndReturn(root.right, index, value) + root.left.sum;
        }
        return root.sum;
    }
    
    public static int rangeSumQuery(SegmentTreeNodeSum root, int start, int end){
    	if(root == null) return 0;
    	if(end < root.start || start > root.end) return 0;
    	if(start <= root.start && root.end <= end ) return root.sum;
    	return rangeSumQuery(root.left, start, end)+ rangeSumQuery(root.right, start, end);
    }
    
    public static void main(String[] args) {
		
    	int [] A = {2, 5, 1, 4, 9, 3};
		System.out.println("Build segement tree");
    	SegmentTreeNodeSum root = build(A);
		
    	System.out.println();
    	System.out.println("perform range sum queries");
		for(int start = 0; start < A.length; start ++){
			for(int end = start; end < A.length; end ++){
				System.out.println("start ="+ start +" end="+end + " sub-array =" 
					+ Arrays.toString(Arrays.copyOfRange(A, start, end+1)) 
					+ " sum of this range = " + rangeSumQuery(root, start, end));
			}
		}
		
		System.out.println();
		System.out.println("now change the tree a bit");
		modify(root, 3, 100);
		A[3] = 100;
		
		System.out.println();
    	System.out.println("perform range sum queries again");
		for(int start = 0; start < A.length; start ++){
			for(int end = start; end < A.length; end ++){
				System.out.println("start ="+ start +" end="+end + " sub-array =" 
					+ Arrays.toString(Arrays.copyOfRange(A, start, end+1)) 
					+ " sum of this range = " + rangeSumQuery(root, start, end));
			}
		}
	}
}
