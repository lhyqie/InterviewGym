package stack;

import java.util.Stack;
import java.util.HashMap;

public class ExpressionEvaluation {
	

	private static class Pair{
		public Pair(char op1, char op2){
			this.op1 = op1; 
			this.op2 = op2;
		}
		public int hashCode() { 
			return op1 ^ op2; 
		}
		public boolean equals(Object other) {  // can not use  public boolean equals(Pair other)
			if (!(other instanceof Pair)) return false;
			Pair rhs = (Pair) other;
			return op1 == rhs.op1 && op2 == rhs.op2;
		}
		private char op1;
		private char op2;
	}
	
	 /** operator precedence mapping
	  *  row : op1
	  *  column : op2
	  *  
	  *     +  -  *  /  (  )  #  .
	  *  +  >  >  <  <  <  >  >  <
	  *  -  >  >  <  <  <  >  >  < 
	  *  *  >  >  >  >  <  >  >  < 
	  *  /  >  >  >  >  <  >  >  <
	  *  (  <  <  <  <  <  =     < 
	  *  )  >  >  >  >     >  >  <
	  *  #  <  <  <  <  <     =  <
	  *  .  >  >  >  >     >  >    
	 */
	static HashMap<Pair, Character> opt_pre = new HashMap<Pair, Character> ();
	static {
		opt_pre.put(new Pair('+', '+'), '>');
		opt_pre.put(new Pair('+', '-'), '>');
		opt_pre.put(new Pair('+', '*'), '<');
		opt_pre.put(new Pair('+', '/'), '<');
		opt_pre.put(new Pair('+', '('), '<');
		opt_pre.put(new Pair('+', ')'), '>');
		opt_pre.put(new Pair('+', '#'), '>');
		opt_pre.put(new Pair('+', '.'), '<');
		
		opt_pre.put(new Pair('-', '+'), '>');
		opt_pre.put(new Pair('-', '-'), '>');
		opt_pre.put(new Pair('-', '*'), '<');
		opt_pre.put(new Pair('-', '/'), '<');
		opt_pre.put(new Pair('-', '('), '<');
		opt_pre.put(new Pair('-', ')'), '>');
		opt_pre.put(new Pair('-', '#'), '>');
		opt_pre.put(new Pair('-', '.'), '<');
		
		opt_pre.put(new Pair('*', '+'), '>');
		opt_pre.put(new Pair('*', '-'), '>');
		opt_pre.put(new Pair('*', '*'), '>');
		opt_pre.put(new Pair('*', '/'), '>');
		opt_pre.put(new Pair('*', '('), '<');
		opt_pre.put(new Pair('*', ')'), '>');
		opt_pre.put(new Pair('*', '#'), '>');
		opt_pre.put(new Pair('*', '.'), '<');
		
		opt_pre.put(new Pair('/', '+'), '>');
		opt_pre.put(new Pair('/', '-'), '>');
		opt_pre.put(new Pair('/', '*'), '>');
		opt_pre.put(new Pair('/', '/'), '>');
		opt_pre.put(new Pair('/', '('), '<');
		opt_pre.put(new Pair('/', ')'), '>');
		opt_pre.put(new Pair('/', '#'), '>');
		opt_pre.put(new Pair('/', '.'), '<');
		
		opt_pre.put(new Pair('(', '+'), '<');
		opt_pre.put(new Pair('(', '-'), '<');
		opt_pre.put(new Pair('(', '*'), '<');
		opt_pre.put(new Pair('(', '/'), '<');
		opt_pre.put(new Pair('(', '('), '<');
		opt_pre.put(new Pair('(', ')'), '=');
		opt_pre.put(new Pair('(', '.'), '<');
		
		opt_pre.put(new Pair(')', '+'), '>');
		opt_pre.put(new Pair(')', '-'), '>');
		opt_pre.put(new Pair(')', '*'), '>');
		opt_pre.put(new Pair(')', '/'), '>');
		opt_pre.put(new Pair(')', ')'), '>');
		opt_pre.put(new Pair(')', '#'), '>');
		opt_pre.put(new Pair(')', '.'), '<');
		
		opt_pre.put(new Pair('#', '+'), '<');
		opt_pre.put(new Pair('#', '-'), '<');
		opt_pre.put(new Pair('#', '*'), '<');
		opt_pre.put(new Pair('#', '/'), '<');
		opt_pre.put(new Pair('#', '('), '<');
		opt_pre.put(new Pair('#', '#'), '=');
		opt_pre.put(new Pair('#', '.'), '<');
		
		opt_pre.put(new Pair('.', '+'), '>');
		opt_pre.put(new Pair('.', '-'), '>');
		opt_pre.put(new Pair('.', '*'), '>');
		opt_pre.put(new Pair('.', '/'), '>');
		opt_pre.put(new Pair('.', ')'), '>');
		opt_pre.put(new Pair('.', '#'), '>');
		
	}
	
