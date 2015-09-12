package string;

import java.util.*;

public class SuffixTrie {
	
	public static void main(String[] args) {
		SuffixTrie trie = new SuffixTrie("bibs");
		trie.print();
		System.out.println();
		
		String [] targets = {"bibs", "ibs", "bs" ,"s", "bib", "bb", null, ""};
		for (String target : targets) {
			System.out.println("has suffix : " + target + "  = " + trie.hasSuffix(target));
			System.out.println("has hasSubString : " + target + "  = " + trie.hasSubString(target));
			System.out.println("index of hasSubString : " + target + "  = " + trie.indexOfSubString(target));
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------");
		SuffixTrie trie2 = new SuffixTrie("mississippi");
		trie2.print();
		System.out.println();
		
		String [] targets2 = {"is", "ppi", "hi", "sis", "i", "ssippi"};
		for (String target : targets2) {
			System.out.println("has suffix : " + target + "  = " + trie2.hasSuffix(target));
			System.out.println("has hasSubString : " + target + "  = " + trie2.hasSubString(target));
			System.out.println("index of hasSubString : " + target + "  = " + trie2.indexOfSubString(target));
			System.out.println();
		}
	}
	
	TrieNode root ;
	
	public SuffixTrie(String word){
		word += '$';
		root = new TrieNode();
		char chs[] = word.toCharArray();
		for(int i = 0; i < word.length(); i++){  //for each suffix word[i:]
			TrieNode node = root;
			String suffix = word.substring(i);
			for (int j = 0; j < suffix.length(); j++) { // for each character in suffix
				char ch = suffix.charAt(j);
				if(!node.children.containsKey(ch)){
					node.children.put(ch, new TrieNode(ch));
				}
				node = node.children.get(ch);
				node.indexes.add(i + j);
			}
			//node.indexes.add(word.length());
		}
	}
	
	public boolean hasSubString(String target){
		TrieNode end = followPath(target);
		return end != null;
	}
	
	public boolean hasSuffix(String target){
		TrieNode end = followPath(target);
		return end != null && end.children.containsKey('$');
	}
	
	public List<Integer> indexOfSubString(String target){
		TrieNode end = followPath(target);
		if(end != null && end.indexes.size() > 0){
			List<Integer> res = new ArrayList<Integer>(end.indexes);
			for(int i = 0; i < res.size(); i++) res.set(i, res.get(i)- target.length() + 1);
			return res;
		}else{
			return null;
		}
	}
	
	
	public TrieNode followPath(String target){
		if(target == null || target.length() == 0) return null;
		TrieNode node = root;
		for (char ch : target.toCharArray()) {
			if(node.children.containsKey(ch)){
				node = node.children.get(ch);
			}else{
				return null;
			}
		}
		return node;
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
		}else{
			for (TrieNode node : buffer) {
				//System.out.print(""+node.val);
				System.out.print(""+node.val + " " + node.indexes+"  ");
			}
			System.out.println();
		}
		buffer.pop();
	}
	
	
	static class TrieNode{
		char val;
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		List<Integer> indexes = new ArrayList<Integer>();
		public TrieNode(){this.val = ' ';}
		public TrieNode(char val){this.val = val;}
	}
}

/***
 *   below is the solution from the book Cracking the Coding Interview 6th edition
 * 
package string;

import java.util.*;

public class Trie {
	
	public static Trie createTrieFromSuffix(String word){
		Trie trie = new Trie();
		for(int i = 0; i < word.length(); i++){ //suffix tree
			trie.insertKeyValue(word.substring(i), i);
		}
		return trie;
	}
	
	public static void main(String[] args) {
		//---------------------------------------------------
		Trie trie = createTrieFromSuffix("bibs");
		trie.print();
		List<Integer> indexes = trie.search("ibs");
		System.out.println(indexes);
		System.out.println();
		//---------------------------------------------------
		trie = createTrieFromSuffix("mississippi");
		String[] targets = {"is", "ppi", "hi", "sis", "i", "ssippi"};
		for (String target : targets) {
			System.out.println("searching " + target + " found at " + trie.search(target));
		}
		System.out.println();
		//---------------------------------------------------
		String [] dictionary = {"ac", "ag", "at","cc", "cc","ct", "gt", "gt", "ta", "tt"};
		int[] val = {4, 8, 14, 12, 2, 6, 18, 0, 10, 16};
		Trie trie2 = new Trie();
		for(int i = 0; i < val.length; i++){
			trie2.insertKeyValue(dictionary[i], val[i]);
		}
		trie2.print();
	}
	
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public Trie(String word){
		this();
		
	}
	
	public void insertKeyValue(String key, int value) {
		root.insertString(key, value);
	}
	
	public List<Integer> search(String s){
		List<Integer> indexes = root.search(s);
		if(indexes != null){
			//adjust offset
			for(int i = 0; i < indexes.size(); i++){
				indexes.set(i, indexes.get(i) - s.length() + 1);
			}
		}
		return indexes;
	}
	
	public void print(){
		Stack<TrieNode> buffer = new Stack<TrieNode>();
		printHelper(root, buffer);
	}
	
	public void printHelper(TrieNode root, Stack<TrieNode> buffer){  // preOrder
		if(root == null) {
			for (TrieNode node : buffer) {
				System.out.print(""+node.val + " " + node.indexes+"  ");
			}
			System.out.println();
			return ;
		}
		buffer.push(root);
		//System.out.print(""+root.val);
		//System.out.print(""+root.val + (root.indexes) + " ");
		for(Map.Entry<Character, TrieNode> entry : root.children.entrySet()){
			printHelper(entry.getValue(), buffer);
		}
		buffer.pop();
	}
	
	static class TrieNode{
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		List<Integer> indexes = new ArrayList<Integer>();
		char val;
		public TrieNode(){ this.val=' ';}
		public TrieNode(char val){ this.val = val;}
		public void insertString(String word, int k) {
			indexes.add(k);
			//indexes.add(k - 1);
			if( word == null || word.length() == 0) {
				children.put('\0', null);  //terminating chars
				return;
			}
			char first = word.charAt(0);
			if(!children.containsKey(first)) {
				children.put(first, new TrieNode(first));
			}
			children.get(first).insertString(word.substring(1), k+1);
			//reminder 
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
	}
}

 */
