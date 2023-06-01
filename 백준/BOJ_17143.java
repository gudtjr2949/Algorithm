package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 백준 17143 : 낚시왕
public class BOJ_17143 {
	
	static Shark[][] map;
	static int R, C, M;
	static int[] nx = {0, 0, 0, 1, -1};
	static int[] ny = {0, -1, 1, 0, 0};
	static HashMap<Integer, Integer> dir;
	static Shark[] arr;
	static boolean[] live;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dir = new HashMap<>();
		arr = new Shark[M];
		live = new boolean[M];
		Arrays.fill(live, true);

		answer = 0;

		// 방향 변경용 해시맵
		dir.put(1, 2);
		dir.put(2, 1);
		dir.put(3, 4);
		dir.put(4, 3);
		
		map = new Shark[R][C];

		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			arr[i] = new Shark(i, y, x, s, d, z);
		}

		for (int i = 0 ; i < C ; i++) {
			makeMap();
			findShark(i);
			for (int j = 0; j < M; j++) {
				if (live[j]) {
					move(j, arr[j]);
				}
			}
			initialMap();
		}

		System.out.println(answer);
	}

	private static void initialMap() {
		for (int i = 0 ; i < R ; i++) {
			for (int j = 0 ; j < C ; j++) {
				map[i][j] = null;
			}
		}
	}

	private static void makeMap() {
		for (int i = 0 ; i < M ; i++) {
			if (live[i]) { // 해당 상어가 살아있냐

				if (map[arr[i].y][arr[i].x] != null) { // 해당 칸에 다른 상어가 있으면?
					if (map[arr[i].y][arr[i].x].z > arr[i].z) { // 크기 비교 후, 더 큰 상어 넣기. 작은 상어는 죽이기
						live[i] = false;
					} else {
						live[map[arr[i].y][arr[i].x].idx] = false;
						map[arr[i].y][arr[i].x] = arr[i];
					}
				} else { // 다른 상어 없으면 그냥 상어 넣기
					map[arr[i].y][arr[i].x] = arr[i];
				}

			}
		}
	}

	private static void findShark(int idx) {
		for (int i = 0 ; i < R ; i++) {
			if (map[i][idx] != null) {
				answer += map[i][idx].z;
				live[map[i][idx].idx] = false;
				break;
			}
		}
	}
	
	private static void move(int idx, Shark shark) {
		
		int dx = shark.x;
		int dy = shark.y;
		
		for (int i = 0 ; i < shark.s ; i++) {
			if (!(dx+nx[shark.d] >= C || dx+nx[shark.d] < 0 || dy+ny[shark.d] >= R || dy+ny[shark.d] < 0)) {
				dx += nx[shark.d];
				dy += ny[shark.d];
			}
			else {
				shark.d = dir.get(shark.d);
				dx += nx[shark.d];
				dy += ny[shark.d];
			}
		}

		shark.x = dx;
		shark.y = dy;

		arr[idx] =  shark;
	}

	static class Shark {
		int idx, x, y, s, d, z;

		public Shark(int idx, int x, int y, int s, int d, int z) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