	public static void main(String[] args) {
		String[] init_expressions = {
				"13",
				"1+2",
				"1-2",
				"(1+2)",
				"(1+2+3)",
				"10 + 2 * 6",
			    "100 * 2 + 12",
			    "3 * (2+1)",
			    "100 * ( 2 + 12 ) / 14",
			    "6+5*(3-2)",
			    "10 * (2 + 6)",
			    //"2*(-2)"  // this is not currently supported   because - is not a binary operator but a unary operator
			    "2 + 3 * ( 1 - 4*2)",
			    "1+(2)",
				"0 * 1 + 2",
				"1  - 0  * 1 + 8",
				"1 + 0 - 0  * 1 + 2 * 2 * (2)",
		};
		
		for (String expression : init_expressions) {
			System.out.println(expression + " = " + intEval(expression));
		}
		
		// ==========================================================================
		System.out.println("===========================================================================");
		String[] double_expressions = {
				"13",
				"1+2",
				"1-2",
				"(1+2)",
				"(1+2+3)",
				"10 + 2 * 6",
			    "100 * 2 + 12",
			    "3 * (2+1)",
			    "100 * ( 2 + 12 ) / 14",
			    "6+5*(3-2)",
			    "10 * (2 + 6)",
			    "1.2",
			    "1.3 + 2.3",
			    "1-2.3",
			    "100.4 * ( 2.3 + 1.2 ) / 14.3",
			    "(2+1.1)",
			    "(5-4)/4-2",
			    "(5.5-4.5)/2+56.7",
			    "2 + 3 * ( 1 - 4*2)",
			    "1+(2)",
				"0 * 1 + 2",
				"1  - 0  * 1 + 8",
				"1 + 0 - 0  * 1 + 2 * 2 * (2)",
		};
		
		for (String expression : double_expressions) {
			System.out.println(expression + " = " + doubleEval(expression));
		}
	}
	
	public static int intCal(char op, int v1, int v2){
		if(op == '+'){
			return v1 + v2;
		}else if(op == '-'){
			return v1 - v2;
		}else if(op == '*'){
			return v1 * v2;
		}else if(op == '/'){
			return v1 / v2;
		}
		else{
			throw new RuntimeException("unknown operator");
		}
	}
	
	public static double doubleCal(char op, double v1, double v2){
		if(op == '+'){
			return v1 + v2;
		}else if(op == '-'){
			return v1 - v2;
		}else if(op == '*'){
			return v1 * v2;
		}else if(op == '/'){
			return v1 / v2;
		}else if(op == '.'){
			int bits = 0;  //bit of v2
			int t = (int)v2;
			while(t > 0) {
				t /= 10;
				bits ++;
			}
			return v1 + v2 / Math.pow(10, bits);
		}else{
			throw new RuntimeException("unknown operator");
		}
	}
	
