package company.twitter;

import cern.colt.Arrays;

public class RationalSum {
	public static void main(String[] args) {
		System.out.println("gcd(3,12) = " + gcd(3, 12));
		System.out.println("gcd(18,12) = " + gcd(18, 12));
		System.out.println("gcd(12,18) = " + gcd(12, 18));
		System.out.println("lcm(12,18) = " + lcm(12, 18));
		
		System.out.println(Arrays.toString(rationalAdd(new int[]{2,4}, new int[]{3,5})));
		int [][] rationals = {{2,4},{3,5},{1,3}};
		System.out.println(Arrays.toString(rationalAddAll(rationals)));
		
		rationals = new int[][]{{4,2},{2,4},{2,4},{2,3}};
		System.out.println(Arrays.toString(rationalAddAll(rationals)));
	}
	
	public static int[] rationalAddAll(int [][] rationals){
		if(rationals == null || rationals.length == 0) return null;
		if(rationals.length == 1) return rationals[0];
		int[] res = rationals[0];
		for(int i = 1; i < rationals.length; i++){
			res = rationalAdd(res, rationals[i]);
		}
		return res;
	}
	
	public static int[] rationalAdd(int r1[], int[] r2){
		int de = lcm(r1[1], r2[1]);
		int nu = r1[0] * de / r1[1] + r2[0] * de / r2[1];
		int div = gcd(de, nu);
		de /= div;
		nu /= div;
		return new int[]{nu, de};
	}
	
	public static int gcd(int a, int b){
		while(a > 0){
			int t = b % a;
			b = a;
			a = t;
		}
		return b;
	}
	
	public static int lcm(int a, int b){
		return a * b / gcd(a, b);
	}
	
}
