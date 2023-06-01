import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 17135 : 캐슬 디펜스
public class BOJ_17135 {

	static int N, M, D;
	static int[][] map;
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		answer = Integer.MIN_VALUE;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combi(0, 0, new int[3]);

		System.out.println(answer);
	}

	private static void combi(int idx, int cur, int[] input) {
		if (idx == 3) {
			fire(input);
			return;
		}

		if (cur == M) {
			return;
		}

		input[idx] = cur;
		combi(idx + 1, cur + 1, input);
		combi(idx, cur + 1, input);
	}

	private static void fire(int[] archer) {
		int[][] copy_map = copyMap();
		
		int[][] origin = copyMap();
		
		int archer_idx = 0;
		int shoot = 0;

		while (check(copy_map)) {
			
			Loop:
			for (int k = 0; k < 3; k++) {
				for (int i = N - 1; i >= 0; i--) {
					for (int j = 0; j < M; j++) {
						if (origin[i][j] == 1 && (Math.abs(N - i) + Math.abs(archer[archer_idx] - j)) <= D) {
							shoot++;
							copy_map[i][j] = 0;
							archer_idx++;

							if (archer_idx == 3) {
								archer_idx = 0;
								break Loop;
							}
						}
					}
				}
			}
			
			archer_idx++;
			
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < M ; j++) {
					copy_map[i][j] = origin[i][j];
				}
			}
			
			copy_map = move_Enemy(copy_map);
			origin = move_Enemy(origin);
		}

		answer = Math.max(answer, shoot);
	}

	private static boolean check(int[][] copy_map) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy_map[i][j] == 1) {
					return true;
				}
			}
		}

		return false;
	}

	private static int[][] move_Enemy(int[][] copy_map) {

		int[][] tmp = new int[N][M];

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (copy_map[i][j] == 1) {
					if (i + 1 < N) {
						copy_map[i + 1][j] = 1;
						copy_map[i][j] = 0;
					} else {
						copy_map[i][j] = 0;
					}
				}
			}
		}

		return copy_map;
	}

	private static int[][] copyMap() {
		int[][] tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		return tmp;
	}
}
