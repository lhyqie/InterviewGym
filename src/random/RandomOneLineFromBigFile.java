package random;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RandomOneLineFromBigFile {
	public static void main(String[] args) {
		ArrayList<String> file = new ArrayList<String>();
		file.add("1");
		file.add("2");
		file.add("3");
		file.add("4");
		file.add("5");
		
		//test
		System.out.println("Running experiments ...");
		TreeMap<String, Integer> cntMap = new TreeMap<String, Integer>();
		for (int i = 0; i < 1000000; i++) {
			String ret = random(file);
			cntMap.put(ret, cntMap.get(ret)==null? 0 : cntMap.get(ret)+1);
		}
		System.out.println(cntMap);
	}
	// core function
	public static String random(List<String> file){
		String ret = null;
		int totalLines = 0;
		for (int i = 0; i < file.size(); i++) {
			int r = (int)(Math.random()*totalLines); // r is a number between [0, totalLines)
			if(r == 0) ret = file.get(i);
			totalLines ++ ;
		}
		return ret;
	}
	// proof
	// p(1) = 1/1 * (1-1/2) * (1-1/3) * ... * (1-1/N) = 1/N;
	// p(2) = 1/2 * (1-1/3) * ... * (1-1/N)   = 1/N
	// p(3) = 1/3 * (1-1/4) * ... * = 1/N
	// ...
	// p(k) = 1/k * (1-1/(k+1))....  = 1/N
	
}
