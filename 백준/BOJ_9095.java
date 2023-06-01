import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 9095 : 1, 2, 3 더하기
public class BOJ_9095 {

	static int[] memo;
	static int N;
	static int[] arr = { 1, 2, 3 };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int T = 0; T < Test; T++) {
			N = Integer.parseInt(bf.readLine());

			memo = new int[N + 1];
			answer = 0;
			
//			dp_memo();

			for (int i = 1; i <= N; i++) {
				dfs(0, new int[i]);
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int idx, int[] input) {
		if (idx == input.length) {
			int sum = 0;
			for (int i = 0 ; i < input.length ; i++) {
				sum += input[i];
			}

			if (sum == N) {
				answer++;
			}
			return;
		}

		for (int i = 0; i < 3; i++) {
			input[idx] = arr[i];
			dfs(idx + 1, input);
		}
	}

	private static void dp_memo() {

	}

}
