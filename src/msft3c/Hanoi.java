package msft3c;

import java.io.IOException;
import java.util.List;

public class Hanoi {
	public static void main(String[] args) throws IOException {
		
		List<String> lines = PalindromeAnagram.readFromFile("msft3c/file3.txt");
		for(String line: lines)
		{
			String[] tokens = line.split(",");
			int n = Integer.parseInt(tokens[0]);
			int from = Integer.parseInt(tokens[1]);
			int to = Integer.parseInt(tokens[2]);
			if(from == to){
				System.out.println(0);
			}else{
				int ret = h(n, from , to, -1);
				System.out.println(ret);
			}
			
		}
		
		
	}
	
	public static int h(int n, int from, int to, int helper){
		if( n== 1){
			return 1;
		}
		else{
			int ret = 1;
			ret += h(n-1, from, to, helper); 
			ret += h(n-1, helper, from, to);
			return ret;
		}
		
	}
	
	/**
	 * @param n
	 * @param source : n disks are on the source tower a  
	 * @param helper :  the tower the top n - 1 disks are moved to
	 * @param target :  target tower
	 */
	public static void hanoi(int n,char source, char helper, char  target){
		if( n== 1){
			//System.out.println("move " + n + " from " + source + " to " + target);
		}else{
			hanoi(n-1, source, target, helper);  // move n-1 disks from source to helper, using target as "helper" 
			//System.out.println("move " + n + " from " + source + " to " + target);
			hanoi(n-1, helper, source, target);  // move n-1 disks from helper to target, using source as "helper"
		}
	}
}
