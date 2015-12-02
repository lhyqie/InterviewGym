package array;

// crack the coding interview 16.16

public class SubSort {
	public static void main(String[] args) {
		int [] nums = {1,2,4,7,10,11,7,12,6,7,16,18,19};
		subsort(nums);
		System.out.println();
		
		nums = new int[]{1,2,4,7,10,11};
		subsort(nums);
		System.out.println();
		
		nums = new int[]{1,2,4,1,10,11};  //special case ,  middle is []
		subsort(nums);
		System.out.println();
		
		nums = new int[]{1,2,14,1,10,11};  //special case ,  middle is []
		subsort(nums);
	}
	
	public static void subsort(int[] nums){
		int endOfLeft = findEndOfLeftIncreasingSequence(nums);
		if(endOfLeft == nums.length - 1) {
			System.out.println("array is sorted, no need to choose subarray to sort");
			return ;
		}

		int startOfRight = findStartOfRightIncreasingSequence(nums);
		System.out.println("endOfLeft = " + endOfLeft +" , startOfRight = " + startOfRight);
		int min = Integer.MAX_VALUE;  // min max of center part
		int max = Integer.MIN_VALUE; 
		
		for(int i = endOfLeft; i <= startOfRight; i++){   // in case of middle 
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		System.out.println("min = "+min+", max = "+max);
		int sortStart = shrinkLeft(nums, endOfLeft, min);
		int sortEnd = shrinkRight(nums, startOfRight, max);
		System.out.println(""+sortStart+","+sortEnd);
		
	}
	
	public static int findEndOfLeftIncreasingSequence(int[] nums){
		for(int i = 1; i < nums.length; i++){
			if(nums[i-1] > nums[i]) return i - 1;
		}
		return nums.length - 1;
	}
	
	public static int findStartOfRightIncreasingSequence(int[] nums){
		for(int i =  nums.length - 1; i >= 0; i--){
			if(nums[i-1] > nums[i]) return i;
		}
		return 0;
	}
	
	public static int shrinkLeft(int[] nums, int endOfLeft, int min){
		for(int i = endOfLeft; i >= 0; i--){
			if(nums[i] <= min){
				return i + 1;
			}
		}
		return 0;
	}
	
	public static int shrinkRight(int[] nums, int startOfRight, int max){
		for(int i = startOfRight; i < nums.length; i++){
			if(max <= nums[i]){
				return i - 1;
			}
		}
		return nums.length - 1;
	}
}
