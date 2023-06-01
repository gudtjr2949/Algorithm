import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 백준 2146 : 다리 만들기
public class BOJ_2146 {

	static int N;
	static int[][] map;
	static boolean[][] visited;

	static int[] nx = { 0, 1, 0, -1 };
	static int[] ny = { -1, 0, 1, 0 };

	static ArrayList<Point>[] list;

	static int num;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		list = new ArrayList[(N * N) / 2];

		for (int i = 0; i < (N * N) / 2; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s_arr[j]);
			}
		}

		num = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					dfs(j, i);
					num++;
				}
			}
		}

		find_min();

		System.out.println(answer);
	}

	private static void dfs(int x, int y) {
		Queue<Point> Q = new LinkedList<>();

		Q.add(new Point(x, y));

		while (!Q.isEmpty()) {
			int size = Q.size();

			for (int i = 0; i < size; i++) {
				Point p = Q.poll();

				visited[p.y][p.x] = true;

				int origin_x = -1;
				int origin_y = -1;

				for (int j = 0; j < 4; j++) {
					int dx = p.x + nx[j];
					int dy = p.y + ny[j];

					if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx]) {
						if (map[dy][dx] == 1) {
							visited[dy][dx] = true;
							Q.add(new Point(dx, dy));
						} else {
							if (origin_x != p.x && origin_y != p.y) {
								origin_x = p.x;
								origin_y = p.y;
								list[num].add(new Point(origin_x, origin_y));
							}
						}
					}
				}
			}
		}
	}

	private static void find_min() {
		for (int i = 0 ; i < num-1 ; i++ ) {
			for (int j = i+1 ; j < num ; j++) {
				for (int q = 0 ; q < list[i].size() ; q++) {
					for (int k = 0 ; k < list[j].size() ; k++) {
						int dis = (Math.abs(list[j].get(k).y - list[i].get(q).y)
								+ Math.abs(list[j].get(k).x - list[i].get(q).x))-1;
						answer = Math.min(answer, dis);
					}
				}
			}
		}

	}
	
	static class Point {
		int x;
		int y;
		int depth;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
