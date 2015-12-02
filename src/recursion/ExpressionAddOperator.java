package recursion;

import java.util.*;

public class ExpressionAddOperator {
	
	public static void main(String[] args) {
		ExpressionAddOperator app = new ExpressionAddOperator();
		List<String> ret = app.addOperators("123", 6);
		System.out.println(ret);
	}
	
	public List<String> addOperators(String num, int target) {
        return search(num, target, "", 1);
    }
    
    public List<String> search(String num, int target, String exp, int mulVal){
        List<String> ret = new ArrayList<String>();
        if(num.length() == 0){
            if(target == 0){
                ret.add(exp);
            }
        }else{
            for(int i = 0; i < num.length(); i++){
                // split to left and right parts which right part contains no operators
                String numL = num.substring(0, i);
                String numR = num.substring(i);
                if(hasLeadingZero(numR)) continue;
                String right = numR + exp;
                int valueR = Integer.parseInt(numR) * mulVal;
                if(i == 0) { // numL is ""
                	if(valueR == target) ret.add(right);
                	continue;
                }

                List<String> numLs  = null;
                // use +
                numLs = search(numL, target - valueR, "", 1);
                for(String left: numLs){
                    ret.add(left+"+"+right);
                }
                // use -
                numLs = search(numL, target + valueR, "", 1);
                for(String left: numLs){
                    ret.add(left+"-"+right);
                }
                // use *
                numLs = search(numL, target, "", valueR);
                for(String left: numLs){
                    ret.add(left+"*"+right);
                }
            }
        }
        return ret;
    }
    
    public boolean hasLeadingZero(String num){
        if(num.length() > 1 && num.charAt(0) == '0') return true;
        else return false;
    }
}
