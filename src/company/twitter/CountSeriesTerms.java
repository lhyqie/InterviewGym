package company.twitter;

public class CountSeriesTerms {
	public static void main(String[] args) {
		System.out.println(countSeriesTerms(1,1,1,2,4));
		System.out.println(countSeriesTerms(2,2,2,3,54));
		System.out.println(countSeriesTerms(1,1,1,2,1000000000));
		System.out.println(countSeriesTerms(1,1,1,1,1000000000));
		System.out.println(countSeriesTerms(1,1,800,1,1000000000));
	}
	
	public static int countSeriesTerms(int A, int D, int B, int R, int Lim){
		int cnt = 0;
        int a = A, b = B;
        if(R == 1){
        	while( a <= Lim ){
        		if(a == b){
                    cnt ++;
        		}
        		a += D;
        	}
        }else{
        	while( a <= Lim || b <= Lim){
                if(a == b){
                    cnt ++;
                    a += D;
                    b *= R;
                }else if (a < b){
                    a += D;
                }else{ //
                    b *= R;
                }
            }
        }
        return cnt;
    }
	
//	public static int countSeriesTerms(int A, int D, int B, int R, int Lim){
//        int cnt = 0;
//        int aVal = A, gVal = B;
//        while (aVal<=Lim || gVal<=Lim){
//            if (aVal==gVal){
//                cnt++;
//                aVal = aVal+D;
//                gVal=gVal*R;
// 
//            }
//            else if (aVal<gVal){
//                aVal = aVal+D;
//            }
//            else{
//                gVal=gVal*R;
//            }
//        }
// 
//        return cnt;
//    }
}
