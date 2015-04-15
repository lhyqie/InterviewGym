package bit;

/**
 * 
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * @author howie
 *
 */
public class SingleNumber {
	
	public static void main(String[] args) {
		int[] a = {100,100,8,1,2,1,3,2,-10,-10,8};
		System.out.println("single number is the array : " + singleNumber(a));
	}
	
	public static int singleNumber(int a[]){
		int ret = a[0];
		for (int i = 1; i < a.length; i++) {
			ret ^= a[i];
		}
		return ret;
	}
}
