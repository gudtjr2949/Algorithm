import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 17472 : 다리 만들기 2
public class BOJ_17472 {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			} 
		}
		
		int idx = 1;
		
		// 섬 넘버링
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					idx++;
					numberingIsland(j, i, idx);
				}
			}
		}
		
		// 연결 가능한 섬 찾기 (가능한 다리모양 찾기)
		
		
		print(map);
	}
	
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};
	
	private static void numberingIsland(int start_x, int start_y, int idx) {
		Queue<Point> Q = new LinkedList<>();
		
		Q.add(new Point(start_x, start_y));
		
		visited[start_y][start_x] = true;
		
		map[start_y][start_x] = idx;
		
		while(!Q.isEmpty()) {
			Point p = Q.poll();
			
			int x = p.x;
			int y = p.y;
			
			visited[y][x] = true;
			
			for (int i = 0 ; i < 4 ; i++) {
				int dx = x + nx[i];
				int dy = y + ny[i];
				
				if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx] && map[dy][dx] == 1) {
					visited[dy][dx] = true;
					map[dy][dx] = idx;
					Q.add(new Point(dx, dy));
				}
			}
		}
	}
	
	private static void print(int[][] arr) {
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
}
