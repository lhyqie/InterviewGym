package array;

/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */
public class GasStation {
	
	public static void main(String[] args) {
		int[] gas = {1,2};
		int[] cost = {2,1};
		int ret = canCompleteCircuit(gas, cost);
		System.out.println(ret);
	}
	
	public static int canCompleteCircuit(int[] gas, int[] cost) {
        // create an delta array d[0.. 2*n-2]
        int n = gas.length;
        //special case n==1
        if(n == 1 && gas[0] >= cost[0]) return 0;
        
        int[] d = new int[2*n - 1];
        //d[i] = gas[i] - cost[i] for i in [0...n-1]
        //d[i] = gas[i-n] - cost[i] for i in [n...2n-2]
        for(int i = 0; i < n; i++) d[i] = gas[i] - cost[i];
        for(int i = n; i < 2*n - 1; i++) d[i] = gas[i-n] - cost[i-n];
        
        //check if there is range d[i...i+n-1] such that all elements >=0 for i in [0, n-1]
        
        // calculate all consecutive sum of d,
        // if the sum >= 0 and range >= n, find the solution, otherwise not
        int start = 0, end = 0;   //[start, end) is the range
        int sum = 0;
        for(int i = 0; i < 2*n-1 ; i++){
            if(sum >=0 && end - start >= n) return start;
            if(sum +d[i] >=0){
                sum += d[i];
                end ++;
            }else{ //implies d[i] < 0
                sum = 0;
                start = i + 1;
                end = i+1;
            }
        }
        if(sum >=0 && end - start >= n) return start;
        else return -1;
    }
}

