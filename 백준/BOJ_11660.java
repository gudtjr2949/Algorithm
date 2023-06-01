package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 11660 : 구간 합 구하기 5
public class BOJ_11660 {

	static int[][] dp;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		String nm = bf.readLine();
		String[] nm_arr = nm.split(" ");
		int N = Integer.parseInt(nm_arr[0]);
		int M = Integer.parseInt(nm_arr[1]);
		
		int[][] board = new int[N][N];

		dp = new int[N][N+1];
		
		for (int i = 0 ; i < N ; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			for (int j = 0 ; j < N ; j++) {
				board[i][j] = Integer.parseInt(s_arr[j]);
			}
		}
		
		int[] start_x = new int[M];
		int[] start_y = new int[M];
		
		int[] end_x = new int[M];
		int[] end_y = new int[M];
		
		for (int i = 0 ; i < M ; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			start_y[i] = Integer.parseInt(s_arr[0]) - 1;
			start_x[i] = Integer.parseInt(s_arr[1]) ;
			
			end_y[i] = Integer.parseInt(s_arr[2]) - 1;
			end_x[i] = Integer.parseInt(s_arr[3]);
		}

		make_dp(board);

		for (int i = 0 ; i < M ; i++) {
			int sum = 0;
			for (int j = start_y[i] ; j <= end_y[i] ; j++) {
				sum += dp[j][end_x[i]] - dp[j][start_x[i] - 1];
			}
			sb.append(sum).append("\n");
		}

		System.out.println(sb);
	}

	private static void make_dp(int[][] board) {
		for (int i = 0 ; i < dp.length ; i++) {
			int sum = 0;
			for (int j = 1 ; j < dp[0].length ; j++) {
				sum += board[i][j-1];
				dp[i][j] = sum;
			}
		}
	}
}