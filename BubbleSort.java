
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {5,8,7,4,3};
		
		arr = bubbleSort(arr);
		
		for(int v:arr) {
			System.out.print(v+" ");
		}
	}
	
	static int[] bubbleSort(int[] arr){
		for(int j = arr.length-1 ; j >= 0; j--) {
			for(int i = 0 ; i < j ; i++) {
				if(arr[i] > arr[i+1]) {
			   		 int tmp = arr[i+1];
			   		 arr[i+1] = arr[i];
			   		 arr[i] = tmp;
			   	 }	
			}
		}
		return arr;
	}
	
	
}
