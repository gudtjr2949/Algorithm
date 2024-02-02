import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 1941 : 소문난 칠공주
public class Main {
	
	static char[][] map;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		answer = 0;
		
		
		for (int i = 0 ; i < 5 ; i++) {
			String s = bf.readLine();
			
			for (int j = 0 ; j < 5 ; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		dfs(0, 0, new Point[7]);
		
		System.out.println(answer);
	}
	
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};

	private static void dfs(int idx, int start, Point[] input) {
		if (idx == input.length) {
			if (check(idx, input)) {
				answer++;
			}
			
			return;
		}
		
		for (int i = start ; i < 25 ; i++) {
			int y = i / 5;
			int x = i % 5;
			input[idx] = new Point(x, y);
			dfs(idx+1, i+1, input);
		}
	}
	
	private static boolean check(int idx, Point[] point) {
		
		boolean[][] visited = new boolean[5][5];
		
		int cnt = 0;
		
		for (int i = 0 ; i < idx ; i++) {
			Point p = point[i];
			
			visited[p.y][p.x] = true;
			
			if (map[p.y][p.x] == 'S') {
				cnt++;
			}
		}
		
		if (cnt >= 4) {
			Queue<Point> Q = new LinkedList<>();
			
			Q.add(point[0]);
			
			int cnt_2 = 1;
			
			boolean[][] visited_2 = new boolean[5][5];
			
			while(!Q.isEmpty()) {
				int size = Q.size();
				
				for (int i = 0 ; i < size ; i++) {
					Point p = Q.poll();
					
					int x = p.x;
					int y = p.y;
					
					visited_2[y][x] = true;
					
					for (int j = 0 ; j < 4 ; j++) {
						int dx = x + nx[j];
						int dy = y + ny[j];
						
						if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5 && visited[dy][dx] && !visited_2[dy][dx]) {
							cnt_2++;
							visited_2[dy][dx] = true;
							Q.add(new Point(dx, dy));
						}
					}
				}
			}
			
			if (cnt_2 < 7) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
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