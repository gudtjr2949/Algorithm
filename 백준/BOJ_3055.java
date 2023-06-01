import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 3055 : 탈출
public class BOJ_3055 {
	
	static int R, C, answer;
	static char[][] map;
	static Point D, S;
	static ArrayList<Point> water;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		answer = 0;
		
		map = new char[R][C];
		
		water = new ArrayList<>();
		
		for (int i = 0 ; i < R ; i++) {
			String s = bf.readLine();
			for (int j = 0 ; j < C ; j++) {
				map[i][j] = s.charAt(j);
				
				if (map[i][j] == 'D') {
					D = new Point(j, i, 'D');
				} else if (map[i][j] == 'S') {
					S = new Point(j, i, 'S');
				} else if (map[i][j] == '*') {
					water.add(new Point(j, i, '*'));
				}
			}
		}
		
		bfs();
		
		if (answer != 0) 
			System.out.println(answer);
		else
			System.out.println("KAKTUS");
	}
	
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};
	
	private static void bfs() {
		Queue<Point> Q = new LinkedList<>();
		
		
		for (int i = 0 ; i < water.size() ; i++) {
			Q.add(water.get(i));
		}
		
		Q.add(new Point(S.x, S.y, S.value, 0));
		
		boolean[][] visited = new boolean[R][C];
		
		
		while(!Q.isEmpty()) {
	
			int size = Q.size();
			
			for (int i = 0 ; i < size ; i++) {
				Point p = Q.poll();
				
				int x = p.x;
				int y = p.y;
				char value = p.value;
				int depth = p.depth;
				
				visited[y][x] = true;
				
				if (map[y][x] == 'D') {
					answer = depth;
					return;
				}
				
				if (value == '*') {
					for (int j = 0; j < 4; j++) {
						int dx = x + nx[j];
						int dy = y + ny[j];

						if (dx >= 0 && dx < C && dy >= 0 && dy < R && !visited[dy][dx] && map[dy][dx] != 'X' && map[dy][dx] != 'D') {
							visited[dy][dx] = true;
							map[dy][dx] = '*';
							Q.add(new Point(dx, dy, map[dy][dx], depth+1));
						}
					}
				}
				
				else if (value == 'S') {
					for (int j = 0; j < 4; j++) {
						int dx = x + nx[j];
						int dy = y + ny[j];
						
						if (dx >= 0 && dx < C && dy >= 0 && dy < R && !visited[dy][dx] && map[dy][dx] != 'X' && map[dy][dx] != '*') {
							visited[dy][dx] = true;
							Q.add(new Point(dx, dy, 'S', depth+1));
						}
					}
				}
				
			}
		}
	}

	static class Point {
		int x, y , depth;
		char value;
		
		public Point(int x, int y, char value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}

		public Point(int x, int y, char value, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
			this.depth = depth;
		}		
	}
}
