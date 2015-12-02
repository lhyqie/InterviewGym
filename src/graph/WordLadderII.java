package graph;

import java.util.*;

class WordNode{
    String word;
    int numSteps;
    WordNode prev;
    public WordNode(String word, int numSteps, WordNode prev){
        this.word = word;
        this.numSteps = numSteps;
        this.prev = prev;
    }
}

public class WordLadderII {
	
	public static void main(String[] args) {
		WordLadderII app = new WordLadderII();
		String beginWord = "hot";
		String endWord = "dog";
		HashSet<String> wordDict = new HashSet<String>(Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
		List<List<String>> res = app.findLadders(beginWord, endWord, wordDict);
		for (List<String> list : res) {
			System.out.println(list);
		}
	}
	
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordDict) {
        HashSet<String> visited = new HashSet<String>();
        List<List<String>> res = new ArrayList<List<String>>();
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1, null));
        wordDict.add(endWord);
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
            visited.add(word);
            if(word.equals(endWord)){
                Stack<String> s = new Stack<String>();
                WordNode p = top;
                while(p !=null) {
                    s.push(p.word);
                    p = p.prev;
                }
                ArrayList<String> row = new ArrayList<String>();
                while(!s.isEmpty()) row.add(s.pop());
                res.add(row);
            }
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c;
                    }
 
                    String newWord = new String(arr);
                    if(!visited.contains(newWord) && wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1, top));
                    }
                    arr[i]=temp;
                }
            }
        }
        return res;
 
    }
}