package coding_test;

import java.util.*;
import java.io.*;

public class Problem22 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		int[] jump = { 3, 2, 1 };
		int[] move = { -1, 1, -1, 1 };

		for (int i = 0; i < Test; i++) {
			String nn = bf.readLine();
			String[] nn_arr = nn.split(" ");

			int N = Integer.parseInt(nn_arr[0]);
			int num = Integer.parseInt(nn_arr[1]);

			boolean[][] board = new boolean[N][N];

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

			for (int j = 0; j < N; j++) {
				for (int q = 0; q < N; q++) {
					board[j][q] = true;
				}
			}
			
			int jump_idx = 0;

			while (jump_idx < 3) {
				for (int j = 0; j < num; j++) {
					if (dir[j] < 0) { // 이미 없어진 소금쟁이
						continue;
					} 
					
					else { // 살아있는 소금쟁이
						int dy = y[j] + (jump[jump_idx] * move[dir[j] - 1]);
						int dx = x[j] + (jump[jump_idx] * move[dir[j] - 1]);
						if ((dir[j] == 1 && dy >= 0) || (dir[j] == 2 && dy < 0) || (dir[j] == 3 && dx >= 0) || (dir[j] == 4 && dx < N)) { // 해당 소금쟁이의 도착지가 N*N 범위를 벗어나지 않으면 
							board[y[j]][x[j]] = true; // 현 위치를 벗어남

							if (dir[j] == 1 && board[dy][x[j]]) { // 상
								board[dy][x[j]] = false;

								y[j] = dy;
							}

							else if (dir[j] == 2 && board[dy][x[j]]) { // 하
								board[dy][x[j]] = false;

								y[j] = dy;
							}

							else if (dir[j] == 3 && board[y[j]][dx]) { // 좌
								board[y[j]][dx] = false;

								x[j] = dx;
							}

							else if (dir[j] == 4 && board[y[j]][dx]) { // 우
								board[y[j]][dx] = false;

								x[j] = dx;
							}

							else { // 그 자리에 이미 소금쟁이가 있는 경우
								dir[j] = -1; // 소금쟁이 없애기
							}
						}
						else { // 소금쟁이의 도착지가 N*N 범위를 벗어나면
							board[y[j]][x[j]] = true;
						}
					}
				}
				
				jump_idx++;
			}

			for (int j = 0; j < N; j++) {
				for (int q = 0; q < N; q++) {
					if (!board[j][q])
						answer++;
				}
			}
			
			sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

}
