import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2579 : 계단 오르기
public class BOJ_2579 {
	
	static int N;
	static int[] cost, memo_1, memo_2;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		cost = new int[N+1];
		
		// memo[i] : i번째 위치까지 올라온 사람이 가질 수 있는 최대 점수를 저장하는 배열
		memo_1 = new int[N+1];
		memo_2 = new int[N+1];
		
		for (int i = 1 ; i <= N ; i++) {
			cost[i] = Integer.parseInt(bf.readLine());
		}
		
		memo_1[0] = 0;
		memo_1[1] = cost[1];
		
		memo_2[0] = 0;
		memo_2[1] = cost[1];

		if (N >= 2) {
			memo_1[2] = memo_1[1] + cost[2];
			memo_2[2] = cost[2];
			solve();
		}
		
		System.out.println(Math.max(memo_1[N], memo_2[N]));
	}

	private static void solve() {
		for (int i = 3 ; i <= N ; i++) {	
			memo_1[i] = Math.max(memo_1[i-2] + cost[i], memo_1[i-3] + cost[i-1] + cost[i]);
			memo_2[i] = Math.max(memo_2[i-2] + cost[i], memo_2[i-3] + cost[i-1] + cost[i]);
		}
	}
}
