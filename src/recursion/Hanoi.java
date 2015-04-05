package recursion;

public class Hanoi {
	public static void main(String[] args) {
		hanoi(3, 'A', 'B', 'C');
	}
	
	/**
	 * @param n
	 * @param source : n disks are on the source tower a  
	 * @param helper :  the tower the top n - 1 disks are moved to
	 * @param target :  target tower
	 */
	public static void hanoi(int n,char source, char helper, char  target){
		if( n== 1){
			System.out.println("move " + n + " from " + source + " to " + target);
		}else{
			hanoi(n-1, source, target, helper);  // move n-1 disks from source to helper, using target as "helper" 
			System.out.println("move " + n + " from " + source + " to " + target);
			hanoi(n-1, helper, source, target);  // move n-1 disks from helper to target, using source as "helper"
		}
	}
}
