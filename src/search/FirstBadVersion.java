package search;

public class FirstBadVersion {
	FirstBadVersion(int n, int firstBad){
		this.n = n;
		this.firstBad = firstBad;
	}
	
	int n;
	int firstBad;
	boolean isBadVersion(int version){
		return version >= firstBad;
	}
	
	public static void main(String[] args) {
		int n = 2126753390;
		int firstBad = 1702766719;
		FirstBadVersion fbv = new FirstBadVersion(n, firstBad);
		System.out.println(fbv.firstBadVersion(n));
		
		n = 3;
		firstBad = 2;
		fbv = new FirstBadVersion(n, firstBad);
		System.out.println(fbv.firstBadVersion(n));

	}
	
	public int firstBadVersion(int n) {
        int left = 1, right = n;
        while(left <= right){
        	int mid = left + (right + 1 - left)/2;
        	System.out.println(left+" "+right + "  mid = "+mid);
        	if(isBadVersion(mid)){
                right = mid - 1;    //[right+1...n] are all bad versions;
            }else{
                left = mid + 1;
            }
        }
        // left == right + 1
        return left;
    }
}
