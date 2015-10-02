package company.twitter;

/*
 * 题目一： Delete SubStrings: 给一个String s和一个String t, 返回一共能在s中删除t多少次。 
 * 例如s = aabb, t = ab, return = 2; s = aabcbdc, t = abc, return 1;
我基本上没太思考，直接给了最直观的答案，用helperfunction1返回s中与t相同的subString的起始位置start，
用helperfunction2删除s.subString(start, t.length), 外层循环终止条件是helperfunction1
返回-1或s.length < t.length, 时间复杂度是O(n^2)。
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
