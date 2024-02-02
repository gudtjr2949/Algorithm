import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 9663 : N-Queen
public class Main {

	static int N;
	static int[][] board;

	static int[] nx = { 1, 0, -1 };
	static int[] ny = { -1, -1, -1 };

	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		board = new int[N][N];
		cnt = 0;

		solve(0);

		System.out.println(cnt);
	}

	private static void solve(int y) {
		if (y == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (check(i, y)) {
				board[y][i] = 1;
				solve(y + 1);
				board[y][i] = 0;
			}
		}
	}

	private static boolean check(int x, int y) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int dx = x + (i * nx[j]);
				int dy = y + (i * ny[j]);

				if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && board[dy][dx] == 1) {
					return false;
				}
			}
		}

		return true;
	}
}