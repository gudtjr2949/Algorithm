import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 2206 : 벽 부수고 이동하기
public class BOJ_2206 {

	static int N;
	static int M;
	static int[][] board;
	static boolean[][][] visited;
	static int[] nx = { 0, 1, 0, -1 };
	static int[] ny = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String nm = bf.readLine();
		String[] nm_arr = nm.split(" ");

		N = Integer.parseInt(nm_arr[0]);
		M = Integer.parseInt(nm_arr[1]);
		board = new int[N][M];
		visited = new boolean[2][N][M];

		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}
		
		int answer = bfs(0, 0);
		
		System.out.println(answer);
	}

	private static int bfs(int x, int y) {
		Queue<Point> Q = new LinkedList<>();

		Q.add(new Point(x, y, 1, true));

		while (!Q.isEmpty()) {
			int size = Q.size();

			for (int i = 0; i < size; i++) {
				Point p = Q.poll();

				if (p.y == N - 1 && p.x == M - 1) {
					return p.depth;
				}

				if (p.chance) {
					visited[0][p.y][p.x] = true;
				} else {
					visited[1][p.y][p.x] = true;
				}

				for (int j = 0; j < 4; j++) {
					int dx = p.x + nx[j];
					int dy = p.y + ny[j];
					if (p.chance) {
						if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && !visited[0][dy][dx]) {
							if (board[dy][dx] == 0) {
								visited[0][dy][dx] = true;
								Q.add(new Point(dx, dy, p.depth + 1, p.chance));
							} else {
								visited[1][dy][dx] = true;
								Q.add(new Point(dx, dy, p.depth + 1, false));
							}
						}
					} else {
						if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && !visited[1][dy][dx] && board[dy][dx] == 0) {
							visited[1][dy][dx] = true;
							Q.add(new Point(dx, dy, p.depth + 1, p.chance));
						}
					}
				}
			}
		}
		return -1;
	}

	static class Point {
		int x;
		int y;
		int depth;
		boolean chance;

		public Point(int x, int y, int depth, boolean chance) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.chance = chance;
		}
	}
}
