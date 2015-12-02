package bit;

public class AddWithoutPlus {
	public static void main(String[] args) {
		System.out.println(add(100, 23));
		System.out.println(add(1, 1));
		System.out.println(add(1, -3));
		System.out.println(add(-4, 1));
		System.out.println(add(0, 0));
	}
	
	public static int add(int a, int b){
		while(b != 0){
			int sum = a ^ b;  // adding without carrying
			int carry = (a&b) << 1;  //carry but don't add
			a = sum;
			b = carry;
		}
		return a;
	}
}
