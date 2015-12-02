package segtree;

// http://www.1point3acres.com/bbs/thread-107864-1-1.html
// http://www.1point3acres.com/bbs/thread-116605-1-1.html
// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=143115
// http://www.1point3acres.com/bbs/thread-138854-1-1.html

/* Enter your code here. Read input from STDIN. Print output to STDOUT */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class TimeRacer implements Comparable<TimeRacer>{
    long start;
    long end;
    int score;
    int racer; 
    int rank;
    public TimeRacer(Integer racer){
    	this.racer = racer.intValue();
    }
    @Override
    public int compareTo(TimeRacer o) {
        if(this.rank > o.rank) return 1;
        if(this.rank < o.rank) return -1;
        return 0;
    }
}

public class SpScorer {
	
    public static void main(String[] args) throws IOException {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        
        PriorityQueue<TimeRacer> startPQ = new PriorityQueue<TimeRacer>(n, new Comparator<TimeRacer>(){
            @Override
            public int compare(TimeRacer arg0, TimeRacer arg1) {
                    if(arg0.start > arg1.start) return 1;
                    if(arg0.start < arg1.start) return -1;
                    return 0;
            }     
        });
        
        PriorityQueue<TimeRacer> endPQ = new PriorityQueue<TimeRacer>(n, new Comparator<TimeRacer>(){
            @Override
            public int compare(TimeRacer arg0, TimeRacer arg1) {
                    if(arg0.end > arg1.end) return 1;
                    if(arg0.end < arg1.end) return -1;
                    return 0;
            }
        });
        
        PriorityQueue<TimeRacer> rstPQ = new PriorityQueue<TimeRacer>(n, new Comparator<TimeRacer>(){
            @Override
            public int compare(TimeRacer arg0, TimeRacer arg1) {
                    if(arg0.score > arg1.score) return 1;
                    if(arg0.score < arg1.score) return -1;
                    return 0;
            }
        });
        
        int lines = n;
        while (lines > 0) {
            line = br.readLine();
            String[] fields = line.split("\\s");
            TimeRacer curr = new TimeRacer(Integer.valueOf(fields[0]));
            curr.start = Long.valueOf(fields[1]);
            curr.end = Long.valueOf(fields[2]);
            startPQ.offer(curr);
            endPQ.offer(curr);
            lines--;
        }
        int count = 0;
        while(startPQ.size() > 0){
            TimeRacer t = startPQ.poll();
            t.rank = count;
            count++;
        }
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(endPQ.size() > 0){
            TimeRacer t = endPQ.poll();
            t.score = getScore(arr, t);
            rstPQ.offer(t);
        }
        while(rstPQ.size() > 0){
            TimeRacer t = rstPQ.poll();
            System.out.print(t.racer);
            System.out.print(" ");
            System.out.print(t.score);
            if(rstPQ.size() > 0) System.out.println();
        }
    }
    
    private static int getScore(ArrayList<Integer> arr, TimeRacer t){
        int idx = Collections.binarySearch(arr, t.rank);
        idx = (idx + 1) * -1; // java search will return insert position as (-pos-1).
        int rst = arr.size() - idx;
        arr.add(idx, t.rank);
        return rst;
    }  
}
