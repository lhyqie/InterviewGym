package dp;

public class LongestIncreasingContinuousSequence {
	
	public static void main(String[] args) {
		//int [] A = {99,55,7,29,80,33,19,23,6,35,40,27,44,74,5,17,52,36,67,32,37,42,18,77,66,62,97,79,60,94,30,2,85,22,26,91,3,16,8,0,48,93,39,31,63,13,71,58,69,50,21,70,61,43,12,88,47,45,72,76};
		int [] A = {8, 4 , 2 ,1};
		longestIncreasingContinuousSubsequence(A);
	}
	
	public static int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        if(A == null || A.length == 0) return 0;
        int n = A.length;
        
        int maxLen = 1;
        int i = 0, j =0;
        for(j =1; j < n; j++){
            if(A[j-1] >= A[j]){
                maxLen = Math.max(maxLen, j - i);
                //System.out.println("i="+i+", j="+j);
                i = j;
            }
        }
        if(i < j){
            maxLen = Math.max(maxLen, j - i);
        }
        //System.out.println(maxLen);
        for(i = n-2, j = n-1;  i >= 0 ; i --){
            if(A[i] <= A[i+1]){
                maxLen = Math.max(maxLen, j - i);
                j = i;
            }
        }
        if(i < j){
            maxLen = Math.max(maxLen, j - i);
        }
        //System.out.println(maxLen);
        return maxLen;
    }
	
}
