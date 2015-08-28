package string;

public class CountAndSay {
	 public String compress(String s){
	    StringBuilder sb = new StringBuilder();
	    int countRepeat = 0;
	    for(int i = 0; i < s.length(); i++){
	      countRepeat++;
	      if( i+1 >= s.length() || s.charAt(i) != s.charAt(i+1)){   // s[i] is the last character or the next char s[i+1] is different
	        sb.append(""+countRepeat);
	        sb.append(s.charAt(i));
	        countRepeat = 0;
	      }
	    }
	    return sb.toString();
	  }
	  
	  public String countAndSay(int n) {
	      int i = 1;
	      String s = "1";
	      while(i < n){
	        System.out.println(s);
	        s = compress(s);
	        i++;
	      }
	      return s;
	  }
}
