package sort;

import java.util.*;

import cern.colt.Arrays;

public class BucketSort {
	public static void main(String[] args) {
		double arr[] = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
		System.out.println(Arrays.toString(arr));
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));	 	   
	}
	
	private static void bucketSort(double[] arr) {
		int n = arr.length;
		List<List<Double>> buckets = new ArrayList<List<Double>>(n + 1);  // create one more bucket to deal with
																		  // boundary
		for(int i = 0; i <= n; i++){
			buckets.add(new ArrayList<Double>());
		}
		
		// 1. define the range of bucket
		double min = Double.MAX_VALUE;
		double max = Double.MIN_NORMAL;
		for(int i = 0; i < n; i++){
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		
		double range = max - min;
		double L  = range / n ; 
		// for i in [0, n) range of bucket[i] : [min + i * L, min + (i+1) * L) 
		//                          bucket[0]  : [min,  min + L)
		//                          bucket[n-1] : [min + (n-1)* L,  min + n *L )
		//                          bucket[n] :   [min+ n*L, ...)                //cover the max
		// index of bucket that a number x belongs to   floor((x - min)/ L)
		
		// 2. put numbers to bucket
		for(int i = 0; i < n; i++){
			double x = arr[i];
			int bid = (int)((x - min) / L);
			buckets.get(bid).add(x);
		}
		
		// 3 sort each bucket
		for(int i = 0; i <= n; i++){
			Collections.sort(buckets.get(i));
		}
		
		// 4 merge back
		int p = 0;
		for(int i = 0; i <=n; i++){
			for(int j = 0; j < buckets.get(i).size(); j++){
				arr[p++] = buckets.get(i).get(j);
			}
		}
	}
}
