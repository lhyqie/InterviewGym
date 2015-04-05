package recursion;

public class Factorial {
	
	public static void main(String[] args) {
		System.out.println(factorial(5));
	}
	
	public static int factorial(int n){
		if(n < 0 ) throw new RuntimeException("parameter invalid for factorial");
		if(n == 0) return 1;
		return factorial(n-1) * n;
	}
}
