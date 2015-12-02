package company.twitter;

public class TrailingZeros {
	public static void main(String[] args) {
		System.out.println(zeros(23));
		System.out.println(zeros(11));
	}
	
	static int zeros(int N) {
        int sum = 0;
        for(int i = 5; i<=N; i*=5){
            sum += N/i;
        }
        return sum;
    }

}
