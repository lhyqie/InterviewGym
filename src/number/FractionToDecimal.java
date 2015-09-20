package number;

import java.util.HashMap;

public class FractionToDecimal {
	public static void main(String[] args) {
		System.out.println(fractionToDecimal(-1, -2147483648));
	}
	public static String fractionToDecimal(int numerator, int denominator) {
        long a = numerator,  b = denominator;
        if( a % b == 0) return "" + (a/b);
        String ans = "";
        if( a * b < 0) {
            ans = "-";
        }
        a = Math.abs(a);
        b = Math.abs(b);
        ans += ""+(a/b)+".";
        a %= b;  // a > 0 for sure and a <b
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        int i = ans.length();
        while( a > 0){   // for example a = 2, b = 3
            if(map.containsKey(a)){
                int j = map.get(a);
                ans = ans.substring(0,j) + "(" + ans.substring(j) + ")";
                return ans;
            }
            map.put(a, i);
            i++;
            a *= 10;
            ans += a/b;
            a %= b;
            
        }
        return ans;
    }
}
