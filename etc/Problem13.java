package coding_test;

import java.util.*;
import java.io.*;

public class Problem13 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		int[] nx = { -1, 0, 1, 1, 1, 0, -1, -1 };
		int[] ny = { -1, -1, -1, 0, 1, 1, 1, 0 };

		for (int i = 0; i < Test; i++) {
			int N = Integer.parseInt(bf.readLine());

			char[][] board = new char[N][N];
			int[][] height = new int[N][N];

			for (int j = 0; j < N; j++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");

				for (int q = 0; q < N; q++) {
					board[j][q] = s_arr[q].charAt(0);
				}
			}

			for (int j = 0; j < N; j++) {
				int start_y = j;
				for (int q = 0; q < N; q++) {
					int start_x = q;

					boolean sign = true;

					int cnt = 0;

					if (board[start_y][start_x] == 'B') {
						for (int k = 0; k < 8; k++) {
							if (start_y + ny[k] < N && start_x + nx[k] < N && start_y + ny[k] >= 0
									&& start_x + nx[k] >= 0) {
								if (board[start_y + ny[k]][start_x + nx[k]] == 'G') {
									sign = false;
									break;
								}
							}
						}

						if (sign) { // 주변에 공원이 없는 경우
							// 가로 탐색
							for (int k = 0; k < N; k++) {
								if (board[start_y][k] == 'B' && board[k][start_x] == 'B')
									cnt += 2;
								else if (board[start_y][k] == 'B' || board[k][start_x] == 'B')
									cnt++;
							}

							height[start_y][start_x] = cnt-1; // -1은 자기 위치를 두 번 더했으니까 하나 빼기
						}
						else {
							height[start_y][start_x] = 2;
						}	
					}
				}
			}
			
			int max = 0;

			for (int j = 0; j < N; j++) {
				for (int q = 0; q < N; q++) {
					max = Math.max(max, height[j][q]);
				}
			}

			sb.append("#").append(i + 1).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

}
