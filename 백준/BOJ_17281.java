import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 17281 : 야구
public class BOJ_17281 {
	
	static int N, answer;
	static int[][] point;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		
		point = new int[N][9];
		
		answer = 0;
		
		for (int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			for (int j = 0 ; j < 9 ; j++) {
				point[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, new boolean[8], new int[8]);
		
		System.out.println(answer);
	}

	private static void dfs(int idx, boolean[] visited, int[] input) {
		if (idx == 8) {
			solve(input);
			return;
		}
		
		for (int i = 0 ; i < 8 ; i++) {
			if (!visited[i]) {
				visited[i] = true;
				input[idx] = i+1;
				dfs(idx+1, visited, input);
				visited[i] = false;
			}
		}
	}

	private static void solve(int[] input) {
		
		int[] order = new int[9];
		
		int order_idx = 0;
		
		for (int i = 0 ; i < 9 ; i++) {
			if (i != 3) {
				order[i] = input[order_idx];
				order_idx++;
			}
		}
		
		boolean[] base = {false, false, false, false};
		
		int idx = 0;
		
		int score = 0;
		
		for (int i = 0 ; i < N ; i++) {
			
			int out = 0;
			
			while(out < 3) {
				// 9번까지 갔으면?
				if (idx == 9) {
					idx = 0;
				}
				
				if (point[i][order[idx]] == 0) {
					out++;
				} else if (point[i][order[idx]] > 0 && point[i][order[idx]] < 4) {
					for (int j = 3 ; j >= 1 ; j--) {
						// 해당 베이스에 주자가 있으면
						if (base[j]) {
							// 주자 이동시키기
							base[j] = false;
							
							if (j + point[i][order[idx]] >= 4) { // 주자가 홈에 들어옴
								score++;
							} else {
								base[j + point[i][order[idx]]] = true;
							}	
						}
					}
					
					base[point[i][order[idx]]] = true;
				} else if (point[i][order[idx]] == 4) {
					score++;
					
					for (int j = 1 ; j <= 3 ; j++) {
						if (base[j]) {
							score++;
						}
					}
					
					base = new boolean[4];
				}
				
				// 다음 타자
				idx++;
			}
			
			base = new boolean[4];
		}
		
		answer = Math.max(answer, score);
	}
}
