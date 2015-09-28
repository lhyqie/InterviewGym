package dp;

/*
 *  determine wether a value can be combined using any number of  a set of coins  
 */
public class CoinCombination {
	 //static int coins [] = { 25, 10, 5, 1};
	static int coins [] = {1, 2, 3};
	 public static void main(String[] args) {
//		 for (int i = 0; i <= 26; i++) {
//			 boolean res = canCombine(i);
//			 System.out.println(i+" -> "+res);
//		 }
		 //printAllCombination(coins, 100);
		 printAllCombination(coins, 5);
	 }
	 
	 /*
	  *  coins in decreasing order,
	  *  solution by enumeration, not dp
	  */
	 public static void printAllCombination(int []coins, int total){
		 int [] counts = new int[coins.length];
		 System.out.println("All combination of coins to form "+ total+ " are:");
		 printAllCombinationHelper(coins, counts, 0, total);
	 }
	 public static void printAllCombinationHelper(int []coins, int counts[], int curIndex, int total){
		 if(curIndex >= coins.length){
			 for (int i = 0; i < counts.length; i++) {
				System.out.print("+"+coins[i]+"*"+counts[i]);
			}
			System.out.println();
			return;
		 }
		 else if(curIndex == coins.length - 1){
			 if(total % coins[curIndex] == 0){
				 counts[curIndex] = total/coins[curIndex];
				 printAllCombinationHelper(coins, counts, curIndex+1, 0);
			 }
		 }else{//<
			 for (int i = 0; i <= total/coins[curIndex]; i++) {
				counts[curIndex] = i;
				printAllCombinationHelper(coins, counts, curIndex+1, total - counts[curIndex] * coins[curIndex]);
			}
		 }
	 }
	 public static boolean canCombine(int cent){
		  boolean []can = new boolean[cent+1];
		 
		  for (int i = 0; i < can.length; i++) {
			  can[i] = false;
		  }
		  can[0]= true;
		  for (int i = 0; i <= cent; i++) {
			  for (int j = 0; j < coins.length; j++) {
				  if( i-coins[j]>=0 && i-coins[j] <=cent && can[i-coins[j]] == true)
					  can[i] = true;
			  }
		  }
		  
		  return can[cent];
	 }
}
