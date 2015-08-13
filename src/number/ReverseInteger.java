package number;

public class ReverseInteger {
	public static void main(String[] args) {
		System.out.println("Largest positive integer is : " + Integer.MAX_VALUE);
		System.out.println("Largest negative integer is : " + Integer.MIN_VALUE);
		System.out.println(reverse(1234));
		System.out.println(reverse(1234));
		System.out.println(reverse(2147483647));
		System.out.println(reverse(1000000003));
		System.out.println(reverse(-123));
	}
	
	public static int reverse(int num){
		int maxDiv10 = Integer.MAX_VALUE /10;
		int res = 0;
		int sign = 1;
		if(num < 0){
			sign = -1;
			num = -num;
		}

		while(num > 0){
			if(sign > 0){  //res should be less than 2147483647
				if(res > maxDiv10 || res == maxDiv10 && num%10 >7){
					return 0;
				}
			}else{		  //res should be less than 2147483647
				if(res > maxDiv10 || res == maxDiv10 && num%10 >8){
					return 0;
				}
					
			}
			res = res * 10 + num % 10;
			num /= 10;
			
		}
		return sign * res;
	}
}
