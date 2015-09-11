package math;

public class MySqrt {
	
	public static int sqrt(int x){  // use long to avoid overflow
		assert x >= 0;
        if(x == 0 || x == 1) return x; 
        long left = 0;
        long right = x;
        while(left < right){
        	long mid =  (left + right) / 2;
        	//System.out.println("left = "+ left + " right = "+right + " mid = "+mid);
            if (mid * mid == x){
                return (int)(mid);
            }else if( mid * mid < x){  
                if(left == mid) break; 
                left = mid;
            }else {
            	if(right == mid) break; 
                right = mid;
            }
        }
        return (int)(left); 
	}
	
	public static void main(String[] args) {
		System.out.println(sqrt(2147395599));
		//System.out.println(sqrt(1764975709));
	}
}
