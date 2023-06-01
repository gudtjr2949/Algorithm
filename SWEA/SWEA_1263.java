import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 1263 : 사람 네트워크2
public class SWEA_1263 {

	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int T = 0; T < Test; T++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
		
			for (int i = 0 ; i < N ; i++) {
				for (int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] == 0 && i != j) {
						map[i][j] = 1000;
					}
				}
			}
			
			// 플로이드 워샬
			for (int k = 0 ; k < N ; k++) {
				for (int i = 0 ; i < N ; i++) {
					for (int j = 0 ; j < N ; j++) {
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			int answer = Integer.MAX_VALUE;
			
			for (int i = 0 ; i < N ; i++) {
				int cnt = 0;
				for (int j = 0 ; j < N ; j++) {
					cnt += map[i][j];
				}
				answer = Math.min(answer, cnt);
			}
			
			sb.append("#").append(T+1).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
