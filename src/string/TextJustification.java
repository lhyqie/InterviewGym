package string;

import java.util.*;

public class TextJustification {
	
	public static void main(String[] args) {
		//String[] words = {"a"}; //{""}; //{"This", "is", "an", "example", "of", "text", "justification."};
		//int maxWidth =  5;  //2;  //16;
		
		//String [] words = {"Here","is","an","example","of","text","justification."};
		//int maxWidth = 14;
		
		String [] words ={"Don't","go","around","saying","the","world","owes","you","a","living;","the","world","owes","you","nothing;","it","was","here","first."};
		int maxWidth = 30;
		
		TextJustification tj = new TextJustification();
		List<List<String>> ret = tj.groupByLine(words, maxWidth);
		System.out.println(ret);
		
		List<String> res = tj.fullJustify(words, maxWidth);
		for (String string : res) {
			System.out.println(string);
		}
	}
	
	public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<String>();
		if(maxWidth == 0 || words == null || words.length == 0 || words.length == 1 && words[0].length() == 0) {
		    StringBuffer sb = new StringBuffer();
		    for(int j = 0; j < maxWidth; j++){
                sb.append(" ");
            }
            ret.add(sb.toString());
		    return ret;
		}
		
		List<List<String>> lines = groupByLine(words, maxWidth);
        for(int i = 0; i < lines.size(); i++){
            if(i != lines.size() - 1){
                ret.add(align(lines.get(i), maxWidth));
            }
            else {
                ret.add(alignLast(lines.get(i), maxWidth));
            }
        }
        return ret;
    }
    
    public String alignLast(List<String> line, int maxWidth){
        StringBuffer ret = new StringBuffer();
        int m = maxWidth; // remaining spaces
        for(int i = 0; i < line.size(); i++){
            ret.append(line.get(i));
            m -= line.get(i).length();
            if(i != line.size() - 1) {
                ret.append(" ");
                m-=1;
            }
        }
        for(int j = 0; j < m; j++){
            ret.append(" ");
        }
        return ret.toString();
    }
    
    //for first line to second last line
    public String align(List<String> line, int maxWidth){
        int n = line.size() - 1;  // n buckets
        StringBuffer ret = new StringBuffer();
        if(n == 0) {
        	ret.append(line.get(0));
        	for(int j = 0; j < maxWidth - line.get(0).length(); j++){
                ret.append(" ");
            }
        	return ret.toString();
        }
        int m = maxWidth;  // total spaces
        for(String word : line) m -= word.length();
        // first word
        ret.append(line.get(0));
        for(int i = 1; i < line.size(); i++){
            if(i <= m%n){ // spaces before second word
                for(int j = 0; j < m/n + 1; j++){
                    ret.append(" ");
                }
            }else{     // spaces otherwise
                for(int j = 0; j < m/n; j++){
                    ret.append(" ");
                }
            }
            ret.append(line.get(i));
        }
        return ret.toString();
    }
    
    public List<List<String>> groupByLine(String[] words, int maxWidth){
        List<List<String>> ret = new ArrayList<List<String>>();
        int len = 0;
        int start = 0;
        int end = 0;
        for(end = 0; end < words.length; end++){
            if(len + words[end].length() > maxWidth){
                List<String> row = new ArrayList<String>();
                for(int i = start; i < end; i++) row.add(words[i]);
                ret.add(row);
                start = end;
                len = words[start].length() + 1;
            }else{
                len += words[end].length() + 1; // + 1 for space
            }
        }
        if(end > start){
        	List<String> row = new ArrayList<String>();
            for(int i = start; i < end; i++) row.add(words[i]);
            ret.add(row);
        }
        return ret;
    }
}
