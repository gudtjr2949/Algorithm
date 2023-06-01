import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17070 : 파이프 옮기기 1
public class BOJ_17070 {
	
	static int[][] dx = {{1, 1}, {0, 1}, {1, 0, 1}};
	static int[][] dy = {{0, 1}, {1, 1}, {0, 1, 1}};

	static int N;
	static int[][] map;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		answer = 0;
		
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		dfs(0, 0, 1, 0);
		
		System.out.println(answer);
	}

	private static void dfs(int idx, int dir, int x, int y) {
		if (x == N-1 && y == N-1) {
			answer++;
			return;
		}
		
		for (int i = 0 ; i < dx[dir].length ; i++) {
			int nx = x + dx[dir][i];
			int ny = y + dy[dir][i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (dx[dir][i] == 1 && dy[dir][i] == 1) { // 밀었을 때 파이프 모양이 대각선인 경우 
					if (map[ny-1][nx] == 0 && map[ny][nx] == 0 && map[ny][nx-1] == 0) {
						dfs(idx+1, 2, nx, ny);
					}
				}
				else if (nx == x) { // 밀었을 때 파이프 모양이 세로인 경우
					if (map[ny][nx] == 0 ) {
						dfs(idx+1, 1, nx, ny);
					}
				}
				else { // 밀었을 때 파이프 모양이 가로인 경우
					if (map[ny][nx] == 0) {
						dfs(idx+1, 0, nx, ny);
					}
				}
			}
		}
	}
}
