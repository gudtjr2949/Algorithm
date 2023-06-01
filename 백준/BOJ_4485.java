import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 4485 : 녹색 옷 입은 애가 젤다지?
public class BOJ_4485 {
	
	static int N;
	static int[][] map;
	static int[][] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx = 1;
		
		while(true) {
			
			N = Integer.parseInt(bf.readLine());
			
			if (N == 0) {
				break;
			}
			
			map = new int[N][N];
			memo= new int[N][N];
			
			for (int i = 0 ; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					memo[i][j] = Integer.MAX_VALUE;
				}
			}
			
			memo[0][0] = map[0][0];
			
			bfs();
			
			sb.append("Problem " + idx + ": " + memo[N-1][N-1] + "\n");
			
			idx++;
		}
		
		System.out.println(sb);
	}
	
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};

	private static void bfs() {
		Queue<Point> Q = new LinkedList<>();
		
		Q.add(new Point(0, 0));
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i = 0 ; i < size ; i++) {
				Point p = Q.poll();
				
				int x = p.x;
				int y = p.y;
							
				for (int j = 0 ; j < 4 ; j++) {
					int dx = x + nx[j];
					int dy = y + ny[j];
					
					if (dx < N && dx >= 0 && dy < N && dy >= 0) {
						if (memo[dy][dx] > memo[y][x] + map[dy][dx]) {
							memo[dy][dx] = memo[y][x] + map[dy][dx];
							Q.add(new Point(dx, dy));
						}
					}
				} 
			}
		}
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
