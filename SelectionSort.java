
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 5, 8, 7, 4, 3 };

		arr = selectionSort(arr);

		for (int v : arr) {
			System.out.print(v + " ");
		}

	}
	
	static int[] selectionSort(int[] arr) {
		
		for(int j = 0 ; j < arr.length-1; j++) {
		
		int index = j;
		int min = arr[index];
		for(int i = j ; i < arr.length ; i++) {
			if(min > arr[i]) {
				min = arr[i];
				index = i;
			}
		}
		
		int tmp = arr[j];
		arr[j] = arr[index];
		arr[index] = tmp;
		}
	
		return arr;
	}
	
}
