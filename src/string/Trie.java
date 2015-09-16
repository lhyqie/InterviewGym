package string;

import java.util.*;

/**
 * 
 * Trie is a collection of key, List pairs
 * where key is string, List is a list of values associate with the key
 */
public class Trie {	
	
	public static void main(String[] args) {
		String [] dictionary = {"ac", "ag", "at","cc", "cc","ct", "gt", "gt", "ta", "tt"};
		int[] val = {4, 8, 14, 12, 2, 6, 18, 0, 10, 16};
		Trie trie = new Trie();
		for(int i = 0; i < val.length; i++){
			trie.insertKeyValue(dictionary[i], val[i]);
		}
		trie.print();
		
		String [] targets = {"ac", "ag", "aa" ,"cc", "gt"};
		for (String target : targets) {
			System.out.println("searching target : " + target + " value = " + trie.search(target));
		}
	}
	
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insertKeyValue(String key, int value) {
		root.insertString(key, value);
	}
	
	public List<Integer> search(String s){
		return root.search(s);
	}
	
	public void print(){
		Stack<TrieNode> buffer = new Stack<TrieNode>();
		printHelper(root, buffer);
	}
	
	public void printHelper(TrieNode root, Stack<TrieNode> buffer){  // preOrder
		if(root == null) return;
		buffer.push(root);
		if(root.children.size() > 0){
			for(Map.Entry<Character, TrieNode> entry : root.children.entrySet()){
				printHelper(entry.getValue(), buffer);
			}
		}else{ // leaf
			for (TrieNode node : buffer) {
				System.out.print(""+node.val + " " + node.indexes+"  ");
			}
			System.out.println();
		}
		buffer.pop();
	}
	
	static class TrieNode{
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		List<Integer> indexes = new ArrayList<Integer>();
		char val;
		public TrieNode(){ this.val=' ';}
		public TrieNode(char val){ this.val = val;}
		public void insertString(String word, int value) {
			TrieNode node = this;
			for(char ch : word.toCharArray()){
				if(!node.children.containsKey(ch)) {
					node.children.put(ch, new TrieNode(ch));
				}
				node = node.children.get(ch);
			}
			node.indexes.add(value);
			//node.children.put('$', null);   //$  is the terminating character
		}
		
		public List<Integer> search(String s){
			if( s == null || s.length() == 0){
				return indexes;
			}else{
				char first = s.charAt(0);
				if(children.containsKey(first)){
					return children.get(first).search(s.substring(1));
				}
			}
			return null;
		}
		
		public String toString(){
			return val+" "+indexes;
		}
	}
}
