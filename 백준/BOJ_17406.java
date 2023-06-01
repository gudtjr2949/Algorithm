import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 백준 17406 : 배열 돌리기4
public class BOJ_17406 {

	static int N, M, K;
	static int[][] board;
	static int[][] copy_board;
	static Point[] point;

	static int[] nx = { 1, 0, -1, 0 };
	static int[] ny = { 0, 1, 0, -1 };
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String nmk = bf.readLine();
		String[] nmk_arr = nmk.split(" ");

		N = Integer.parseInt(nmk_arr[0]);
		M = Integer.parseInt(nmk_arr[1]);
		K = Integer.parseInt(nmk_arr[2]);
		point = new Point[K];

		board = new int[N][M];
		copy_board = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(s_arr[j]);
				copy_board[i][j] = board[i][j];
			}
		}

		for (int i = 0; i < K; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			make_point(i, s_arr);
		}
		
		// 순서정하기
		make_order(new int[K], new boolean[K], 0);
		
		System.out.println(answer);
	}

	private static void make_order(int[] input, boolean[] visited, int idx) {
		if (idx == input.length) {
			solve(input);
			
			answer = Math.min(answer, find_min());
			
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < M ; j++) {
					copy_board[i][j] = board[i][j];
				}
			}
			
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				input[idx] = i;
				make_order(input, visited, idx + 1);
				visited[i] = false;
			}
		}
	}

	private static void solve(int[] order) {
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < (point[order[i]].end_x - point[order[i]].start_x) / 2 ; j++) {
				int start_x = point[order[i]].start_x + j;
				int start_y = point[order[i]].start_y + j;
				int end_x = point[order[i]].end_x - j;
				int end_y = point[order[i]].end_y - j;

				Deque<Integer> deque = new LinkedList<>();

				int dx = start_x;
				int dy = start_y;

				int idx = 0;

				while (idx < 4) {
					if (dx + nx[idx] > end_x || dx + nx[idx] < start_x || dy + ny[idx] > end_y
							|| dy + ny[idx] < start_y) {
						idx++;
					} else {
						deque.add(copy_board[dy][dx]);
						
						dx += nx[idx];
						dy += ny[idx];
					}
				}

				// 시계방향으로 1회 회전
				int tmp = deque.pollLast();
				deque.addFirst(tmp);

				dx = start_x;
				dy = start_y;

				idx = 0;

				// 다시 넣기
				while (idx < 4) {
					if (dx + nx[idx] > end_x || dx + nx[idx] < start_x || dy + ny[idx] > end_y
							|| dy + ny[idx] < start_y) {
						idx++;
					} else {
						copy_board[dy][dx] = deque.pollFirst();
						
						dx += nx[idx];
						dy += ny[idx];
					}
				}
			}
		}
	}
	
	private static int find_min() {
		int min = Integer.MAX_VALUE;
		
		for (int i = 0 ; i < N ; i++) {
			int sum = 0;
			for (int j = 0 ; j < M ; j++) {
				sum += copy_board[i][j];
			}
			
			min = Math.min(min, sum);
		}
		
		return min;
	}

	static class Point {
		int start_x;
		int start_y;
		int end_x;
		int end_y;

		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(int start_x, int start_y, int end_x, int end_y) {
			super();
			this.start_x = start_x;
			this.start_y = start_y;
			this.end_x = end_x;
			this.end_y = end_y;
		}
	}

	private static void make_point(int i, String[] s_arr) {
		int r = Integer.parseInt(s_arr[0]) - 1;
		int c = Integer.parseInt(s_arr[1]) - 1;
		int s = Integer.parseInt(s_arr[2]);

		point[i] = new Point(c - s, r - s, c + s, r + s);
	}
}
