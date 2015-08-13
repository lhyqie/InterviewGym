package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Ways2AddParentheses {
	public static void main(String[] args) {
		
		String expr = "2*3-4*5";
		
		System.out.println(diffWaysToCompute(expr));
		
	}
	
	public static List<Integer> diffWaysToCompute(String expr){
		List<Integer> numList = new ArrayList<Integer>();
		List<Character> opList = new ArrayList<Character>();
		Stack<Integer> numStack = new Stack<Integer>();
		Stack<Character> opStack = new Stack<Character>();
		StringBuffer sb = new StringBuffer();
		for (char ch : expr.toCharArray()) {
			if(Character.isDigit(ch)){
				sb.append(ch);
			}else{
				numList.add(Integer.parseInt(sb.toString()));
				sb = new StringBuffer();
				opList.add(ch);
			}
		}
		if(sb.length() > 0){
			numList.add(Integer.parseInt(sb.toString()));
		}
		//System.out.println(numStack);
		//System.out.println(opStack);
		numStack.push(numList.get(0));
		
		List<Integer> res = new ArrayList<Integer>();
		helper(numList, opList, numStack, opStack, 1, 0, res);
		//System.out.println(res);
		return res;
	}
	
	private static void helper(List<Integer> numList, List<Character> opList, Stack<Integer> numStack, 
			Stack<Character> opStack, int pNum, int pOp, List<Integer> res){
		if(pNum == numList.size()  && pOp == opList.size()){
			while(numStack.size() > 1){
				int b = numStack.pop();
				int a = numStack.pop();
				char op = opStack.pop();
				numStack.push(eval(a, b, op));
			}
			res.add(numStack.pop());
		}else{
			if(opStack.size() == 0){
				numStack.add(numList.get(pNum++));
				opStack.add(opList.get(pOp++));
				helper(numList, opList, numStack, opStack, pNum, pOp, res);  //push
			}else{
				if(pOp < opList.size()){
					numStack.add(numList.get(pNum++));
					opStack.add(opList.get(pOp++));
					helper(numList, opList, (Stack<Integer>)(numStack.clone()), 
							(Stack<Character>)(opStack.clone()), pNum, pOp, res);  //push
					
					pNum--;                            //undo push
					pOp--;
					numStack.pop();
					opStack.pop();
				}
				if(opStack.size() > 0){
					int b = numStack.pop();
					int a = numStack.pop();
					char op = opStack.pop();
					numStack.push(eval(a, b, op));
					helper(numList, opList, (Stack<Integer>)(numStack.clone()), 
							(Stack<Character>)(opStack.clone()), pNum, pOp, res);    //pop
					                                         //undo pop
					numStack.push(a);
					numStack.push(b);
					opStack.push(op);
				}	
				
			}   
		}
	}

	private static Integer eval(int a, int b, char op) {
		if(op == '+') return a + b;
		if(op == '-') return a - b;
		if(op == '*') return a * b;
		if(op == '/') return a / b;
		return null;
	}
}
