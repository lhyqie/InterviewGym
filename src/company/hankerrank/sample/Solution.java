package company.hankerrank.sample;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
	static void MissingNumberInAP(){
		Scanner in = new Scanner(System.in);
        int n;
        n = in.nextInt();
        if( n < 3) return;
        
        int i = 0;
        int arr[] = new int[n];
        while(i < n){
        	arr[i++] = in.nextInt();
        }
        
        
        int min_diff = arr[1] - arr[0];
        if(min_diff < 0){
        	for(int j = 1; j < n; j++){
            	min_diff = Math.max(min_diff, arr[j] - arr[j-1]);            	
            	
            }
        }else{
        	for(int j = 1; j < n; j++){
        		min_diff = Math.min(min_diff, arr[j] - arr[j-1]);
        	}
        }
                
        int real_sum = (n+1) * arr[0] + min_diff * (n+1)* (n) / 2;
        int sum = 0;
        for(int e: arr) sum+= e;
        System.out.println(real_sum - sum);
	}
	
	static void getNumberOfPrimes(){
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int cnt = 0;
        for(int i = 2; i <= n; i++){
        	if(isPrime(i)) cnt++;
        }
        System.out.println(cnt);
	}
	
	static boolean isPrime(int x){
		if( x <= 1) return false;
		if(x == 2) return true;
		for(int i = 2; i <= Math.sqrt(x); i++){
			if(x % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		MissingNumberInAP();
		//getNumberOfPrimes();
	}
}
