package array;

import java.util.*;

// give two cups of size A, B 
// print all size of water can be made from them
public class WaterFromCup {
	
	public static void main(String[] args) {
		printWater(5,3);
	}
	
	public static void printWater(int A, int B){
		Set<Integer> set = new HashSet<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		set.add(A);
		set.add(B);
		list.add(A);
		list.add(B);
		int preSize = -1;
		while(preSize != set.size()){
			preSize = set.size();
			for(int i = 0; i < preSize; i++){
				for(int j = i + 1; j < preSize; j++){
					int newOne = Math.abs(list.get(j)-list.get(i));
					if(!set.contains(newOne)){
						set.add(newOne);
						list.add(newOne);
					}
				}
			}
		}
		System.out.println(list);
	}
}
