package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import stack.ExpressionEvaluation;

public class Calculator {
	public static void main(String[] args) throws IOException {
		
		System.out.println("evaluate expressions from file");
		InputStream in = new FileInputStream(new File("data/calculator_tasks.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
			System.out.println(line + "= " + ExpressionEvaluation.intEval(line) );
		}
        
        
        System.out.println("now, let's evalute expressions from user input line by line");
        br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line = br.readLine())  != null){
        	System.out.println(line + "= " + ExpressionEvaluation.intEval(line) );
        }
	}
}
