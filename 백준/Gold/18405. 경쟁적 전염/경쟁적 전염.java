import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K, S, X, Y;
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};
	static int[][] map;
	static Queue<Point> PQ;

	static class Point implements Comparable<Point>{
		int x, y, time, value;

		public Point(int x, int y, int time, int value) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		PQ = new PriorityQueue<>();

		map = new int[N][N];

		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine());

			for (int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					PQ.add(new Point(j, i, 0, map[i][j]));
				}
			}
		}

		st = new StringTokenizer(bf.readLine());

		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		bfs();

		System.out.println(map[X-1][Y-1]);
	}

	private static void bfs() {
		while (!PQ.isEmpty()) {
			int size = PQ.size();

			Queue<Point> tempQ = new LinkedList<>();

			for (int i = 0 ; i < size ; i++) {
				Point p = PQ.poll();

				int x = p.x;
				int y = p.y;
				int time = p.time;
				int value = p.value;

				if (time == S) {
					return;
				}

				for (int j = 0 ; j < 4 ; j++) {
					int dx = x + nx[j];
					int dy = y + ny[j];

					if (dx >= 0 && dx < N && dy >= 0 && dy < N && map[dy][dx] == 0) {
						map[dy][dx] = map[y][x];
						tempQ.add(new Point(dx, dy, time+1, value));
					}
				}
			}

			if (map[X-1][Y-1] != 0) {
				return;
			}

			while (!tempQ.isEmpty()) {
				PQ.add(tempQ.poll());
			}
		}
	}
}