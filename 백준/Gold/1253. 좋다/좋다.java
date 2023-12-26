import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, answer;
	static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());

		A = new int[N];

		answer = 0;

		StringTokenizer st = new StringTokenizer(bf.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		solve();

		System.out.println(answer);
	}

	private static void solve() {
		for (int i = 0; i < N; i++) {
			int start = 0;
			int end = N - 1;
			int target = A[i];

			while (start < end) {
				int sum = A[start] + A[end];

				if (sum == target) {
					if (start != i && end != i) {
						answer++;
						break;
					} else if (end == i) {
						end--;
					} else if (start == i) {
						start++;
					}
				} else if (sum < target) {
					start++;
				} else {
					end--;
				}
			}
		}
	}
}