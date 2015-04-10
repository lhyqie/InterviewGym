package string;

public class ReplaceSubstring {
	public static void main(String[] args) {
		String str = "String replace Example of replacing Character Sequence";
		String rep1 = str.replace("re", "RE");
		String rep2 = replace(str, "re", "RE");
		System.out.println("origin str : \n" + str +"\n");
		System.out.println("rep1 using built in String.replace(String, String) : \n" + rep1 +"\n");
		System.out.println("rep2 using replace(String, String, String) : \n" + rep2 +"\n");
	}
	
	public static String replace(String original, String oldStr,  String newStr){
		return null;
	}
}
