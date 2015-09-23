package dp;

public class EditDistance {
	
	private static int distance[][] = null;
	
	private static enum Modification{
		LEFT, UP, DIAG
	};
	
	private static Modification modifications[][] = null;
	EditDistance(){
		
	}
	
	public static int minDistance(String str1, String str2){
		// init distance[][]
		int m = str1.length() + 1;
		int n = str2.length() + 1;
		modifications = new Modification[m][];
		distance = new int[m][];
		for (int i = 0; i < distance.length; i++) {
			distance[i] = new int[n];
			modifications[i] =new Modification[n];
		}
		for (int i = 0; i < m; i++) {
			distance[i][0] = i;
		}
		for (int j = 0; j < n; j++) {
			distance[0][j] = j;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				
				int cand1 = distance[i][j-1] + 1;
				int cand2 = distance[i-1][j] + 1;
				int cand3 = (str1.charAt(i-1) == str2.charAt(j-1)) ? distance[i-1][j-1] : distance[i-1][j-1] + 2;
				
				distance[i][j] = Math.min(cand1, Math.min(cand2, cand3));
				
				if(distance[i][j] == cand1){
					modifications[i][j] = Modification.LEFT;
				}else if(distance[i][j] == cand2){
					modifications[i][j] = Modification.UP;
				}else if(distance[i][j] == cand3){
					modifications[i][j] = Modification.DIAG;
				}
			}
		}
		System.out.println("---------------------dp matrix--------------------");
		System.out.println("\t");
		for (int j = 0; j < n; j++) {
			if(j==0) System.out.print("\t \t");
			else System.out.print(str2.charAt(j-1) + "\t");
		}
		System.out.println();
		for (int i = 0; i < m ; i++) {
			if(i==0) System.out.print(" \t");
			else System.out.print(str1.charAt(i-1)+"\t");
			for (int j = 0; j < n; j++) {
				System.out.print(distance[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------choices-----------------------");
		System.out.println("\t");
		for (int j = 0; j < n; j++) {
			if(j==0) System.out.print("\t \t");
			else System.out.print(str2.charAt(j-1) + "\t");
		}
		System.out.println();
		for (int i = 0; i <m ; i++) {
			if(i==0) System.out.print(" \t");
			else System.out.print(str1.charAt(i-1)+"\t");
			for (int j = 0; j < n; j++) {
				System.out.print(modifications[i][j] + "\t");
			}
			System.out.println();
		}
		
		return distance[m-1][n-1];
	}
	
	
	public static void main(String[] args) {
		minDistance("intention","execution");
		System.out.println();
		minDistance("deem","demure");
	}
}
