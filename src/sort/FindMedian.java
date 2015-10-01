package sort;

// O(n)
public class FindMedian {
	public static void main(String[] args) {
		int nums[] = //{0,1,2,3,4,5};
					 {-1,-2,-3,-100,-1,-50};
		             //-100 -50 -3 -2 -1 -1
		System.out.println(median(nums));
	}
	
	public static int median(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1;
        return kthLargestNumber(nums, n - (n-1)/2, 0, n - 1);
    }
  
    public static int kthLargestNumber(int[] nums, int k, int left, int right){
        int n = nums.length;
        int pivotIndex = partition(nums, left, right);
        swap(nums, right, pivotIndex);  
        if(pivotIndex == n - k){
            return nums[pivotIndex];
        }else if(pivotIndex > n - k){
            return kthLargestNumber(nums, k, left, pivotIndex - 1);
        }else{ // pivotIndex < n - k
            return kthLargestNumber(nums, k, pivotIndex + 1, right);
        }
    }
    
    public static int partition(int []nums, int left, int right){
        int pivot = nums[right];
        while(left <= right){
            while(left <= right && nums[left] < pivot) left ++;
            while(left <= right && nums[right] >= pivot) right --;
            if(left < right){
                swap(nums, left, right);
                left ++; right--;
            }
        }
        return right + 1;  // paritition index
    }
    
    private static void swap(int[] nums, int i, int j){
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }
}
