import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, startX, startY, answer;
	static char[][] map;
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};

	static class Penguin {
		int x, y, time;
		boolean visited;

		public Penguin(int x, int y, boolean visited, int time) {
			this.x = x;
			this.y = y;
			this.visited = visited;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int i = 0 ; i < N ; i++) {
			String s = bf.readLine();

			for (int j = 0 ; j < M ; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'S') {
					startX = j;
					startY = i;
				}
			}
		}

		bfs();

		if (answer == 0) answer = -1;

		System.out.println(answer);
	}

	private static void bfs() {
		Queue<Penguin> Q = new LinkedList<>();

		boolean[][] beforeVisited = new boolean[N][M];
		boolean[][] afterVisited = new boolean[N][M];

		Q.add(new Penguin(startX, startY, false, 0));

		while (!Q.isEmpty()) {
			int size = Q.size();

			for (int i = 0 ; i < size ; i++) {
				Penguin p = Q.poll();

				int x = p.x;
				int y = p.y;
				boolean visited = p.visited;
				int time = p.time;

				if (map[y][x] == 'H' && visited) {
					answer = time;
					return;
				}

				for (int j = 0 ; j < 4 ; j++) {
					int dx = x + nx[j];
					int dy = y + ny[j];

					if (dx >= 0 && dx < M && dy >= 0 && dy < N && map[dy][dx] != 'D') {
						if (visited && !afterVisited[dy][dx]) {
							afterVisited[dy][dx] = true;
							Q.add(new Penguin(dx, dy, visited, time+1));
						}
						else if (!visited && !beforeVisited[dy][dx]) {
							if (map[dy][dx] == 'F') {
								beforeVisited[dy][dx] = true;
								Q.add(new Penguin(dx, dy, true, time + 1));
							} else {
								beforeVisited[dy][dx] = true;
								Q.add(new Penguin(dx, dy, visited, time+1));
							}
						}

					}
				}
			}
		}
	}
}