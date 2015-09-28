package random;

import java.util.ArrayList;
import java.util.List;

public class RandomKLinesFromBigFile {
	public static void main(String[] args) {
		ArrayList<String> file = new ArrayList<String>();
		file.add("1");
		file.add("2");
		file.add("3");
		file.add("4");
		file.add("5");
		file.add("6");
		file.add("7");
		
		System.out.println(randomKLines(file,3));
	}
	
	public static int rand(int lower , int higher){
		return lower + (int)(Math.random() * (higher - lower + 1)) ;
	}
	
	
	/*
	 * http://stackoverflow.com/questions/8335769/select-k-random-lines-from-a-text-file
	 * 
	 *  assume file.size() > k
	 *  Here's a nice one-pass algorithm that I just came up with, having O(N) time complexity and O(M) space complexity, for reading M lines from an N-line file.
		Assume M <= N.
		Let S be the set of chosen lines. Initialize S to the first M lines of the file. If the ordering of the final result is important, shuffle S now.
		Read in the next line 
		l. So far, we have read n = M + 1 total lines. 
		The probability that we want to choose l as one of our final lines is therefore M/n.
		Accept l with probability M/n; 
		use a RNG to decide whether to accept or reject l.
		If l has been accepted, randomly choose one of the lines in S and replace it with l.
		Repeat steps 2-4 until the file has been exhausted of lines, incrementing n with each new line read.
		Return the set S of chosen lines.
	 * 
	 *  Proof
	 *  1) for the line k+i
	 *  the prob of choosing it is independent of first k elements
	 *  = {the prob of of it being picked}  * {the prob of it not being replaced by the elements after it}
	 *  = {k/(k+i)} * { [(k-1)/(k+i+1) + (i+1)/(k+i+1))]   * [(k-1)/(k+i+2) + (i+2)/(k+i+2))]  * ... *  [(k-1)/N + (N-K)/N)]}
	 *  = {k/(k+i)} * { (k+i)/(k+i+1) * (k+i+1)/(k+i+2) * ... (N-1)/N}
	 *  = k/N                             
	 *  
	 *  2) for any line in the first k lines
	 *  the prob of choosing it 
	 *  =  the prob of it not being replaced by the elements after it
	 *  =  [(k-1)/(k+1) + 1/(k+1))]   * [(k-1)/(k+2) + (i+1)/(k+2)]  * ... *  [(k-1)/N + (N-K)/N)]
	 *  =  k/(k+1) * (k+1)/(k+2) * .... (N-1)/N
	 *  = k/N
	 */
	public static  ArrayList<String> randomKLines(ArrayList<String> file, int k){
		//push first k strings to ret
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < k; i++) {
			ret.add(file.get(i));
		}
		
		//from the point forward
		int totalLines = k;
		for (int i = k; i < file.size(); i++) {
			int r = (int)(Math.random()*totalLines);
			if(r < k){ //accept the newline
//				int r2 = rand(0, k-1); // r2-th string among the k strings will be replaced
//				ret.set(r2, file.get(i));
				ret.set(r, file.get(i));
			}
			totalLines ++;
		}
		
		return ret;
	}
}
