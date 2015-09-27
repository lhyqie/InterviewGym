package string;

public class ShortestPalindrome {
	
	public static void main(String[] args) {
		//String ret = shortestPalindrome("ba");
		//String ret = shortestPalindrome("abbacd");
		//String ret = shortestPalindrome("aabba");
		String ret = shortestPalindrome("aaaaa");
		System.out.println(ret);
	}
	
	public static String shortestPalindrome(String s) {
        if(s == null || s.length() <= 1) return s;
        char chs[] = s.toCharArray();
        for(int center = (chs.length-1)/2 ; center >= 0; center--){
            int right ;
            right = matchToRight(chs, center, center); 
            if(right != -1){
                if(right == chs.length) return s;
                return new StringBuffer(s.substring(right+1)).reverse().toString() + s; 
            }
            if(center > 0 && chs[center-1] == chs[center]){  
                right = matchToRight(chs, center-1, center); 
                if(right != -1){
                    if(right == chs.length) return s;
                    return new StringBuffer(s.substring(right+1)).reverse().toString() + s; 
                }   
            }
            
            
            //if(matchToRight
        }
        return null;
    }
    
    // return -1 if match does not exist
    public static int matchToRight(char[] chs, int left, int right){
        while(left >= 0 && right < chs.length){
            if(chs[left] != chs[right]) return -1;
            left --;
            right ++;
        }
        if(left < 0) return right - 1;
        return -1;
    }
}