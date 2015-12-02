package bit;

public class PowerNumber {
	
	public static void main(String[] args) {
		for (int i = -120; i <=128; i++) {
			if(powerOfTwo(i)){
				System.out.println(i + " is power of 2");
			}
		}
		System.out.println();
		
		for (int i = -300; i <= 300; i++) {
			if(powerOfFour(i)){
				System.out.println(i + " is power of 4");
			}
		}
	}
	
	public static boolean powerOfTwo(int x){
		if(x < 1) return false;
		return  (x & (x-1)) == 0;
	}
	
	public static boolean powerOfFour(int x){
		if(x < 1) return false;
		// for example 8 bits
		// 0 1 0 1 0 1 0 1    => 0x55
		//  32 bits  => 0x55555555
		return  ((x & (x-1)) == 0) &&( (x & 0x55555555) > 0);
	}
	
}
