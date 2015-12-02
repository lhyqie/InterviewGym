package javautils;

import cern.colt.Arrays;

public class TestString {
	public static void main(String[] args) {
		String str = "abc";
		System.out.println(str);
		System.out.println(Arrays.toString(str.split("\n")));
	}
}
