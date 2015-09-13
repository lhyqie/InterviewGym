package bit;

public class NumberOfOneBits {
	
	public static int hammingWeight(int n) {
		long t;
        if(n < 0) {
        	t = n + 2147483648L * 2;
        }else{
        	t = n;
        }
        System.out.println(t);
        int cnt = 0;
        while(t > 0){
            t = t & (t-1);
            cnt ++;
        }
        return cnt;
    }
	
	public static void main(String[] args) {

		
		Integer a = new Integer(2147483647);
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a));
		System.out.println("# of 1 bit :" + hammingWeight(a));
		System.out.println();
	
		Integer b = new Integer(a + 1);
		System.out.println(b);
		System.out.println(Integer.toBinaryString(b));
		System.out.println("# of 1 bit :" + hammingWeight(b));
		
	}
	
}
