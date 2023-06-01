import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 1941 : 소문난 칠공주
public class BOJ_1941 {
	
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

	// 칠공주에 드갈 자리 정하기 (조합)
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
	
	// 칠공주 조건에 부합하는지 확인
	private static boolean check(int idx, Point[] point) {
		
		// 칠공주에 속하는 인원 배열
		boolean[][] visited = new boolean[5][5];
		
		// 이다솜파 수
		int cnt = 0;
		
		for (int i = 0 ; i < idx ; i++) {
			Point p = point[i];
			
			visited[p.y][p.x] = true;
			
			if (map[p.y][p.x] == 'S') {
				cnt++;
			}
		}
		
		// 이다솜파가 4명 이상인 경우, 다 같이 하나로 연결되어 있는지 bfs로 확인하기
		if (cnt >= 4) {
			Queue<Point> Q = new LinkedList<>();
			
			Q.add(point[0]);

			int cnt_2 = 1;
			
			// bfs용 방문배열
			boolean[][] visited_2 = new boolean[5][5];

			while (!Q.isEmpty()) {

				Point p = Q.poll();

				int x = p.x;
				int y = p.y;

				visited_2[y][x] = true;

				for (int i = 0; i < 4; i++) {
					int dx = x + nx[i];
					int dy = y + ny[i];

					if (dx >= 0 && dx < 5 && dy >= 0 && dy < 5 && visited[dy][dx] && !visited_2[dy][dx]) {
						cnt_2++;
						visited_2[dy][dx] = true;
						Q.add(new Point(dx, dy));
					}
				}

			}
			
			// cnt_2가 7보다 작다면 칠공주에 속한 인원들이 하나의 뭉치로 이루어지지 않았다는 것
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
	}
}
