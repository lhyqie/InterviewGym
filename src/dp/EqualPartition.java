package dp;

//you have an array of integers, you are going to put some of them into one bucket 
//and the rest of them into another bucket. 
//where the sum of one bucket equals the sum of the second

public class EqualPartition {
	
	//refer to dp.ClosestParition 
	//(this is a special case, solving CloestParition can solve EqualParition)
	
	public static void main(String[] args) {
//		int a[] = new int[6];
//		for (int i = 0; i < a.length; i++) {
//			a[i] = (int)(Math.random()*10);
//		}
		
//		int a [] ={3, 1, 1, 2, 2, 1};
		int a [] = {2,5,1,11,3,5,10,24,555,123,985,1337,9,13,24,89,19,27};
		System.out.println("a can be partitioned into two sets that  the sum of one bucket equals the sum of the second ?");
		System.out.println(hasEqualPartition(a));
	}
	
	public static boolean hasEqualPartition(int []a){
		int n = a.length;
		int N = 0;
		for(int e : a) N+=e; // sum of a
		boolean P[][] = new boolean[N/2+1][n+1];
		//goal is P(N/2, n)
		//p(i, j) be True if a subset of { a1, ..., aj } sums to i and False otherwise.
		//recursion:
		// p(i, j) is True if either p(i, j - 1) is True or if p(i - aj, j - 1) is True
		// p(i, j) is False otherwise
		for (int j = 0; j <= n; j++) {
			P[0][j] = true;
		}
		for (int i = 1; i <= N/2; i++) {
			P[i][0] = false;  //note P[0][0] is true
		}
		for (int i = 1; i <= N/2; i++) {
			for (int j = 1; j <= n; j++) {
				if(i-a[j-1] >=0 ){
					P[i][j] = P[i][j-1] || P[i-a[j-1]][j-1];
				}
				else{
					P[i][j] = P[i][j-1];
				}
			}
		}
		return P[N/2][n];
	}
}
