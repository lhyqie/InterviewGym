package array;

import java.util.Arrays;

public class PlusOne {
	public static int[] plusOne(int[] digits) {
        int[] res = new int[digits.length+1];
        int c = 1;
        for(int i = digits.length - 1; i >= 0 ; i--){
            res[i+1] += digits[i] + c;
            if(res[i+1] == 10){
                res[i+1] = 0;
                c = 1;
            }else{
            	c = 0;
            }
        }
        res[0] = c;
        if(res[0] == 0) return Arrays.copyOfRange(res, 1, res.length);
        else return res;
	}
	
	public static void main(String[] args) {
		int [] digits = {1, 0};
		System.out.println(Arrays.toString(plusOne(digits)));
	}
}
