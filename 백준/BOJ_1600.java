package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 1600 : 말이 되고픈 원숭이
public class BOJ_1600 {

	static int[] horse_dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] horse_dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	
	static int[] monkey_dx = {0, 1, 0, -1};
	static int[] monkey_dy = {-1, 0, 1, 0};
	
	static int[][] map;
	static int W, H, K, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		answer = -1;
		
		map = new int[H][W];
		
		for (int i = 0 ; i < H ; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < W ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		System.out.println(answer);
	}
	
	private static void bfs() {
		Queue<Monkey> Q = new LinkedList<>();
		Q.add(new Monkey(0, 0, K, 0));
		
		boolean[][][] visited = new boolean[31][H][W];

		while(!Q.isEmpty()) {
			int size = Q.size();
			
			for (int i = 0 ; i < size ; i++) {
				Monkey m = Q.poll();

				visited[m.k][m.y][m.x] = true;
				
				if (m.y == H-1 && m.x == W-1) {
					answer = m.depth;
					return;
				}
				
				if (m.k > 0) { // 말의 움직임 가능
					for (int j = 0 ; j < 8 ; j++) { // 말 처럼 움직이는 경우
						int ny = m.y + horse_dy[j];
						int nx = m.x + horse_dx[j];

						if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] == 0 && !visited[m.k-1][ny][nx]) {
							visited[m.k-1][ny][nx] = true;

							Q.add(new Monkey(nx, ny, m.k-1, m.depth+1));
						}
					}

					for (int j = 0 ; j < 4 ; j++) { // 말처럼 움직을 수 있지만 말 처럼 움직이지 않는 경우
						int ny = m.y + monkey_dy[j];
						int nx = m.x + monkey_dx[j];

						if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] == 0 && !visited[m.k][ny][nx]) {
							visited[m.k][ny][nx] = true;

							Q.add(new Monkey(nx, ny, m.k, m.depth+1));
						}
					}
				}
				else { // 말의 움직임 불가능
					for (int j = 0 ; j < 4 ; j++) {
						int ny = m.y + monkey_dy[j];
						int nx = m.x + monkey_dx[j];

						if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] == 0 && !visited[0][ny][nx]) {
							visited[0][ny][nx] = true;
							Q.add(new Monkey(nx, ny, m.k, m.depth+1));
						}
					}
				}
			}
		}
	}

	static class Monkey {
		int x, y, k, depth;

		public Monkey(int x, int y, int k, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.depth = depth;
		}
	}
}
