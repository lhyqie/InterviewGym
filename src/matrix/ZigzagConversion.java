package matrix;

import utils.ArrayUtils;

public class ZigzagConversion {
	
	public static String convert(String s, int numRows) {
		if(numRows == 0) return null;
        if(numRows == 1) return s;
        StringBuffer sb = new StringBuffer();
        
        int numCols = s.length();
        int n = s.length();
//        while(true){
//            n -= numRows;
//            numCols ++;
//            if(n <= 0) break;
//            n -= 1;
//            numCols ++;
//            if(n <= 0) break;
//        }
        System.out.println("numRows = "+ numRows + " numCols="+numCols);
        char M[][] = new char[numRows][numCols];
        
        int r = 0, c = 0, direction = 1;
        for(char ch : s.toCharArray()){
        	if(direction == -1){
	            if(r == -1) {
	                r = 1;
	                direction = 1;
	            }else{
	            	c++;
	            }
	            
	            
        	} else{
	            if(r == numRows) {
	                r = numRows  -2;
	                direction = -1;
	                c++;
	            }
        	}
            M[r][c] = ch;
            r += direction;
            ArrayUtils.print(M);
            System.out.println();
        }
        for(r = 0; r < M.length; r++){
        	for(c = 0; c < M[r].length; c++){
        		if(M[r][c] != '\u0000') sb.append(M[r][c]);
        	}
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		//System.out.println(convert("PAYPALISHIRING", 3));
		System.out.println(convert("PAYPALISHIRING", 4));
	}
}
