package number;

public class Number {
	public static void main(String[] args) {
		int base = 1;
		for (int i = 0; i < 1000; i++) {
			if(isPowerOf(i, base)){
				System.out.println(i + " is a power of " + base);
			}
			
		}
	}
	
	/**
	 * @param x  : the number
	 * @param base : the bass 
	 * @return  true if x is a power of base
	 */
	public static boolean isPowerOf(int x, int base){
		if(base == 0){
			if(x == 0) return true;  //  0 = 0^n
			else return false;
		}else if(base == 1){ 
			if(x == 1) return true;   //  1 = 1^n
			else return false;
		}
		if(x <= 0) return false;
		if(x == 1) return true;  // 1 is base^0
		while( x != 1){
			if(x % base != 0) return false;
			x /= base;
		}
		return true;
	}
}
