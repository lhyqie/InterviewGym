package array;

import java.util.*;

/*
 * You're given an array of integers(eg [3,4,7,1,2,9,8]) Find the index of values that satisfy A+B = C + D, where
A,B,C & D are integers values in the array. Eg: Given [3,4,7,1,2,9,8] array The following 3+7 = 1+ 9 satisfies
A+B=C+D so print (0,2,3,5)
 */

public class EqualPairSum {
	static class Pair{
		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
		int first;
		int second;
		@Override
		public String toString() {
			return "Pair [first=" + first + ", second=" + second + "]";
		}
		
	}
	public static void main(String[] args) {
		int A[] = {3,4,7,1,2,9,8};
		printEqualPair(A);
		
	}
	
	public static void printEqualPair(int A[]){
		HashMap<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();
		for(int i = 0; i < A.length; i++){
			for(int j = i+1; j < A.length; j++){
				int sum  = A[i] + A[j];
				if(!map.containsKey(sum)){
					map.put(sum, new ArrayList<Pair>());
				}
				map.get(sum).add(new Pair(i, j));
			}
		}
		
		for(Integer sum : map.keySet()){
			if(map.get(sum).size() > 1){
				System.out.print("sum = " + sum +", pairs = " );
				for(Pair pair : map.get(sum)){
					System.out.print(pair+ " ");
				}
				System.out.println();
			}
		}
	}
}
