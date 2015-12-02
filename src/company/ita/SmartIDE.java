package company.ita;

/* 
 * passed 4 out of 5 tests
 */

import java.io.*;

public class SmartIDE {

//below are the test inputs from stdin
/* Single Line */ /*s
 s2*/
// line1
int a = 0; /* c2 */ //line2 //rest
// line3
int b = 0;  /*line 4*/  /*line5*/
/*
 * mutiple lines
 */
int c = 0; /* mutlple lines hard
as
*/ /* sa */ /*sad
*/
	
	public static void outputComments(String inputString){
		String[] lines = inputString.split("\n");
		boolean inComment = false;
		boolean found = false;
		for (String line : lines) {
			found = false;
			int start = line.indexOf("/*");
			int end = -1; 
			if(!inComment){
				while(start >= 0){
					end = line.indexOf("*/", start);
					if(end >=0){
						System.out.print(line.substring(start, end) + "*/");
						found = true;
						inComment = false;
						start = line.indexOf("/*", end);
						if(start >=0) inComment = true;
					}else{
						System.out.print(line.substring(start).trim());
						found = true;
						inComment = true;
						break;
					}
					
				}
			}else{
				start = 0;
				do{
					end = line.indexOf("*/", start);
					if(end >=0){
						System.out.print(line.substring(start, end) + "*/");
						found = true;
						inComment = false;
						start = line.indexOf("/*", end);
						if(start >=0) {
							inComment = true;
							end = line.indexOf("*/", start);
							if(end >=0){
								System.out.print(line.substring(start, end));
								start = end + 2;
								found = true;
								inComment = false;
							}else{
								
							}
							
						}
					}else{
						inComment = true;
						System.out.print(line.substring(start).trim());
						start = line.indexOf("/*", end);
						found = true;
					}
					
				}while(start >= 0 && end >=0 );	
			}
			
			int idx = line.indexOf("//", end);
			if(idx >= 0){
				System.out.print(line.substring(idx).trim());
				found = true;
			}
			if(found) System.out.println();
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		String inputString = "";
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
	
		while ((line = stdin.readLine()) != null){
			// Do something.
			inputString += line + '\n';
		}
		inputString += "\n";
		//System.out.println(inputString);
		outputComments(inputString);
	}
}
