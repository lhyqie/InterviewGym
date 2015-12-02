package hashmap;

import cern.colt.Arrays;

public class FindMissingPositive {
	
	public static void main(String[] args) {
		int A[] = {-1, 100, 7, 1, 2, 0, 100};
		int ret = firstMissingPositive(A);
		System.out.println(ret);
		System.out.println(Arrays.toString(A));
	}
	
	public static int firstMissingPositive(int[] A) {
        int n = A.length;
 
    	for (int i = 0; i < n; i++) {
    		while (A[i] != i + 1) {
    			if (A[i] <= 0 || A[i] >= n)
    				break;
 
                if(A[i]==A[A[i]-1])
                    break;
 
    			int temp = A[i];
    			A[i] = A[temp - 1];
    			A[temp - 1] = temp;
    		}
    	}
 
    	for (int i = 0; i < n; i++){
    		if (A[i] != i + 1){
    			return i + 1;
    		}
    	}	
 
    	return n + 1;
	}
}
