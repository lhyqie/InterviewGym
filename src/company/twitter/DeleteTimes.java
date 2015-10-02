package company.twitter;

/*
 * ��Ŀһ�� Delete SubStrings: ��һ��String s��һ��String t, ����һ������s��ɾ��t���ٴΡ� 
 * ����s = aabb, t = ab, return = 2; s = aabcbdc, t = abc, return 1;
�һ�����û̫˼����ֱ�Ӹ�����ֱ�۵Ĵ𰸣���helperfunction1����s����t��ͬ��subString����ʼλ��start��
��helperfunction2ɾ��s.subString(start, t.length), ���ѭ����ֹ������helperfunction1
����-1��s.length < t.length, ʱ�临�Ӷ���O(n^2)��
 */
public class DeleteTimes {
	public static void main(String[] args) {
		//String s = "aabb";
		//String t = "ab";
		
		//String s = "aabcbdc";
		//String t = "abc";
				
		//String s = "aabcbc";
		//String t = "abc";
		
		String s = "abababababa";
		String t = "ba";
		
		System.out.println(deleteTimes(s, t));
	}
	
	
	
	public static int deleteTimes(String s, String t){
		if(t == null || t.length() == 0) return 0;
		StringBuffer sb = new StringBuffer(s);
		int start = -1;
		int cnt = 0;
		while(true){
			start = sb.indexOf(t);
			if(start < 0) break;
			sb.replace(start, start + t.length(), "");
			cnt ++;
			if(sb.length() < t.length()) break;
		}
		return cnt;
	}
}
