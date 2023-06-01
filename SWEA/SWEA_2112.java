package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 2112 : 보호 필름
public class SWEA_2112 {
	
	static int D, W, K, answer;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int Test = Integer.parseInt(bf.readLine());
		
		for (int T = 1 ; T <= Test ; T++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			answer = Integer.MAX_VALUE;
			
			map = new int[D][W];
			
			for (int i = 0 ; i < D ; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0 ; j < W ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			test(new int[0], new int[0]);
			
			if (answer == Integer.MAX_VALUE) {
				for (int i = 1; i < D; i++) {
					dfs(0, 0, new int[i]);
					
					if (answer != Integer.MAX_VALUE) {
						break;
					}
				}
			}
			
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	// 바꿀 막 정하기
	private static void dfs(int idx, int cur, int[] input) {
		if (idx == input.length) {
			dfs_2(0, input, new int[idx]);
			return;
		}
		
		if (answer != Integer.MAX_VALUE) {
			return;
		}
		
		for (int i = cur ; i < D ; i++) {
			input[idx] = i;
			dfs(idx + 1, i + 1, input);
		}
	}
	
	// 바꿀 막의 특성 정하기
	private static void dfs_2(int idx, int[] order, int[] input) {
		if (idx == input.length) {
			test(order, input);
			return;
		}
		
		if (answer != Integer.MAX_VALUE) {
			return;
		}
		
		for (int i = 0 ; i < 2 ; i++) {
			input[idx] = i;
			dfs_2(idx + 1, order, input);
		}
	}

	private static void test(int[] order, int[] chara) {
		
		int[][] copy_map = copy(order, chara);
		
		boolean sign = false;
		
		Loop:
		for (int i = 0 ; i < W ; i++) {
			int A_cnt = 1;
			int B_cnt = 1;
			
			for (int j = 0; j < D - 1; j++) {
				if (copy_map[j][i] == 0 && copy_map[j + 1][i] == 0) {
					A_cnt++;
				} else if (copy_map[j][i] == 0 && copy_map[j + 1][i] == 1) {
					A_cnt = 1;
					B_cnt = 1;
				} else if (copy_map[j][i] == 1 && copy_map[j + 1][i] == 1) {
					B_cnt++;
				} else if (copy_map[j][i] == 1 && copy_map[j + 1][i] == 0) {
					A_cnt = 1;
					B_cnt = 1;
				}

				if (A_cnt == K || B_cnt == K) {
					sign = true;
					break;
				} else {
					sign = false;
					break Loop;
				}
			}
		}
		
		if (sign) 
			answer = Math.min(answer, order.length);
	}
	
	private static int[][] copy(int[] order, int[] chara) {
		int[][] copy_map = new int[D][W];
		
		for (int i = 0 ; i < D ; i++) {
			for (int j = 0 ; j < W ; j++) {
				copy_map[i][j] = map[i][j];
			}
		}
		
		for (int i = 0 ; i < chara.length ; i++) {
			for (int j = 0 ; j < W ; j++) {
				if (chara[i] == 0) {
					copy_map[order[i]][j] = 0;
				} else {
					copy_map[order[i]][j] = 1;
				}
			}
		}
		
		return copy_map;
	}
}
