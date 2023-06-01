import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 백준 10972 : 다음 순열
public class BOJ_10972 {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		Integer[] reverse = new Integer[N];

		String s = bf.readLine();
		String[] s_arr = s.split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(s_arr[i]);
			reverse[i] = arr[i];
		}

		Arrays.sort(reverse, Collections.reverseOrder());

		boolean sign = true;

		for (int i = 0; i < N; i++) {
			if (arr[i] != reverse[i]) {
				sign = false;
				break;
			}
		}

		if (!sign) {
			solve();
			print();
		} else {
			System.out.println(-1);
		}
	}

	private static void solve() {
		int n = arr.length;

		int i = n - 1; // 젤 마지막 숫자 인덱스

		while (i > 0 && arr[i - 1] >= arr[i]) // 앞에 더 큰게 있는지 찾기
			i--;

		int j = n - 1; // 젤 마지막 숫자 인덱스

		while (j > 0 && arr[i - 1] >= arr[j]) 
			j--;

		swap(i-1, j);

		int k = n - 1;
		while (i < k) {
			swap(i++, k--);
		}
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void print() {
		for (int i = 0 ; i < N ; i++)
			System.out.print(arr[i] + " ");
	}

}
