package msft3c;

import java.io.IOException;
import java.util.List;

public class BubbleSort {
	public static void main(String[] args) throws IOException
	{
		List<String> lines = PalindromeAnagram.readFromFile("msft3c/file2.txt");
		for(String line: lines)
		{
			//System.out.println(line);
			String [] tokens = line.split(" ");
			int [] nums = new int[tokens.length];
			for(int i = 0; i < tokens.length; i++)
			{
				nums[i] = Integer.parseInt(tokens[i]);
			}
			int ret = bubbleSort(nums);
			System.out.println(ret);
			for(int num : nums){
				System.out.print(num+" ");
			}
			System.out.println();
			
		}
	}
	
	
	public static int bubbleSort(int[] a){
		int n = a.length;
		int cnt = 0;
		for (int i = 0; i < n-1; i++) {  // place the largest element of a[0...n-1-i] at a[n-1-i] 
			for (int j = 1; j <= n-1-i; j++) {
				if(a[j-1] > a[j]) {
					swap(a, j-1, j);
					cnt ++;
				}
			}
			
		}
		return cnt;
	}
	
	public static void swap(int[] a, int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}
}
