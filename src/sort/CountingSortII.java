package sort;

import java.util.Arrays;

/*
 * in-place sort A[]
 */
public class CountingSortII {
	public static void main(String[] args) {
		int A[] = {0,3,3,2,2,3,3,4,4,5,6,5,2,1,4};
	    int max = 0;
	    for(int e: A) { max = Math.max(e, max); }
	    int C[] = new int[max+1]; 
	    for(int e: A) C[e]++;
	    System.out.println(Arrays.toString(C));
	    int j = A.length - 1;
	    int i = max;
	    while(j>=0){
	      while(C[i] > 0){
	         A[j--] = i;
	         C[i]--;
	      }
	      i--;
	    }
	    System.out.println(Arrays.toString(A));
	    // below looks better
	}
	
	/* 
	 * 
	 * http://www.lintcode.com/en/problem/sort-colors-ii/ 
	 *  
	 * public void sortColors2(int[] colors, int k) {
        int cnts[] = new int[k+1];
        for(int color : colors) cnts[color] ++;
        
        for(int i = 2; i <=k ; i++){
            cnts[i] += cnts[i-1];
        }
        
        for(int i = k; i >= 1 ; i--){
            while(cnts[i] > 0){
                colors[--cnts[i]] = i;
            }
        }
    }
	 */
}
