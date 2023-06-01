import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 4963 : 섬의 개수
public class BOJ_4963 {

	static int[] nx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] ny = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int[][] board;
	static boolean[][] visited;
	
	static int w;
	static int h;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String wh = bf.readLine();
			String[] wh_arr = wh.split(" ");
			w = Integer.parseInt(wh_arr[0]);
			h = Integer.parseInt(wh_arr[1]);

			if (w == 0 && h == 0)
				break;

			board = new int[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");
				for (int j = 0; j < s_arr.length; j++) {
					board[i][j] = Integer.parseInt(s_arr[j]);
				}
			}
			
			int cnt = 0;

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (board[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static void bfs(int y, int x) {
		Queue<Point> Q = new LinkedList<>();
		
		Q.add(new Point(x, y));
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i = 0 ; i < size ; i++) {
				Point p = Q.poll();
				visited[p.y][p.x] = true;
				
				for (int j = 0 ; j < 8 ; j++) {
					int dx = p.x + nx[j];
					int dy = p.y + ny[j];
					
					if (!(dx >= w || dx < 0 || dy >= h || dy < 0) && !visited[dy][dx] && board[dy][dx] == 1) {
						visited[dy][dx] = true;
						Q.add(new Point(dx, dy));
					}
				}
			}
		}
	}
}
