package sort;

public class NutsAndBolts {
	
	// Method to print the array
    private static void printArray(char[] arr) {
        for (char ch : arr){
            System.out.print(ch + " ");
        }
        System.out.print("\n");
    }
    
    public static void main(String[] args)
    {
        // Nuts and bolts are represented as array of characters
        
    	char nuts[] = {'@', '#', '$', '%', '^', '&'};
        char bolts[] = {'$', '%', '&', '^', '@', '#'};
    	
    	//char nuts[] = {'6', '2', '1', '4', '5', '3'};
        //char bolts[] = {'2', '3', '4', '1', '6', '5'};
 
        // Method based on quick sort which matches nuts and bolts
        matchPairs(nuts, bolts, 0, 5);
 
        System.out.println("Matched nuts and bolts are : ");
        printArray(nuts);
        printArray(bolts);
    }
    
    
    public static void swap(char[] arr, int i, int j){
    	char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

 
    // Method which works just like quick sort
    private static void matchPairs(char[] nuts, char[] bolts, int low,
                                                              int high)
    {
        if (low < high)
        {
            // Choose last character of bolts array for nuts partition.
            int pivot = partition(nuts, low, high, bolts[high]);
            // Now using the partition of nuts choose that for bolts
            // partition.
            partition(bolts, low, high, nuts[pivot]);
 
            // Recur for [low...pivot-1] & [pivot+1...high] for nuts and
            // bolts array.
            matchPairs(nuts, bolts, low, pivot-1);
            matchPairs(nuts, bolts, pivot+1, high);
        }
    }
 
    // Similar to standard partition method. Here we pass the pivot element
    // too instead of choosing it inside the method.
    private static int partition(char[] arr, int left, int right, char pivot)
    {	
    	int indexPivot = -1;  // the index of element that equals == pivot
        while(left <= right){
            while(left <= right && arr[left] < pivot) left ++;
            while(left <= right && arr[right] >= pivot) {
            	if(arr[right] == pivot) indexPivot = right;
            	right --;
            }
            if(left < right){
                swap(arr, left, right);
                if(arr[right] == pivot) indexPivot = right;
                left ++ ; right --;
            }
        }
        swap(arr, indexPivot, right +1);   // swap so that arr[right+1] == pivot
        return right + 1;
        
    	/*  // alternative way
        int i = left;
        for (int j = left; j < right; j++)
        {
            if (arr[j] < pivot){
                swap(arr, i, j);
                i++;
            } else if(arr[j] == pivot){
                swap(arr, j, right);
                j--;
            }
        }
        swap(arr, i, right); 		
        // Return the partition index of an array based on the pivot 
        // element of other array.
        return i;
		*/
    	
    }
}
