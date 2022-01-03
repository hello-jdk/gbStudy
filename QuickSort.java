
public class QuickSort {

	static int[] data = { 5, 8, 7, 4, 3 };

	public static void main(String[] args) {

		System.out.println("=== ���� �� �迭 ===");
		for (int v : data) {
			System.out.print(v + " ");
		}
		System.out.println();

//			System.out.println("=== ���� �� �迭 ===");
//			quick(data, 0, data.length - 1);

		System.out.println("=== ���� �� �迭 ===");
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

		// cycle ����
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

		// �ǹ� ����
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
