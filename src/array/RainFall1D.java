package array;

/**
 * Given an array of integers representing heights of walls in 1D array
 * 
 * what is the maximum water hold in the puddles after a heavy rain 
 * 
 */
public class RainFall1D {
	
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] arr = {2, 5, 1, 2, 3, 4, 7, 7, 6}; 
		System.out.println(calculateVolume(arr));
		
		int[] arr2 = {2, 7, 2, 7, 4, 7, 1, 7, 3, 7}; 
		System.out.println(calculateVolume(arr2));
	}
	
	public static int calculateVolume(int[] arr) {
		
		int leftMax = 0;
		int rightMax = 0;
		int left = 0;
		int right = arr.length - 1;
		int volume = 0;
		
		while(left < right) {
			if(arr[left] > leftMax) {
				leftMax = arr[left];
			}
			if(arr[right] > rightMax) {
				rightMax = arr[right];
			}
			if(leftMax >= rightMax) {
				volume += rightMax - arr[right];
				right--;
			} else {
				volume += leftMax - arr[left];
				left++;
			}
		}
		return volume;
	}
	
}
