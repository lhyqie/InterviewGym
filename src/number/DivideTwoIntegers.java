package number;

public class DivideTwoIntegers {
	public static int divide(int dividend, int divisor) {
        
		if(divisor == 0) return Integer.MAX_VALUE;
        if(dividend == 0) return 0;
        if(divisor==-1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
    
        int sign = 1;
        if(dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) sign = -1;
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);
        //dividend = Math.abs(dividend);
        //divisor = Math.abs(divisor);
        int res = 0;
        while(a >= b){
            int numShift = 0;
            while(a >= (b << numShift)) numShift++;  //numShift < 31 important
            if(numShift > 0){
            	a -= b << (numShift - 1); 
                res += 1 << (numShift - 1) ;
            }else{
            	//a-= b;
            	//res += 1;
            	break;
            }
            System.out.println(a);
  
        }
        return res * sign; 
    }
	
	public static void main(String[] args) {
		//System.out.println(divide(2147483647, 1));
		//System.out.println(divide(-1010369383, -2147483648));
		//System.out.println(divide(1863955875, -1385477804));
		//System.out.println(divide(-1, 1));
		System.out.println(divide(-2147483648, -1));
		//System.out.println(divide(-2147483648, 2));
		//System.out.println(divide(-1010369383, -2147483648));
		
		
	}
}
