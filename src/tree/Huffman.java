package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import array.ShuffleArray;


/**
 * https://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html
 * 
 * @author howie
 *
 */
public class Huffman {
	
	public Huffman(){}
	private HuffmanNode root = null;
	private HashMap<Character,String> codemap = null;
	
	public static void main(String[] args) {
		
		int [] elements = {1, 2, 3, 4, 5, 6};
		int [] counts= {5, 7, 10, 15, 20, 45};
		int n = 0;
		for (int e : counts) {
			n += e;
		}
		int [] a = new int[n];
		int i = 0;
		for (int j = 0; j < counts.length; j++) {
			for (int c = 0; c < counts[j]; c++) {
				a[i++] = elements[j];
			}
		}
		ShuffleArray.permutate(a);
		StringBuffer sb = new StringBuffer();
		for (int e : a) {
			sb.append(e);
		}
		String document = sb.toString();
		System.out.println("Original document is : ");
		System.out.println(document);
		System.out.println();

		Huffman huff = new Huffman();
		String encoded_document = huff.encode(document);
		System.out.println("Encoded document is : ");		
		System.out.println(encoded_document);
		System.out.println();
		
		String document2 = huff.decode(encoded_document);
		System.out.println("Encoded document after decoding is : ");
		System.out.println(document2);
		
		System.out.println();
		if(document.equals(document2)){
			System.out.println("encoded and decoded is reversible");
		}else{
			System.out.println("encoded and decoded is irreversible, something is wrong!");
		}
	}
	
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
		public void visualize(){
			HuffmanVisualizer.visualize(this);
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
	
	public void printCodeMap(){
		System.out.println("code map :");
		for (Map.Entry<Character,String> entry : codemap.entrySet()) {
			System.out.println(entry.getKey() +" : "+ entry.getValue());
		}
		System.out.println();
	}
	
	public String encode(String document){
		char[] chs = document.toCharArray();
		root = buildHuffmanTree(chs);
		root.visualize();
		
		codemap = new HashMap<Character,String>();
		Stack<Character> path = new Stack<Character>();
		constructCodeMap(root, codemap, path);
		printCodeMap();
		
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			sb.append(codemap.get(ch));
		}
		return sb.toString();
	}
	
	
	public String decode(String encoded_document){
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
	
}
