package datastream;

/**
 * Online algorithm for computing the average most recent k elements of streaming data 
 * @author howie
 * Time complexity O(1) for each request
 */
public class WindowAverage {
	
	private int k; 			// window size
	private DataStream ds;  // DataStream object
	private float avg;      // current average
	private int[] buffer;   // buffer of size k
	private int t;          // current timestamp

	/**
	 * @param k : window size k
	 * @param ds : DataStream object
	 */
	public WindowAverage(int k, DataStream ds){
		this.k = k;
		this.ds = ds;
		this.buffer = new int[k];
	}
	
	public int getCurrentTimeStamp(){
		return this.t;
	}
	/**
	 * @return : the new element that received
	 */
	public int getCurrent(){
		int t = ds.getTimeStamp();
		if(t == 0) throw new RuntimeException("fetch one from data stream first");
		return buffer[(t-1) % k];
	}
	
	/**
	 * @return : the average of most recent k elements
	 * Time complexity O(1)
	 */
	public float getAverage(){
		return avg;	
	}
	/**
	 * @return : the average of most recent k elements
	 * Time complexity O(k)
	 */
	public float getAverageNaive(){
		float avg = 0;
		int t = ds.getTimeStamp();
		if(t < k){  // first k steps
			for (int i = 0; i < t; i++) {
				avg += buffer[i];
			}
			avg /= t;
		}else{
			for (int i = 0; i < k; i++) {
				avg += buffer[i];
			}
			avg /= k;
		}
	
		return avg;
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
		if(t < k){  // first k steps
			buffer[t] = ds.next();
			avg = (avg * t + buffer[t]) / (t + 1);
		}else{
			int newData = ds.next();
			int oldData = buffer[t%k];
			buffer[t%k] = newData;      //replace the old one
			avg += (newData - oldData) * 1.0 / k;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("================================= Correctness Test  =====================================");
		DataStream ds = new DataStream(10, 1, 10, 123434L);
		System.out.println(ds);
		WindowAverage wa = new WindowAverage(3, ds);
		System.out.println("Window size k :" + wa.getWindowSize());
		while(ds.hasNext()){
			wa.fetch();
			System.out.println(String.format("t : %s \t element : %d \t naive average : %10.4f \taverage : %10.4f ",
					wa.getCurrentTimeStamp(), wa.getCurrent(), wa.getAverageNaive(), wa.getAverage()));
		}
		
		System.out.println("\n\n================================= Performance Test  =====================================");
		DataStream ds2 = new DataStream(1000000, 1, 10, 123434L);
		WindowAverage wa2 = new WindowAverage(1000, ds2);
		
		System.out.println("# of elements in the data stream : " + ds2.getLength());
		System.out.println("Window size k :" + wa2.getWindowSize());
		System.out.println("O(1) approach : ");		
		
		long startTime = System.currentTimeMillis();
		while(ds2.hasNext()){
			wa2.fetch();
			wa2.getAverage();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" Time elapsed : " + (endTime - startTime) + " ms");
		
		
		System.out.println("\n");
		System.out.println("O(k) approach : ");
		DataStream ds3 = new DataStream(10000000, 1, 10, 123434L);
		//System.out.println(ds2);
		WindowAverage wa3 = new WindowAverage(10000, ds3);
		startTime = System.currentTimeMillis();
		while(ds3.hasNext()){
			wa3.fetch();
			wa3.getAverageNaive();
		}
		endTime = System.currentTimeMillis();
		System.out.println(" Time elapsed : " + (endTime - startTime) + " ms");
	}
}
 