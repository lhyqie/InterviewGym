package segtree;

import java.util.Arrays;

//note that start, end are the value range which may not be present in the array on which the tree is built

public class SegmentTreeNodeCount {
	public int start, end, count;
	public SegmentTreeNodeCount left, right;
	public SegmentTreeNodeCount(int start, int end, int count) {
		this.start = start;
		this.end = end;
		this.count = count;
		this.left = this.right = null;
	}
	
	public static void main(String[] args) {
		int A[] = {1,2,7,8,5}; //{0, 2, 3};
		System.out.println("Array : " + Arrays.toString(A));
		System.out.println("Build segement tree");
		SegmentTreeNodeCount root = build(Arrays.copyOfRange(A,0, A.length));  // don't destroy A
		
		System.out.println();
    	System.out.println("perform range count queries");
		for(int start = 0; start <=8; start ++){
			for(int end = start; end <=8; end ++){
				System.out.println("start ="+ start +" end="+end
					+ " count of this range = " + query(root, start, end));
			}
		}
		
		System.out.println();
		System.out.println("count # of numbers smaller before itself II :");
		for(int a : A){
			System.out.println("" + a + " : " + query(root, 0, a-1));
		}
	}
	
	// https://leetcode.com/problems/search-for-a-range/
	public static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1, -1};
        int r1, r2;
        // 1. find  first index of target
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(nums[mid] >= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        // nums[right +1] is the candidate
        if(right + 1 >= nums.length || nums[right + 1] != target){
            return new int[]{-1, -1};
        }else{
            r1 = right + 1;
        }
        //System.out.println(r1);
        
        // 2. find last index of target
        left = 0;
        right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if(nums[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        // nums[left - 1] is the candidate
        r2 = left - 1;
        return new int[]{r1, r2};
    }
	
	public static SegmentTreeNodeCount build(int[] A) {
        // first sort the array
		Arrays.sort(A);
        if( A == null || A.length == 0) return null;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
        	min = Math.min(min, A[i]);
        	max = Math.max(max, A[i]);
        }
        return buildSubArray(A, min, max);
    }
    
    public static SegmentTreeNodeCount buildSubArray(int[] A, int min, int max){
        if(min > max) return null;
        // base case
        if(min == max) {
        	// find the # of element in A that equals min or max
        	int[] tmp = searchRange(A, min);
        	if(tmp[0] == -1 && tmp[1] == -1) return new SegmentTreeNodeCount(min, max, 0);
        	else return new SegmentTreeNodeCount(min, max, tmp[1] - tmp[0] + 1);
        }
        SegmentTreeNodeCount root = new SegmentTreeNodeCount(min, max, 0);  // dummy value for now
        
        int mean = (min + max) >>> 1;
        SegmentTreeNodeCount left = buildSubArray(A, min, mean);
        SegmentTreeNodeCount right = buildSubArray(A, mean + 1, max);
        root.count = left.count +  right.count;  //should check left ==null  right ==null,
        									   //but it is assured any node has degrees 0 (leaf) 
        									   //or 2 (internal node)
        root.left = left;
        root.right = right;
        return root;
    }
    
    public static int query(SegmentTreeNodeCount root, int start, int end) {
        // write your code here
        if(root == null) return 0;
        if(start > end) return 0;
        if(root.start > end || root.end < start) return 0;
        if(start <= root.start && root.end <= end) return root.count;
        
        int mid = (root.start + root.end) >>> 1;
        if(start <= mid && mid <= end){
            int leftSum = query(root.left, start, mid);
            int rightSum = query(root.right, mid + 1, end);
            return leftSum + rightSum;
        }else if(start > mid){
            return query(root.right, start, end);
        }else{ // mid > end
            return query(root.left, start, end);
        }
    }
}
