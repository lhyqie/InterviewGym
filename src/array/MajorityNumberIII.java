package array;

import java.util.*;


public class MajorityNumberIII {
	
	 public static void main(String[] args) {
		 //int [] arr = {3,1,2,3,2,3,3,4,4,4}; 
		 //int k = 3;
		 
		 int[] arr = {32,125,176,234,170,147,151,243,67,62,20,
				 	  149,191,129,131,107,126,50,194,63,191,191,
				 	  13,139,191,164,239,119,234,79,51,160,194,140,
				 	  191,165,80,191,26,26,191,26,16,252,196,12,191,
				 	  191,249,52,161,169,94,140,250,75,110,143,57,255,
				 	  90,143,191,71,16,22,50,252,191,138,191,142,221,104,
				 	  182,57,47,191,179,63,191,68,91,185,225,183,69,216,
				 	  146,152,164,172,169,68,245,123,191,191,219,207,244,
				 	  147,215,42,121,112,241,179,27,162,243,133,148,178,214,
				 	  191,208,138,45,62,191,56,232,74,197,154,225,31,136,191,
				 	  244,166,41,48,50,94,245,239,103,191,191,161,180,82,210,
				 	  191,191,253,163,171,140,249,198,51,85,93,55,76,32,191,
				 	  191,27,57,231,163,205,134,165,40,11,191,133,183,164,138,
				 	  75,191,22,232,248,54,136,106,109,229,242,121,210,218,28,
				 	  72,252,90,177,184,60,229,81,98,36,48,21,230,120,19,202,
				 	  76,196,236,44,162,94,89,151,72,191,242,187,218,228,62,
				 	  169,62,187,162,232,24,236,164,28,63,117,212,191,206,15,
				 	  209,85,37,177,23,250,30,126,246,48,115,96,198,106,198,139,
				 	  19,118,153};
		 int k = 9;
		 
		 ArrayList<Integer> nums = new ArrayList<Integer>();
		 for(int num : arr) nums.add(num);
		 
		 int ret = majorityNumber(nums, k);
		 System.out.println(ret);
	 }
	
	 
	// http://blog.csdn.net/nicaishibiantai/article/details/43636799
	    /*
	      思路和Majority NumberII 一样，维护k-1个candidate 在map里面，key为数字值，value为出现次数。先找到这k-1个candidate后，扫描所有元素，如果该元素存在在map里面，update map；如果不存在，1： 如果map里面有值为count= 0，那么删除掉这个元素，加入新元素；2：map里面没有0出现，那么就每个元素的count--
	        剩下的map里面的值都有可能是majority，所以重新扫描数组，记录下每一个元素出现次数，次数最大的就是majority
	    */
	    public static int majorityNumber(ArrayList<Integer> nums, int k) {
	        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	        int totalCan = 0;
	        int i = 0;
	        while (totalCan < k && i < nums.size()) {
	            int cur = nums.get(i);
	            if (map.containsKey(cur)) {
	                map.put(cur, map.get(cur) + 1);
	            } else {
	                map.put(cur, 1);
	                totalCan++;
	            }
	            i++;
	        }
	        while (i < nums.size()) {
	            int cur = nums.get(i);
	            if (map.containsKey(cur)) {
	                map.put(cur, map.get(cur) + 1);
	            } else { // not match any candidate
	                if (map.values().contains(0)) {
	                    map.put(cur, 1);
	                    Integer zeroKey = null;
	                    for (Map.Entry entry : map.entrySet()) {
	                        if (entry.getValue().equals(0)) {
	                            zeroKey = (Integer) entry.getKey();
	                            break;
	                        }
	                    }
	                    map.remove(zeroKey);
	                } else {
	                    for (Map.Entry entry : map.entrySet()) {
	                        map.put((Integer) entry.getKey(), (Integer) entry.getValue() - 1);
	                    }
	                }
	            }
	            i++;
	        }

	        Map<Integer, Integer> newMap = new HashMap<Integer, Integer>();
	        int maxCount = 0;
	        int maxNum = 0;
	        for (int j = 0; j < nums.size(); j++) {
	            int cur = nums.get(j);
	            if (map.containsKey(cur)) {
	                newMap.put(cur, newMap.get(cur) == null ? 1 : newMap.get(cur) + 1);
	                if (newMap.get(cur) > maxCount) {
	                    maxCount = newMap.get(cur);
	                    maxNum = cur;
	                }
	            }
	        }
	        return maxNum;
	    }
	    
	    
	 // 有个例子没pass
	 /*
	 public static int majorityNumber(ArrayList<Integer> nums, int k) {
        Integer cur[] = new Integer[k];
        for(int i = 0; i < k; i++){ cur[i] = new Integer(Integer.MIN_VALUE); }
        int cnt[] = new int[k];
        
        for(int num : nums){
        	if(num == 191){
        		System.out.print(" ");
        	}
        	boolean found = false;
            for(int i = 0; i < k; i++){
                if(cur[i] == num && cnt[i] > 0) {
                    found = true;
                    cnt[i] ++ ; 
                    break;
                }
            }
            
            if(!found){
	            for(int i = 0; i < k; i++){
	                if(cnt[i] == 0){
	                    cur[i] = num;
	                    found = true;
	                    cnt[i] = 1;
	                    break;
	                }
	            }
       
	            if(!found){
	            	for(int i = 0; i < k; i++) {
	            		if(cnt[i] > 0 ) cnt[i] -- ;
	            	}
	            }
            }
//          System.out.println("num = " + num);
//	        System.out.println(Arrays.toString(cur));
//	        System.out.println(Arrays.toString(cnt));
//	        System.out.println();
        }
        

        
        
        for(int i = 0; i < k; i++){ cnt[i] = 0; }
        
        for(Integer num : nums){
            for(int i = 0; i < k; i++){
                if(cur[i] == num) cnt[i] ++ ; 
            }
        }
        
//        System.out.println(Arrays.toString(cur));
//        System.out.println(Arrays.toString(cnt));
        
        int maxIndex = 0;
        for(int i = 0; i < k; i++){
            if(cnt[i] > nums.size()/k && cnt[maxIndex] < cnt[i]) maxIndex = i;
        }
        return cur[maxIndex];
	        
	 }
	 */
}
