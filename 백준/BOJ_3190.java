package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 백준 3190 : 뱀
public class BOJ_3190 {
	
	static int N, K, L;
	static char[][] map;
	static Move[] move;
	static Deque<Point> deque;
	static int[] nx = {0, 1, 0, -1, 0};
	static int[] ny = {0, 0, 1, 0, -1};
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		K = Integer.parseInt(bf.readLine());

		answer = 0;

		deque = new ArrayDeque<>();

		deque.add(new Point(0, 0));
		
		map = new char[N][N];
		map[0][0] = 'S';
		for (int i = 0 ; i < K ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			map[y][x] = 'A';
		}
		
		L = Integer.parseInt(bf.readLine());
		move = new Move[L];
		
		for (int i = 0 ; i < L ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			move[i] = new Move(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}

		solve();

		System.out.println(answer);
	}

	private static void solve() {
		int dir = 1;
		int cnt = 1;
		int move_idx = 0;

		while (true) {

			if(!snakeMove(dir)) {
				answer = cnt;
				break;
			}

			if (move_idx < L) {
				if (cnt == move[move_idx].time) {
					dir = setDir(dir, move_idx);
					move_idx++;
				}
			}

			cnt++;
		}
	}

	private static int setDir(int dir, int move_idx) {
		if (move[move_idx].dir == 'D') {
			dir++;
			if (dir == 5) {
				dir = 1;
			}
		}
		else {
			dir--;
			if (dir == 0) {
				dir = 4;
			}
		}

		return dir;
	}

	private static boolean snakeMove(int dir) {
		// 머리 움직이기
		int dx = deque.peekLast().x + nx[dir];
		int dy = deque.peekLast().y + ny[dir];

		if (dy >= N || dy < 0 || dx >= N || dx < 0) {
			return false;
		}

		// 빈칸
		if (map[dy][dx] == 0) {
			Point tail = deque.pollFirst();
			map[tail.y][tail.x] = 0;
			map[dy][dx] = 'S';
			deque.addLast(new Point(dx, dy));
		}
		else if (map[dy][dx] == 'A') { // 사과
			map[dy][dx] = 'S';
			deque.add(new Point(dx, dy));
		}
		else if (map[dy][dx] == 'S') { // 자기 몸을 만난 경우
			return false;
		}

		return true;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Move {
		int time;
		char dir;
		
		public Move(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}
}
