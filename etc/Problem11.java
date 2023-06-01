package coding_test;

import java.util.*;
import java.io.*;

public class Problem11 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int Test = Integer.parseInt(bf.readLine());
		
		for (int i = 0 ; i < Test ; i++) {
			int N = Integer.parseInt(bf.readLine());
			
			char[][] board = new char[N][N];
			
			for (int j = 0 ; j < N ; j++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");
				for (int q = 0 ; q < N ; q++) {
					board[j][q] = s_arr[q].charAt(0);
				}
			}
			
			int cnt = 0;
			
			// 탐색
			for (int j = 0 ; j < N ; j++) {
				for (int q = 0 ; q < N ; q++) {
					if (board[j][q] == 'A') {
						for (int k = q+1 ; k < N ; k++) {
							if (board[j][k] == 'S')
								cnt++;
							else
								break;
						}
					}
					
					else if (board[j][q] == 'B') {
						// 오른쪽
						for (int k = q+1 ; k < N ; k++) {
							if (board[j][k] == 'S')
								cnt++;
							else
								break;
						}
						
						// 왼쪽
						for (int k = q-1; k >= 0 ; k--) {
							if (board[j][k] == 'S')
								cnt++;
							else
								break;
						}
					}
					
					else if (board[j][q] == 'C') {
						// 오른쪽
						for (int k = q+1 ; k < N ; k++) {
							if (board[j][k] == 'S')
								cnt++;
							else
								break;
						}
						
						// 왼쪽
						for (int k = q-1; k >= 0 ; k--) {
							if (board[j][k] == 'S')
								cnt++;
							else
								break;
						}
						
						// 아래
						for (int k = j+1 ; k < N ; k++) {
							if (board[k][q] == 'S')
								cnt++;
							else
								break;
						}
						
						// 위
						for (int k = j-1 ; k >= 0 ; k--) {
							if (board[k][q] == 'S')
								cnt++;
							else
								break;
						}
					}
				}
			}
			
			sb.append("#").append(i+1).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
