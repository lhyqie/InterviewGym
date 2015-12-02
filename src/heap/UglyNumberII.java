package heap;

import java.util.PriorityQueue;


// http://www.lintcode.com/en/problem/ugly-number/
public class UglyNumberII {
	public static long kthPrimeNumber(int k) {
        // write your code here
        int cnt = 0;
        PriorityQueue<Long> pq = new PriorityQueue<Long>(10);
        pq.offer(3L);
        pq.offer(5L);
        pq.offer(7L);
        while(true){
            long top = pq.poll();
            cnt ++ ;
            if(cnt == k) return top;
            if(!pq.contains(top*3)) pq.offer(top * 3);
            if(!pq.contains(top*5)) pq.offer(top * 5);
            if(!pq.contains(top*7)) pq.offer(top * 7);
        }
    }
	
	public static int nthUglyNumber(int n) {
        int f[] = new int[n+1];
        f[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        for(int i = 1 ; i < n; i++){
            int next2 = f[p2]*3, next3 = f[p3]*5, next5 = f[p5]*7;
            int min = Math.min(Math.min(next2, next3), next5);
            if(min == next2) p2++;
            if(min == next3) p3++;
            if(min == next5) p5++;
            f[i] = min;
        }
        return f[n-1];
    }
	
	public static void main(String[] args) {
		for(int k = 1; k <= 11; k++){
			System.out.println("k =" +k + "  number : " + kthPrimeNumber(k) + " \t" + nthUglyNumber(k+1));
		}
	}
}
