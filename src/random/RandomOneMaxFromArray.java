package random;

public class RandomOneMaxFromArray {
	public static void main(String[] args) {
		int arr[] = {0,1,2,3,4,5,6, 1,2,6, 5, 6, 1};
		int x = randomOne(arr);
		System.out.println(x);
		
		int y = randomOneMax(arr);
		System.out.println(y);
	}
	
	public static int randomOne(int arr[]){
		int ret = 0;
		int cnt = 0;
		for(int i = 0; i < arr.length; i++){
			int r = (int)(Math.random() * cnt);
			if(r == 0){
				ret = arr[i];
			}
			cnt ++;
		}
		return ret;
	}
	
	public static int randomOneMax(int arr[]){
		int maxCnt = 0;
		int max = Integer.MIN_VALUE;
		int ret = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			if(max <= arr[i]){
				max = arr[i];
			}
			if(arr[i] == max){
				maxCnt ++;
				int r = (int)(Math.random() * maxCnt);
				if(r == 0){
					ret = arr[i];
				}
			}else{
				ret = max;
			}
		}
		return ret;
	}
}
