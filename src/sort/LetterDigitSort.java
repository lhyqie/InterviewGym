package sort;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 问了一个字符串比较问题，说很多用户名都会重复，通过后面的数字来区分，
 * 但是在排序的时候严格按照字符串排序就会出现 abc10 排在 abc2 前面（因为‘1’比‘2’要小），
 * 但是事实上他们想要达到的效果是 abc10 排在 abc2 后面（10比2要大），于是让写一个字符串比较函数。
字符串少的时候，使用insertion sort， 减少stack
字符串多的时候，使用merge sort
两个string比较的时候，字母部分比较按字母表顺序。数字部分比较去掉前导0，比较长度，长度相同，按字母表顺序比较。

 */
public class LetterDigitSort {
	
	public static int firstIndexOfDigit(String str){
		for(int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if(Character.isDigit(ch)) return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String [] strs = {"abc10","abc1","abc2","abc03","aac12","a89", "a"};
		
		for (String str : strs) {
			System.out.println("first index of digit for " + str + " is at : " + firstIndexOfDigit(str));
		}
		
		System.out.println(Arrays.toString(strs));
		Arrays.sort(strs, new Comparator<String>(){
			@Override
			public int compare(java.lang.String s1, java.lang.String s2) {
				int idx1 = firstIndexOfDigit(s1);
				int idx2 = firstIndexOfDigit(s2);
				
				if(idx1 == -1 || idx2 == -1) {
					return s1.compareTo(s2);
				}else{
					String s1letter = s1.substring(0, idx1);
					String s2letter = s1.substring(0, idx1);
					if(s1letter.compareTo(s2letter) != 0) return s1letter.compareTo(s2letter);
					else{
						if(idx1 >=0 && idx2 >=0 ){
							int number1 = Integer.parseInt(s1.substring(idx1));
							int number2 = Integer.parseInt(s2.substring(idx2));
							return number1 - number2;
						}else{
							return s1.compareTo(s2);
						}
						
					}
				}
			}			
		});
		
		System.out.println(Arrays.toString(strs));
	}
}
