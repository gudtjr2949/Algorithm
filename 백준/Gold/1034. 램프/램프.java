import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, answer;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		answer = 0;

		for (int i = 0 ; i < N ; i++) {
			String s = bf.readLine();
			for (int j = 0 ; j < M ; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		K = Integer.parseInt(bf.readLine());

		for (int i = 0 ; i < N ; i++) {
			int cnt = 0;

			for (int j = 0 ; j < M ; j++) {
				if (map[i][j] == 0) cnt++;
			}

			if (K >= cnt) {
				// K와 한 행의 0의 갯수가 모두 홀수이거나 K와 한 행의 0의 갯수가 모두 짝수인 경우
				if ((K % 2 != 0 && cnt % 2 != 0) || (K % 2 == 0 && cnt % 2 == 0)) {
					 // 같은 모양의 행의 갯수 찾기
					findSameLamp(i);
				} 
			}
		}

		System.out.println(answer);
	}

	private static void findSameLamp(int idx) {
		int cnt = 1;

		String s1 = "";

		for (int i = 0 ; i < M ; i++) {
			s1 += map[idx][i];
		}

		for (int i = 0 ; i < N ; i++) {
			if (idx != i) {
				String s2 = "";
				for (int j = 0; j < M; j++) {
					s2 += map[i][j];
				}

				if (s1.equals(s2))
					cnt++;
			}
		}

		answer = Math.max(answer, cnt);
	}
}