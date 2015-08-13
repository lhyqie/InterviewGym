package number;

public class IsNumber {
	public static boolean isNumber(String s) {
        //"  +31.4e-3  "
        int i = 0, n = s.length();
        char[] chs = s.toCharArray();
        while(i < n && chs[i] == ' ') i++;  //skip heading spaces
        if(i < n && (chs[i] == '+' ||  chs[i] == '-')) i++; //optional + - sign
        boolean ret = false;
        while(i < n && Character.isDigit(chs[i])){              //before .
            ret = true; i++;
        }
        if(i < n && chs[i] == '.'){                             //optional .
            i++;
            while(i < n && Character.isDigit(chs[i])){          // after .
                ret = true; i++;
            }
        }
        if( ret && i < n && chs[i] == 'e'){
            ret = false;
            i++;
            if(i < n && (chs[i] == '+' ||  chs[i] == '-')) i++;   //optional + -
            while(i < n && Character.isDigit(chs[i])){            
                ret = true; i++;
            }
        }
        while(i < n && chs[i] == ' ') i++;  //skip heading spaces
        if(ret && i == n) return true;
        else return false;
    }
	
	public static void main(String[] args) {
		System.out.println(isNumber("2e0"));
	}
}
