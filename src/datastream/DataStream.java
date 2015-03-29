package datastream;

import java.util.Arrays;

import utils.ArrayUtils;

public class DataStream {
	
	private int data[] = null;
	private int t = 0;   //t starting from 0
	
	public DataStream(){
		data = ArrayUtils.randomIntArray(100, 0, 10);
	}
	
	public DataStream(int n){
		data = ArrayUtils.randomIntArray(n, 0, 10);
	}
	
	public DataStream(int n, int min, int max){
		data = ArrayUtils.randomIntArray(n, min, max);
	}
	
	public DataStream(int n, int min, int max, long seed){
		data = ArrayUtils.randomIntArray(n, min, max, seed);
	}
	
	public DataStream(int[] data){
		this.data = data;
	}
	
	public int getLength(){
		return data.length;
	}
	
	public String toString(){
		return Arrays.toString(data);
	}
	/**
	 * @return : the timestamp of the next coming element 
	 */
	public int getTimeStamp(){
		return t;
	}
	
	/**
	 * @return: whether data stream ends
	 */
	public boolean hasNext(){
		return t < data.length;
	}
	
	/**
	 * @return : the next element at timestamp t
	 */
	public int next(){
		if(!hasNext()) throw new RuntimeException("end of streaming data");
		return data[t++];
	}
	
	
}
