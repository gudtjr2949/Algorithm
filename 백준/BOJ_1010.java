package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1010 : 다리 놓기
public class BOJ_1010 {

	static long[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());

		for (int Test = 0; Test < T; Test++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int R = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			memo = new long[N+1][R+1];

			sb.append(combi(N, R)).append("\n");
		}

		System.out.println(sb);
	}

	private static long combi(int n, int r) {

		// nCr 을 구한 적이 있다면?
		if (memo[n][r] > 0) {
			return memo[n][r];
		}

		if (r == 0 || n == r) {
			return memo[n][r] = 1;
		}

		return memo[n][r] = combi(n-1, r-1) + combi(n-1, r);
	}
}
