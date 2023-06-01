import java.io.*;
import java.util.*;

// 백준 17276 : 배열 돌리기
public class BOJ_17276 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int i = 0; i < Test; i++) {
			String nd = bf.readLine();
			String[] nd_arr = nd.split(" ");

			int N = Integer.parseInt(nd_arr[0]);

			int d = Integer.parseInt(nd_arr[1]);

			int[][] board = new int[N][N];

			for (int j = 0; j < N; j++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");

				for (int q = 0; q < N; q++) {
					board[j][q] = Integer.parseInt(s_arr[q]);
				}
			}

			Deque<Integer[]> deque = new LinkedList<>();

			Integer[] line = new Integer[N / 2];
			

			// 대각선 찾기
			for (int j = 0; j < 8; j++) {
				for (int q = 0; q < N / 2; q++) {
					if (j == 0) {
						line[q] = board[q][q];
					} else if (j == 1) {
						line[q] = board[q][N / 2];
					} else if (j == 2) {
						line[q] = board[q][(N - 1) - q];
					} else if (j == 3) {
						line[q] = board[N / 2][(N - 1) - q];
					} else if (j == 4) {
						line[q] = board[(N - 1) - q][(N - 1) - q];
					} else if (j == 5) {
						line[q] = board[(N - 1) - q][N / 2];
					} else if (j == 6) {
						line[q] = board[(N - 1) - q][q];
					} else if (j == 7) {
						line[q] = board[N / 2][q];
					}
				}
				
				deque.add(line);
				
				line = new Integer[N/2];
			}


			if (d > 0) {
				for (int j = 0; j < d / 45; j++) {
					deque.addFirst(deque.removeLast());
				}
			}
			else {
				for (int j = 0; j < (d * -1) / 45; j++) {
					deque.addLast(deque.removeFirst());
				}
			}
			
			int[][] tmp_board = new int[N][N];
			
			for (int j = 0; j < 8; j++) {
				Integer[] tmp = deque.poll();
				
				for (int q = 0; q < N / 2; q++) {
					
					if (j == 0) {
						tmp_board[q][q] = tmp[q];
					} else if (j == 1) {
						tmp_board[q][N / 2] = tmp[q];
					} else if (j == 2) {
						tmp_board[q][(N - 1) - q] = tmp[q];
					} else if (j == 3) {
						tmp_board[N / 2][(N - 1) - q] = tmp[q];
					} else if (j == 4) {
						tmp_board[(N - 1) - q][(N - 1) - q] = tmp[q];
					} else if (j == 5) {
						tmp_board[(N - 1) - q][N / 2] = tmp[q];
					} else if (j == 6) {
						tmp_board[(N - 1) - q][q] = tmp[q];
					} else if (j == 7) {
						tmp_board[N / 2][q] = tmp[q];
					}
				}
			}
			
			for (int j = 0; j < N; j++) {
				for (int q = 0; q < N; q++) {
					if (tmp_board[j][q] == 0) {
						tmp_board[j][q] = board[j][q];
					}
				}
			}
			
			for (int j = 0 ; j < N ; j++) {
				for (int q = 0 ; q < N ; q++) {
					sb.append(tmp_board[j][q]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
