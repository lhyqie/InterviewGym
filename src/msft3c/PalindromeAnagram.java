package msft3c;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cern.colt.Arrays;

public class PalindromeAnagram {
	
	public static boolean isAnagramPalindrome(String word){
		int[] counts = new int[256];
		for (char c : word.toCharArray()) {
			counts[c] ++; 
		}
		int odd_cnt = 0;
		for (int i = 0; i < 256; i++) {
			if(counts[i] % 2 != 0) odd_cnt ++;
		}
		if(odd_cnt <= 1) return true;
		else return false;
	}
	
	public static List<String> readFromFile(String filepath) throws IOException
	{
		List<String> ret = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = null;
		while((line = br.readLine()) != null)
		{
			ret.add(line);
		}
		return ret;
	}
	public static void main(String[] args) throws IOException
	{
		List<String> lines = readFromFile("msft3c/file1.txt");
		for(String line : lines){
			//System.out.println(line);
			int[] counts = new int[256];
			for (char c : line.toCharArray()) {
				counts[c] ++; 
			}
			int even_cnt = 0;
			int odd_cnt = 0;
			for (int i = 0; i < 256; i++) {
				if(counts[i] % 2 != 0) odd_cnt ++;
				else if(counts[i] != 0) even_cnt += counts[i]/2;
			}
			List<Integer> divs = new ArrayList<Integer>();
			for (int i = 0; i < 256; i++) {
				if(counts[i] > 0){
					divs.add(counts[i]);
				}
			}
			
			if(odd_cnt > 1){
				System.out.print(odd_cnt-1);
			}else{
				System.out.print(0);
			}
			
			System.out.print(",");
			
			//System.out.print(" even_cnt" + even_cnt +" ");
			//System.out.print(factorial(even_cnt) + "->");
			
			int res2 = 1;
			for(int div : divs){
				//System.out.print(div/2  + "->");
				if(div % 2 == 0) res2 *= factorial(div/2);
				else {
					res2 *= factorial((div - 1)/2);
					even_cnt += (div - 1)/2;
				}
			}
			int res1 = factorial(even_cnt);
			System.out.println(res1/ res2);
			
		}
	
	}
	
	public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
