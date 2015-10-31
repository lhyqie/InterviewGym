package sort;

import java.util.Arrays;
import java.util.Comparator;

/*
 * ����һ���ַ����Ƚ����⣬˵�ܶ��û��������ظ���ͨ����������������֣�
 * �����������ʱ���ϸ����ַ�������ͻ���� abc10 ���� abc2 ǰ�棨��Ϊ��1���ȡ�2��ҪС����
 * ������ʵ��������Ҫ�ﵽ��Ч���� abc10 ���� abc2 ���棨10��2Ҫ�󣩣�������дһ���ַ����ȽϺ�����
�ַ����ٵ�ʱ��ʹ��insertion sort�� ����stack
�ַ������ʱ��ʹ��merge sort
����string�Ƚϵ�ʱ����ĸ���ֱȽϰ���ĸ��˳�����ֲ��ֱȽ�ȥ��ǰ��0���Ƚϳ��ȣ�������ͬ������ĸ��˳��Ƚϡ�

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
