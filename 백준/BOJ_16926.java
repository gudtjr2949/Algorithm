import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// 백준 16926 : 배열 돌리기 1
public class BOJ_16926 {

	static int N;
	static int M;
	static int R;
	static int[][] board;

	static int[] nx = { 1, 0, -1, 0 };

	static int[] ny = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String nmr = bf.readLine();
		String[] nmr_arr = nmr.split(" ");

		N = Integer.parseInt(nmr_arr[0]);
		M = Integer.parseInt(nmr_arr[1]);
		R = Integer.parseInt(nmr_arr[2]);

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(s_arr[j]);
			}
		}

		int startX = 0;
		int startY = 0;

		int endX = M;
		int endY = N;

		// 안으로 드가기
		for (int i = 0; i < Math.min(N, M) / 2; i++) {
			solve(startX, startY, endX, endY);

			startX++;
			startY++;

			endX--;
			endY--;
		}
		print();
	}

	private static void solve(int startX, int startY, int endX, int endY) {
		Deque<Integer> deque = new LinkedList<>();

		int dir = 0; // 방향

		// 변화하는 위치
		int dx = startX;
		int dy = startY;
		
		// 덱에 차례대로 넣어주기
		while (dir < 4) {
			if (dy + ny[dir] >= endY || dy + ny[dir] < startY || dx + nx[dir] >= endX || dx + nx[dir] < startX) {
				dir++;
			} else {
				deque.add(board[dy][dx]);
				dx = dx + nx[dir];
				dy = dy + ny[dir];
			}
		}

		// R번 만큼 회전
		for (int i = 0; i < R; i++) {
			int tmp = deque.pollFirst();
			deque.addLast(tmp);
		}
		
		// 변수 초기화
		dir = 0;
		dx = startX;
		dy = startY;
		
		// 회전을 마친 덱 다시 board에 넣어주기
		while (dir < 4) {
			if (dy + ny[dir] >= endY || dy + ny[dir] < startY || dx + nx[dir] >= endX || dx + nx[dir] < startX) {
				dir++;
			}

			else {
				board[dy][dx] = deque.pollFirst();
				dx = dx + nx[dir];
				dy = dy + ny[dir];
			}
		}
	}
	
	private static void print() {
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
