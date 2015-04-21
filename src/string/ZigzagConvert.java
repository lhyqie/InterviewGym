package string;

import java.util.ArrayList;

public class ZigzagConvert {
	public static void main(String[] args) {
		System.out.println(convert("AB", 1));
		//System.out.println(convert("ABC", 2));
	}
	
	public static String convert(String s, int nRows) {
		if(nRows == 1) return s;
        ArrayList<Character>[] rows = (ArrayList<Character>[])new ArrayList[nRows];
        for(int i = 0; i < nRows; i++){
            rows[i] = new ArrayList<Character>();
        }
        int r = 0;
        int direction = 1;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            rows[r].add(ch);
            r = r + direction;
            if(r == nRows || r == -1){
                direction *= -1;
                r = r + 2 * direction;
            }
            
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < nRows; i++){
            for(char c : rows[i]){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
