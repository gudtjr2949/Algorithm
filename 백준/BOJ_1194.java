import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1194 : 달이 차오른다, 가자.
public class BOJ_1194 {
	
	static int N, M, answer;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		int x = 0, y = 0;
		
		for (int i = 0 ; i < N ; i++) {
			String s = bf.readLine();
			for (int j = 0 ; j < M ; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '0') {
					x = j;
					y = i;
				}
			}
		}
		
		map[y][x] = '.';
		
		answer = -1;
		
		bfs(x, y);
		
		System.out.println(answer);
		
	}
	
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};
	
	private static void bfs(int x, int y) {
		Queue<Point> Q = new LinkedList<>();
		
		Q.add(new Point(x, y, 0, 0));
		
		boolean[][][] visited = new boolean[N][M][1 << 6];
		
		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i = 0 ; i < size ; i++) {
				Point p = Q.poll();
				
				visited[p.y][p.x][p.bit] = true;
				
				for (int j = 0 ; j < 4 ; j++) {
					int dx = p.x + nx[j];
					int dy = p.y + ny[j];
					
					if (dx < M && dx >= 0 && dy < N && dy >= 0 && !visited[dy][dx][p.bit] && map[dy][dx] != '#') {
						if (map[dy][dx] == '.') { // 빈 칸
							visited[dy][dx][p.bit] = true;
							Q.add(new Point(dx, dy, p.depth+1, p.bit));
						} else if (map[dy][dx] == '1') { // 출구
							answer = p.depth+1;
							return;
						} else if (Character.isLowerCase(map[dy][dx])) { // 열쇠
							// 비트 마스킹
							int A = p.bit;
							A |= (1 << map[dy][dx] - 'a');
							
							visited[dy][dx][A] = true;
							Q.add(new Point(dx, dy, p.depth+1, A));
						} else if (Character.isUpperCase(map[dy][dx])){ // 문
							if((p.bit & (1 << map[dy][dx] - 'a')) > 0) {
								visited[dy][dx][p.bit] = true;
								Q.add(new Point(dx, dy, p.depth+1, p.bit));
							}
						}
					}
				}
			}
		}
	}

	static class Point {
		int x, y, depth, bit;
		
		public Point(int x, int y, int depth, int bit) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.bit = bit;
		}
	}
}