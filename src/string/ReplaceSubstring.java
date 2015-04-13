package string;

import java.util.List;

public class ReplaceSubstring {
	public static void main(String[] args) {
//		String source = "String replace Example of replacing Character Sequence";
//		String pattern = "re";
//		String replaced = "RE";
		
//		String source = "aaabab";
//		String pattern = "aa";
//		String replaced = "AA";
		
//		String source = "南师大,我在南师大，南师大很大,南师大";
//		String pattern = "南师大";
//		String replaced = "南京师范大学";
		
		
		String source = "wang ping wangshanwangpinghua";
		String pattern = "wang";
		String replaced = "zhang";

		
		System.out.println("source : \n" + source + "\n");
		
		String rep1 = source.replace(pattern, replaced);
		System.out.println("rep1 using built in String.replace(String, String) : \n" + rep1 + "\n");

		String rep2 = replace(source, pattern, replaced);
		System.out.println("rep2 using replace(String, String, String) : \n"
				+ rep2 + "\n");
	}

	public static String replace(String source, String pattern, String replaced) {
		StringBuffer ret = new StringBuffer();
		int[] next = KMP.genNext(pattern);
		
		int i = 0;  // i is the pointer copying elements
		int pos = 0; // pos is the position from which we search
		while(true){
			pos = KMP.index_KMP(source, pattern, pos, next);
			if(pos == -1) break;  // end of search and replace
			//now a substring is found at position pos
			//source[i...pos-1] contains no substring "pattern", simply copy them to "ret"
			while(i < pos){
				ret.append(source.charAt(i++));
			}
			//source[pos...pos+pattern.length()-1] is a substring found
			ret.append(replaced);
			pos += pattern.length(); // jump forward for pattern.length() steps
			i = pos;
		}
		
		while( i < source.length()){  //copy the remaining string
			ret.append(source.charAt(i++));
		}
		return ret.toString();
	}

}
