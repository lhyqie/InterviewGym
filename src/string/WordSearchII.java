package string;

/*
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * the idea is correct, just don't know why leetcode still complains time exceeds limit
 * https://leetcode.com/problems/word-search-ii/
 */

public class WordSearchII {
	
	public static void main(String[] args) {
		String boardStr[] = {"aaaa",
							 "aaaa",
							 "aaaa",
							 "aaaa",
							 "bcde",
							 "fghi",
							 "jklm",
							 "nopq",
							 "rstu",
							 "vwxy",
							 "zzzz"};
		char[][] board = new char[boardStr.length][boardStr[0].length()];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = boardStr[i].charAt(j);
				//System.out.print(board[i][j] + " ");
			}
			//System.out.println();
		}
		
		List<String> wordList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/WordSearchII.txt"));
			for(String line = br.readLine(); line != null; line = br.readLine()){
				wordList.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String words[] = new String[wordList.size()];
		for(int i =0 ; i< words.length; i++){
			words[i] = wordList.get(i);
		}
		
		WordSearchII ws = new WordSearchII();
		List<String> ret = ws.findWords(board, words);
		System.out.println(ret);
	}
    
	List<String> ret = null;
	
	public List<String> findWords(char[][] board, String[] words) {
		ret = new ArrayList<String>();
        Trie trie = new Trie();
        for(String word : words) trie.insert(word);
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                DFS(board, r, c, "", trie);
            }
        }
        return ret;
    }
    
    public void DFS(char[][] board, int r, int c, String path, Trie trie){
        if(r < 0 || r >= board.length || c <0 || c >= board[0].length ) return;
        if(board[r][c] == '#') return;
        path += board[r][c];
        
        if(!trie.startsWith(path)) return;
        if(trie.search(path)) ret.add(path);
    
        char tmp = board[r][c];
        board[r][c] = '#';
        DFS(board, r+1, c, path, trie);
        DFS(board, r-1, c, path, trie);
        DFS(board, r, c+1, path, trie);
        DFS(board, r, c-1, path, trie);
        board[r][c] = tmp;
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

    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            word += '$';
            TrieNode node = root;
            for(char ch : word.toCharArray()){
                if(!node.children.containsKey(ch)){
                    node.children.put(ch, new TrieNode(ch));   
                }
                node = node.children.get(ch);
            }
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            if(word == null || word.length() == 0) return false;
            TrieNode node = root;
            for(char ch : word.toCharArray()){
                if(!node.children.containsKey(ch)) return false;
                node = node.children.get(ch);
            }
            if(node.children.containsKey('$')) return true;
            else return false;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.length() == 0) return false;
            TrieNode node = root;
            for(char ch : prefix.toCharArray()){
                if(!node.children.containsKey(ch)) return false;
                node = node.children.get(ch);
            }
            return node != null;
        }
    }
}