	/**
	 * @param expression : Expression can contain parentheses, 
	 * parentheses are well-matched. For simplicity, 
	 * allowed are binary operators +, -, *, and /
	 * @return : the result of expression after evaluated 
	 */
	public static int intEval(String expression){
		expression += "#";  //ending operator 
		char[] chs = expression.toCharArray();
		Stack<Integer> values = new Stack<Integer>();
		Stack<Character> ops = new Stack<Character>();
		ops.push('#'); // starting operator 
		for (int i = 0; i <chs.length; i++) {
			if(chs[i] == ' ') continue;  //if ch is space, skip
			if(Character.isDigit(chs[i])){ // if ch is number
				if(i > 0 && Character.isDigit(chs[i-1])){ //handle integers that are > 9
					values.push(values.pop() * 10 + chs[i] - 48);
				}else{
					values.push(chs[i] - 48);
				}
			}else{   // if ch is operator
				char precedence = opt_pre.get(new Pair(ops.peek(), chs[i])) ;
				if( precedence == '<' ){
					ops.push(chs[i]);
				}else if( precedence == '>'){
					char op = ops.pop();
					int v2 = values.pop(); // pay attention to the order  v2 first get popped
					int v1 = values.pop();
					values.push(intCal(op, v1, v2));
					if(chs[i] == ')'){
						while(ops.peek() != '('){   // (1+3.1)
							op = ops.pop();
							v2 = values.pop(); // pay attention to the order  v2 first get popped
							v1 = values.pop();
							values.push(intCal(op, v1, v2));
						}
						ops.pop();  //pop (
					}else if(ops.size() >= 2){
						while( opt_pre.get(new Pair(ops.get(ops.size()-1), chs[i])) == '>' ){
							op = ops.pop();
							v2 = values.pop(); // pay attention to the order  v2 first get popped
							v1 = values.pop();
							values.push(intCal(op, v1, v2));
						}
						if(chs[i] != '#') ops.push(chs[i]);
					}else if(chs[i] != '#') ops.push(chs[i]);
					
				}else{  // precedence == '='
					ops.pop();
				}
				
			}
			//debug
			//System.out.println(chs[i]);
			//System.out.println(values);
			//System.out.println(ops);
			//System.out.println();
		}
		
		// Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
		while (ops.size() > 1){  //in the end, the starting # is the only remaining op
        	char op = ops.pop();	
    		int v2 = values.pop();
        	int v1 = values.pop();
            values.push(intCal(op, v1, v2 ));
    	
        }

		return values.peek();
	}
	
	/**
	 * @param expression : Expression can contain parentheses, 
	 * parentheses are well-matched. For simplicity, 
	 * allowed are binary operators  +, -, *, and /
	 * numbers can contain . 
	 * @return the result of expression after evaluated 
	 */
	public static double doubleEval(String expression){
		expression += "#";  //ending operator 
		char[] chs = expression.toCharArray();
		Stack<Double> values = new Stack<Double>();
		Stack<Character> ops = new Stack<Character>();
		ops.push('#'); // starting operator 
		for (int i = 0; i < chs.length; i++) {
			if(chs[i] == ' ') continue;  //if ch is space, skip
			if(Character.isDigit(chs[i])){ // if ch is number
				if(i > 0 && Character.isDigit(chs[i-1])){ //handle integers that are > 9
					values.push(values.pop() * 10 + chs[i] - 48);
				}else{
					values.push((double)(chs[i] - 48));
				}
			}else{   // if ch is operator
				char precedence = opt_pre.get(new Pair(ops.peek(), chs[i])) ;
				if( precedence == '<' ){
					ops.push(chs[i]);
				}else if( precedence == '>'){
					char op = ops.pop();
					double v2 = values.pop(); // pay attention to the order  v2 first get popped
					double v1 = values.pop();
					values.push(doubleCal(op, v1, v2));
					if(chs[i] == ')'){
						while(ops.peek() != '('){   // (1+3.1)
							op = ops.pop();
							v2 = values.pop(); // pay attention to the order  v2 first get popped
							v1 = values.pop();
							values.push(doubleCal(op, v1, v2));
						}
						ops.pop();  //pop (
					}else if(ops.size() >= 2){
						while( opt_pre.get(new Pair(ops.get(ops.size()-1), chs[i])) == '>' ){
							op = ops.pop();
							v2 = values.pop(); // pay attention to the order  v2 first get popped
							v1 = values.pop();
							values.push(doubleCal(op, v1, v2));
						}
						if(chs[i] != '#') ops.push(chs[i]);
					}else if(chs[i] != '#') ops.push(chs[i]);
					
				}else{  // precedence == '='
					ops.pop();
				}
				
			}
			//debug
			//System.out.println(chs[i]);
			//System.out.println(values);
			//System.out.println(ops);
			//System.out.println();
		}
		
		// Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (ops.size() > 1){  //in the end, the starting # is the only remaining op
        	char op = ops.pop();
    		double v2 = values.pop();
        	double v1 = values.pop();
            values.push(doubleCal(op, v1, v2 ));
        	
        }

		return values.peek();
	}
	
}
