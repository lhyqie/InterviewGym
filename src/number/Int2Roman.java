package number;

public class Int2Roman {
	
	int[] w = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	String[] c = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	
	
	public static void main(String[] args) {
		Int2Roman ir = new Int2Roman();
		
		for(int i = 1; i < 1000; i++){
			System.out.println(i+"\t"+ir.intToRoman(i));
		}
	}
	
	
	    
	public String intToRoman(int num) {
		StringBuilder res = new StringBuilder();
	    int i = 0;
	    while (num > 0) {  // from largest w to smallest
	        for (int j = 0; j < num/w[i]; j++) res.append(c[i]);
	        num %= w[i];
	        i++;
	   
	    }
	    return res.toString();
	}
}
