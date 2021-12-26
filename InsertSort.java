
public class InsertSort {

	public static void main(String[] args) {
		int[] arr = { 5, 8, 7, 4, 3 };

		arr = insertSort(arr);

		for (int v : arr) {
			System.out.print(v + " ");
		}

	}

	static int[] insertSort(int[] arr) {
		for (int i = 1; i < arr.length; ++i) {
			int tmp = arr[i];
			for(int j = i -1 ; j >= 0 ; --j) {
				if(tmp > arr[j]) {
					arr[j+1] = tmp;
					break;
				}else {
					arr[j+1] = arr[j];
				}
				
				if(j == 0) {
					arr[j] = tmp;
				}	
			}
		}
		
		return arr;
	}
}
