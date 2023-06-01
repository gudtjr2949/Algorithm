import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2098 : 외판원 순회
public class BOJ_2098 {
	
	static int N;
	static int[][] W;
	static int[][] memo;
	static final int cannotCycle = 17 * 1000000;
    static final int notVisit = cannotCycle * 2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		W = new int[N][N];
		memo = new int[N][(1 << N) - 1];
		
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < N ; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0 ; i < N ; i++) {
			Arrays.fill(memo[i], notVisit);
		}
		
		System.out.println(dfs(0, 0));
	}

	// now : 현 위치
	// visited : 현재까지 방문한 마을을 이진수로 나타낸 것 (1 : 방문, 0 : 미방문)
	private static int dfs(int now, int visited) {
		
		visited = visited | (1 << now);
		
		// basis part : 모든 도시들을 다 방문했다면?
		if (visited == (1 << N) - 1) {
			if (W[now][0] == 0) {
				return cannotCycle;
			}
			
			return W[now][0];
		}
		
		if (memo[now][visited] != notVisit) {
			return memo[now][visited];
		}
		
		// i : 갈 도시
		for (int i = 0 ; i < N ; i++) {
			if ((visited & (1 << i)) == 0 && W[now][i] != 0) {
				memo[now][visited] = Math.min(memo[now][visited], dfs(i, visited) + W[now][i]);
			}
		}
		
		return memo[now][visited];
	}
	
}
