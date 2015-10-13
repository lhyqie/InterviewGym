package dp;

public class MaximumProductSubArray {
	public static int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// at least choose one element
		int maxPos = nums[0], maxNeg = nums[0], maxRes = nums[0]; 
		for (int i = 1; i < nums.length; i++) {
			int maxP = maxPos, maxN = maxNeg;
			maxPos = Math.max( Math.max(nums[i], nums[i] * maxP), nums[i] * maxN);
			maxNeg = Math.min( Math.min(nums[i], nums[i] * maxP), nums[i] * maxN);
			maxRes = Math.max(maxPos, maxRes);
		}
		return maxRes;
	}
	
	public static void main(String[] args) {
		System.out.println( maxProduct(new int[]{3, -1, 4}) );
		
		System.out.println( maxProduct(new int[]{7, -2, -4}) );
		
		System.out.println( maxProduct(new int[]{-4, -3, -2}) );
	}
}
