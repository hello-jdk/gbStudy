
public class QuickSort {

	static int[] data = { 5, 8, 7, 4, 3 };

	public static void main(String[] args) {

		System.out.println("=== 정렬 전 배열 ===");
		for (int v : data) {
			System.out.print(v + " ");
		}
		System.out.println();

//			System.out.println("=== 정렬 중 배열 ===");
//			quick(data, 0, data.length - 1);

		System.out.println("=== 정렬 후 배열 ===");
		for (int v : data) {
			System.out.print(v + " ");
		}

	}

	static void quick(int[] data, int start, int end) {

		int p = start;
		int L = start + 1;
		int H = end;

		if (end - start < 1)
			return;

		// cycle 정렬
		while (L <= H) {
			while (data[p] < data[H]) {
				H--;
				if (L > H)
					break;
			}

			while (data[p] > data[L]) {
				L++;
				if (L > H)
					break;
			}

			if (L < H) {
				int tmp = data[H];
				data[H] = data[L];
				data[L] = tmp;
			}
		}

		// 피벗 정렬
		int tmp = data[p];
		data[p] = data[H];
		data[H] = tmp;

//			for(int v : data) {
//				System.out.print(v+" ");
//			}
//			System.out.println("p:"+p+" H:"+H+" L:"+L);

		quick(data, start, H - 1);
		quick(data, H + 1, end);

	}

}
