package hashmap;

import java.util.HashMap;
import java.util.HashSet;

public class ValidWordAbbr {
	
	public static void main(String[] args) {
		// Your ValidWordAbbr object will be instantiated and called as such:
		 String[] dictionary = {"deer", "door", "cake", "card"};
		 String[] testWords = {"card", "dear", "door", "cart", "cake"};

		ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
		for (String word : testWords) {
			 System.out.println(word + " is unique ? :" + vwa.isUnique(word));
		}
		 
	
	}
	
	HashMap<String, HashSet<String>> abbrDict = new HashMap<String,HashSet<String>>();
    public ValidWordAbbr(String[] dictionary) {
        if(dictionary == null || dictionary.length == 0) return;
        for(String word : dictionary){
            String abbr = getAbbr(word);
            if(abbrDict.get(abbr) == null){
                abbrDict.put(abbr, new HashSet<String>());
            }
            abbrDict.get(abbr).add(word);
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if(!abbrDict.containsKey(abbr)) return true;
        if(abbrDict.get(abbr).contains(word)) {
        	if(abbrDict.get(abbr).size() == 1)return true;
        }
        return false;
    }
    
    public String getAbbr(String word){
        String abbr = "";
        int n = word.length();
        if(n <=2) {
            abbr = word;
        }else{
            abbr = word.substring(0,1) + (n-2) + word.substring(n-1);
        }
        return abbr;
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");