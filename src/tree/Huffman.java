package tree;

import array.PermutateArray;


/**
 * https://www.siggraph.org/education/materials/HyperGraph/video/mpeg/mpegfaq/huffman_tutorial.html
 * 
 * @author howie
 *
 */
public class Huffman {
	
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
		PermutateArray.permutate(a);
		StringBuffer sb = new StringBuffer();
		for (int e : a) {
			sb.append(e+" ");
		}
		String document = sb.toString();
		System.out.println("Original document is : ");
		System.out.println(document);
		System.out.println();

		
		String encoded_document = encode(document);
		System.out.println("Encoded document is : ");		
		System.out.println(encoded_document);
		System.out.println();
		
		String document2 = decode(encoded_document);
		System.out.println("Encoded document after decoding is : ");
		System.out.println(document2);
	}
	
	public static String encode(String document){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	
	public static String decode(String encoded_document){
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	
}
