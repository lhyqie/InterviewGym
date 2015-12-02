package greedy;

/*
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
 */
public class Candy {
	public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0) return 0;
        int c[] = new int[ratings.length];
        for(int i = 0; i < ratings.length; i++) c[i] = 1;
        
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i-1] < ratings[i]){
                c[i] = c[i-1] + 1;
            }
        }
        for(int i = ratings.length - 1; i >= 1; i--){
            if(ratings[i-1] > ratings[i] && c[i-1] <= c[i]){
                c[i-1] = c[i] + 1;
            }
        }
        
        int res =0 ;
        for(int i = 0; i < ratings.length; i++) res += c[i];
        return res;
    }
}
