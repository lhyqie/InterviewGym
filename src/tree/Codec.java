package tree;

import java.util.*;

public class Codec {
	
	public static void main(String[] args) {
		Codec codec = new Codec();
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("0");
		codec.decode(codec.encode(strs));
	}
    
    private HuffmanNode root = null;
	
	private HashMap<Character,String> codemap = null;
	
	static class HuffmanNode implements Comparable<HuffmanNode>{
		char ch;
		int count;
		HuffmanNode left;
		HuffmanNode right;
		public HuffmanNode(char ch, int count){
			this.ch = ch;
			this.count = count;
		}
		public int compareTo(HuffmanNode o) {
			return count-o.count;
		}
		public String toString(){
			return ""+ch+":"+count;
		}
	}

	private HuffmanNode buildHuffmanTree(char[] chs){
		// 1 count characters
		int[] counts = new int[256];  //assume ASCII
		for(char ch: chs){
			counts[ch]++; 
		}
		
		// 2 construct a priority queue sorted by counts
		PriorityQueue<HuffmanNode> PQ = new PriorityQueue<HuffmanNode>();
		for (int i = 0; i < 256; i++) {
			char ch = (char)(i);
			int count = counts[i];
			if(count != 0) PQ.add(new HuffmanNode(ch, count));
		}
		
		// 3 build huffman tree bottom-up
		while(PQ.size() >= 2){
				// get the first two smallest
				HuffmanNode small = PQ.poll();
				HuffmanNode big = PQ.poll();
				HuffmanNode parent = new HuffmanNode('*', small.count + big.count);
				parent.left = small;
				parent.right = big;
				PQ.offer(parent);
		}
		// now PQ has only one element
		HuffmanNode root = PQ.poll();
		return root;
	}
	
	private void constructCodeMap(HuffmanNode root, HashMap<Character,String> codemap, Stack<Character> path) {
		if(root == null) return;
		if(root.left == null && root.right == null){ // root is a leaf 
			StringBuffer code = new StringBuffer();
			for (Character ch : path) {
				code.append(ch);
			}
			codemap.put(root.ch, code.toString());
			return;
		}
		if(root.left != null){
			path.push('0');
			constructCodeMap(root.left, codemap, path);
			path.pop();
		}
		if(root.right != null){
			path.push('1');
			constructCodeMap(root.right, codemap, path);
			path.pop();
		}
	}
	
	public String encode_huff(String document){
		char[] chs = document.toCharArray();
		root = buildHuffmanTree(chs);
		
		codemap = new HashMap<Character,String>();
		Stack<Character> path = new Stack<Character>();
		constructCodeMap(root, codemap, path);
		
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			sb.append(codemap.get(ch));
		}
		return sb.toString();
	}
	
	public String decode_huff(String encoded_document){
		char[] chs = encoded_document.toCharArray();
		StringBuffer sb = new StringBuffer();
		HuffmanNode p = this.root;
		int pos = 0;
		while(pos < chs.length){
			if(chs[pos] == '0'){
				if(p.left == null) throw new RuntimeException("invalid encoded document");
				p = p.left;
			}else if(chs[pos] == '1'){
				if(p.right == null) throw new RuntimeException("invalid encoded document");
				p = p.right;
			}else{
				throw new RuntimeException("invalid encoded document");
			}
			if(p.left == null && p.right == null){ // p is now a leaf
				sb.append(p.ch);
				p = this.root; //back to root
			}
			pos ++;
		}
		return sb.toString();
	}
	
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if(strs == null || strs.size() == 0) return null;
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < strs.size(); i++){
            sb.append(strs.get(i));
            if(i != strs.size()- 1) sb.append("\n");
        }
        System.out.println(sb.toString());
        System.out.println(encode_huff(sb.toString()));
        return encode_huff(sb.toString());
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if(s == null) return new ArrayList<String>();
        String e = decode_huff(s);
        String[] tokens = e.split("\n");
        List<String> ret = new ArrayList<String>();
        for(String token : tokens){
            ret.add(token);
        }
        return ret;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));