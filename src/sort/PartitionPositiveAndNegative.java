package sort;

import cern.colt.Arrays;

public class PartitionPositiveAndNegative {
	public static void main(String[] args){
		int [] a = null;
		int index  = - 1;
		
		a = new int[]{1,3,4,-2,-10,-1,1,3,-2,3}; // test without 0
		System.out.println(Arrays.toString(a));
		index = paritionPosAndNeg(a, 0, a.length - 1);
		System.out.println("the split position is at : " + index);
		System.out.println(Arrays.toString(a));
		System.out.println();
		
		a = new int[]{1,3,0,4,-2,-10,-1,0, 1,3,-2,3}; // test with 0's
		System.out.println(Arrays.toString(a));
		index = paritionPosAndNeg(a, 0, a.length - 1);
		System.out.println("the split position is at : " + index);
		System.out.println(Arrays.toString(a));
		
		
	}
	
	static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

	/**
	 * partition array into two parts where the left part is < 0, right part is >= 0
	 * the split is indicated by the returning index
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	static int paritionPosAndNeg(int []a, int left, int right){
//		int pivot = 0;
//		while(left < right){
//			while(left < right && a[left] < pivot) left ++ ;
//			while(left < right && a[right] >= pivot) right -- ;
//			if(left >= right) break;
//			swap(a, left, right);
//			System.out.println("temp a =" + Arrays.toString(a));
//		}
//		return left;
		
		int pivot = 0;
		while(left <= right){
			while(left <= right && a[left] < pivot) left++;
			while(left <= right && a[right] >= pivot) right--;
			if(left < right){
				swap(a, left, right);
			}
		}
		return right + 1;
	}
}
