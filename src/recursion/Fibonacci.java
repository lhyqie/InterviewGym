package recursion;

public class Fibonacci {
	public static void main(String[] args) {
		
		// iteration is much faster than recursion as recursion has too many redundant computations
		for (int n = 0; n <= 40; n++) {
			System.out.println("f_itertaion(" + n + ") = " + f_itertaion(n));
		}
		
		System.out.println("========================================================================");
		for (int n = 0; n <= 40; n++) {
			System.out.println("f_recursion(" + n + ") = " + f_recursion(n));
		}
		
		
	}
	
	public static int f_recursion(int n){
		if(n < 0) return -1;
		if(n == 0) return 0;
		if(n == 1) return 1;
		return f_recursion(n-1) + f_recursion(n-2);
	}
	
	public static int f_itertaion(int n){
		if(n < 0) return -1;
		if(n == 0) return 0;
		if(n == 1) return 1;
		int a = 0, b = 1, c = 0;
		for (int i = 2; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;	
	}
}
