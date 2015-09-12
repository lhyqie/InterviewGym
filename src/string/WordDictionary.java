package string;

import java.io.*;
import java.util.*;


/*
 * Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z 
or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
 */
public class WordDictionary {
	
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/WordDictionary.txt"));
			for(String line = br.readLine(); line != null; line = br.readLine()){
				words.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WordDictionary d = new WordDictionary();
		for (String word : words) {
			d.addWord(word);
		}
		for(int i = 0; i < 20000; i++){
			d.search(".");
		}
		
	}
	// Adds a word into the data structure.
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        word += '$';
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            if(!node.children.containsKey(ch)){
                node.children.put(ch, new TrieNode(ch));   
            }
            node = node.children.get(ch);
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if(word == null || word.length() == 0) return false;
        return search(word.toCharArray(), 0, root);
    }

    // Returns if the word is in the trie.
    public boolean search(char[] chs, int k, TrieNode root) {
        if(k == chs.length) {
            if(root.children.containsKey('$')) return true;
            else return false;
        }
        if(chs[k] == '.'){
            for(Map.Entry<Character, TrieNode> entry : root.children.entrySet()){
                TrieNode child = entry.getValue();
                if(search(chs, k+1, child)) return true;
            }
            return false;
        }else{
            if(root.children.containsKey(chs[k])) return search(chs, k+1, root.children.get(chs[k]));
            else return false;
        }
    }
}


class TrieNode {
    // Initialize your data structure here.
    char val;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    public TrieNode() {
        this.val = '\0';
    }
    public TrieNode(char val){
        this.val = val;
    }
}