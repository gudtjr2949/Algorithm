import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long answer;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		StringTokenizer st = new StringTokenizer(bf.readLine());

		arr = new int[N];

		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		solve();

		System.out.println(answer);
	}

	private static void solve() {
		for (int i = 0 ; i < N ; i++) {
			int startIdx = i + 1;
			int endIdx = N - 1;
			int value = arr[i];

			if (value > 0) {
				break;
			}

			// System.out.println(startIdx + " " + endIdx);
			while (startIdx < endIdx) {
				int sum = value + arr[startIdx] + arr[endIdx];

				if (sum == 0) {
					// 이 두 수가 같다면, 이미 정렬을 한 뒤이기 떄문에, 이 두 수 사이에 있는 수도 모두 같음
					if (arr[startIdx] == arr[endIdx]) {
						answer += ((endIdx - startIdx) * (endIdx - startIdx + 1)) / 2;
						break;
					} else {
						int startSame = 1;
						int endSame = 1;

						// 같은 수 확인
						while (arr[startIdx] == arr[startIdx + 1]) {
							startSame++;
							startIdx++;
						}

						while (arr[endIdx] == arr[endIdx - 1]) {
							endSame++;
							endIdx--;
						}

						answer += endSame * startSame;
					}
				}

				if (sum > 0) {
					endIdx--;
				} else {
					startIdx++;
				}
			}
		}
	}
}