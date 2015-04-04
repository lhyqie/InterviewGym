package datastream;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Twitter Challenge, find the day with most tweets in recent k days 
 * Also an interview question with Microsoft
 */
public class WindowMax {
	private int k; 			// window size
	private DataStream ds;  // DataStream object
	private int t;          // current timestamp
	private int[] buffer;   // circular array of size k, used for O(k) approach only
	private Deque<Integer> Q; // double ended queue.  order from largest to smallest
	
	/**
	 * @param k : window size k
	 * @param ds : DataStream object
	 */
	public WindowMax(int k, DataStream ds){
		this.k = k;
		this.ds = ds;
		buffer = new int[k];
		Q = new LinkedList<Integer>();
	}
	
	public int getCurrentTimeStamp(){
		return this.t;
	}
	
	public int getCurrent(){
		int t = ds.getTimeStamp();
		if(t == 0) throw new RuntimeException("fetch one from data stream first");
		return buffer[(t-1) % k];
	}
	
	/**
	 * @return : the max of most recent k elements
	 * Time complexity O(1)
	 */
	public int getMax(){
		return buffer[Q.getFirst()%k];
	}
	
	/**
	 * @return : the max of most recent k elements
	 * Time complexity O(k)
	 */
	public int getMaxNaive(){
		int max = 0;
		int t = ds.getTimeStamp();
		if(t < k){  // first k steps
			for (int i = 0; i < t; i++) if( max < buffer[i]) max = buffer[i];
		}else{
			for (int i = 0; i < k; i++) if( max < buffer[i]) max = buffer[i];
		}
		return max;
	}
	
	public int getWindowSize(){
		return k;
	}
	
	/**
	 *  fetch data from data stream
	 */
	public void fetch(){
		if(!ds.hasNext()) return;
		this.t = ds.getTimeStamp();
		int newData = ds.next();
		//---------------- for naive approach -------------------
		if(t < k){  // first k steps
			buffer[t] = newData;
		}else{
			buffer[t%k] = newData;      //replace the old one
		}
		//---------------- for advanced approach -------------------
		if(t < k){  // first k steps
			// delete elements smaller than newData because since newData is newer and larger, they have no chance to be max
			while(!Q.isEmpty() && newData >= buffer[Q.getLast()%k]) 
				Q.pollLast();
			Q.addLast(t);
		}else{
			// delete elements smaller than newData because since newData is newer and larger, they have no chance to be max
			while(!Q.isEmpty() && newData >= buffer[Q.getLast()%k]) 
				Q.pollLast();
			// if any element leaving the window, we update Q
			while(!Q.isEmpty() && Q.getFirst() <= t-k) 
				Q.pollFirst();
			
			Q.addLast(t);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("================================= Correctness Test  =====================================");
		DataStream ds = new DataStream(10, 1, 10, 123434L);
		System.out.println(ds);
		WindowMax wm = new WindowMax(3, ds);
		System.out.println("Window size k :" + wm.getWindowSize());
		while(ds.hasNext()){
			wm.fetch();
			System.out.println(String.format("t : %s \t element : %d \t naive average : %d \taverage : %d ",
					wm.getCurrentTimeStamp(), wm.getCurrent(), wm.getMaxNaive(), wm.getMax()));
		}
		
		System.out.println("\n\n================================= Performance Test  =====================================");
		DataStream ds2 = new DataStream(1000000, 1, 10, 123434L);
		WindowMax wm2 = new WindowMax(1000, ds2);
		
		System.out.println("# of elements in the data stream : " + ds2.getLength());
		System.out.println("Window size k :" + wm2.getWindowSize());
		System.out.println("O(1) approach : ");		
		
		long startTime = System.currentTimeMillis();
		while(ds2.hasNext()){
			wm2.fetch();
			wm2.getMax();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" Time elapsed : " + (endTime - startTime) + " ms");
		
		
		System.out.println("\n");
		System.out.println("O(k) approach : ");
		DataStream ds3 = new DataStream(10000000, 1, 10, 123434L);
		//System.out.println(ds2);
		WindowMax wm3 = new WindowMax(10000, ds3);
		startTime = System.currentTimeMillis();
		while(ds3.hasNext()){
			wm3.fetch();
			wm3.getMaxNaive();
		}
		endTime = System.currentTimeMillis();
		System.out.println(" Time elapsed : " + (endTime - startTime) + " ms");
	}
}
