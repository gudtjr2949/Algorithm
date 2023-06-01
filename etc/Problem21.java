package coding_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem21 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		int[] jump = { 3, 2, 1 };

		for (int i = 0; i < Test; i++) {
			String nn = bf.readLine();
			String[] nn_arr = nn.split(" ");

			int N = Integer.parseInt(nn_arr[0]);
			int num = Integer.parseInt(nn_arr[1]);

			int[][] cnt = new int[N][N];

			int answer = 0;

			int[] x = new int[num];
			int[] y = new int[num];
			int[] dir = new int[num];

			for (int j = 0; j < num; j++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");

				y[j] = Integer.parseInt(s_arr[0]);
				x[j] = Integer.parseInt(s_arr[1]);
				dir[j] = Integer.parseInt(s_arr[2]);
			}

			int jump_idx = 0;
			
			Loop: 
			while (jump_idx < 3) {
				for (int j = 0; j < num; j++) {
					if (dir[j] == 1) { // 하
						if (y[j]+jump[jump_idx] == N) {
							break;
						}
						
						cnt[y[j] + jump[jump_idx]][x[j]]++;
						if (cnt[y[j] + jump[jump_idx]][x[j]] == 2) {
							answer = j + 1;
							break Loop;
						}
						y[j] += jump[jump_idx];
					} 
					
					else { // 우
						if (x[j]+jump[jump_idx] == N) {
							break;
						}
						
						cnt[y[j]][x[j] + jump[jump_idx]]++;
						
						if (cnt[y[j]][x[j] + jump[jump_idx]] == 2) {
							answer = j + 1;
							break Loop;
						}
						x[j] += jump[jump_idx];
					}
				}
				jump_idx++;
			}	

			sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

}
