import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 11066 : 파일 합치기
public class Main {
	
	static int K;
	static int[] C, sum;
	static int[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int Test = Integer.parseInt(bf.readLine());
		
		for (int T = 0 ; T < Test ; T++) {
			K = Integer.parseInt(bf.readLine());
			
			C = new int[K+1];
			sum = new int[K+1];
			memo = new int[K+1][K+1];
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			for (int i = 1 ; i <= K ; i++) {
				C[i] = Integer.parseInt(st.nextToken());

				sum[i] = sum[i-1] + C[i]; // 부분합
			}
			
			solve();

			sb.append(memo[1][K]).append("\n");
		}

		System.out.println(sb);
	}

	private static void solve() {
		for (int range = 1 ; range < K ; range++) { // 범위 길이
			
			for (int start = 1 ; start <= K - range ; start++) { // 시작
				int end = range + start;

				memo[start][end] = Integer.MAX_VALUE;

				for (int mid = start ; mid < end ; mid++) { // 시작부터 마지막 까지
					memo[start][end] = Math.min(memo[start][end], memo[start][mid] + memo[mid+1][end] + (sum[end] - sum[start-1]));
				}
			}
		}
	}
}