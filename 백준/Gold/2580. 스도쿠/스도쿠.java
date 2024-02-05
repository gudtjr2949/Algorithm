import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 2580 : 스도쿠
public class Main {

	static int[][] board;
	static ArrayList<Point> list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		list = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(s_arr[j]);
				if (board[i][j] == 0) {
					list.add(new Point(j, i));
				}
			}
		}

		solve(0);
	}

	private static void solve(int idx) {
		if (idx != 0 && !isAvailable(list.get(idx-1))) {
			return;
		}
		
		if (idx == list.size()) {
			print();
			System.exit(0);
		}

		int x = list.get(idx).x;
		int y = list.get(idx).y;

		// 1 ~ 9까지 차례대로 넣어보기
		for (int i = 0; i < 9; i++) {
			board[y][x] = i + 1;
			solve(idx + 1);
			board[y][x] = 0;
		}
	}

	private static boolean isAvailable(Point p) {

		int x = p.x;
		int y = p.y;
		int num = board[p.y][p.x];

		// 가로
		for (int i = 0; i < 9; i++) {
			if (board[y][i] == num) {
				if (i != x)
					return false;
			}
		}

		// 세로
		for (int i = 0; i < 9; i++) {
			if (board[i][x] == num) {
				if (i != y)
					return false;
			}
		}

		int dx = (x / 3) * 3;
		int dy = (y / 3) * 3;

		// 사각형
		for (int i = dy; i < dy + 3; i++) {
			for (int j = dx; j < dx + 3; j++) {
				if (board[i][j] == num) {
					if (i != x && i != y)
						return false;
				}
			}
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

	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}