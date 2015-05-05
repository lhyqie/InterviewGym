package bit;

import java.util.BitSet;

import array.PermutateArray;


/*          
 *              0 1 2 3 4 5 6 7
 *    -------------------------            
 *    2nd bit | 0 0 0 0 1 1 1 1
 *    1st bit | 0 0 1 1 0 0 1 1
 *    0th bit | 0 1 0 1 0 1 0 1
 *
 *    bitset[k][i]
 */


public class BitmapIndex {
	
	
	public static void test(int nBits){
		long startT = 0;
		long endT = 0;
		
		//--------------- create numbers----------
		startT = System.nanoTime();
		int n = 1 << nBits; // 2^nBits
		int[] numbers = new int[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}
		// permutate
		PermutateArray.permutate(numbers);
		//System.out.println("Numbers : " + Arrays.toString(numbers));
		endT = System.nanoTime();
		//System.out.println(String.format("Time for numbers creation is %10.5f seconds", (endT - startT) / 1000000000.));
		//System.out.println();
		//-----------------create bitmap index----------
		startT = System.nanoTime();
		BitSet[] bitmap = createIndex(numbers, nBits, n);
		
		//for (int i = 0; i < bitmap.length; i++) {
		//	System.out.println(bitmap[i]);
		//}
		endT = System.nanoTime();
		//System.out.println(String.format("Time for bitmap index building is %10.5f seconds", (endT - startT) / 1000000000.));
		//System.out.println();
		// ---------------- test search ----------------
		startT = System.nanoTime();
		int nSample = 100;
		for (int i = 0; i < nSample; i++) {
			int x = random(0, n);
			int idx = find(numbers, bitmap, x, nBits, n);
			//if( idx == -1){
			//	System.out.println(""+x +" not found!");
			//}else{
			//	System.out.println(String.format("%d is found at %d", x, idx));
			//}
		}
		endT = System.nanoTime();
		System.out.println(String.format("%.5f",(endT - startT) / 1000000000.));
		//System.out.println(String.format("Total running time for searching %d samples is %10.5f seconds", nSample,(endT - startT) / 1000000000.));
	}
	
	
	public static void main(String[] args) {
		for (int nBits = 1; nBits <= 20; nBits++) {
			//System.out.println("nBits = "+ nBits);
			test(nBits);
			//System.out.println();
		}	
	}
	
	public static int random(int min, int max){
		int x = (int)(Math.random()* (max - min)) + min; // [0, 9]
		return x;
	}
	/**
	 * @param x
	 * @param k
	 * @return the k-th bit of number x
	 */
	public static int getBit(int x, int k){
		return (x >> k) & 1;
	}
	
	public static BitSet[] createIndex(int[] numbers, int nBits, int n){
		BitSet[] bitmap = new BitSet[nBits];
		for (int i = 0; i < bitmap.length; i++) {
			bitmap[i] = new BitSet(nBits);
		}
		
		for (int k = 0; k < nBits; k++) {  // each row
			for (int i = 0; i < n; i++) {  // set each bitset[k]
				if(getBit(numbers[i],k)==1) bitmap[k].set(i);
			}
		}
		return bitmap;
	}
	
	public static int find(int []numbers, BitSet[] bitmap, int x, int nBits, int n){
		// find all the positions where bits are 1
		// if x == 6  => 1 1 0 => 111 and bitmap[2] and bitmap[1]  
		//
		BitSet ret = new BitSet(nBits);
		for (int i = 0; i < n; i++) ret.set(i);
		for (int k = 0; k < nBits; k++) {
			if( getBit(x, k) == 1) {
				ret.and(bitmap[k]);
			}
		}
		//System.out.println("candidate positions" + ret);
		int i = -1; //{6,7}
		while(true){
			i = ret.nextSetBit(i+1);
			if(i == -1) return -1;
			if(numbers[i] == x){
				return i;
			}
		}
	}
}

