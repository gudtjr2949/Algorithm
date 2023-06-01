import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// SWEA 4012 : 요리사
public class SWEA_4012 {

	static int N;
	static int[][] board;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int i = 0; i < Test; i++) {
			N = Integer.parseInt(bf.readLine());
			board = new int[N][N];
			answer = Integer.MAX_VALUE;

			for (int j = 0; j < N; j++) {

				String s = bf.readLine();
				String[] s_arr = s.split(" ");

				for (int q = 0; q < N; q++) {
					board[j][q] = Integer.parseInt(s_arr[q]);
				}
			}

			Combination(new int[N / 2], 0, 0);
			
			sb.append("#").append(i+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void Combination(int[] input, int idx, int cur) {
		if (idx == input.length) {
			solve(input);
			return;
		}

		for (int i = cur; i < N; i++) {
			input[idx] = i;
			Combination(input, idx + 1, i + 1);
		}
	}

	/*
	 * true : A 요리 
	 * false : B 요리
	 */
	private static void solve(int[] A) {
		int[] B = make_B(A);

		int A_sum = 0;
		int B_sum = 0;

		// A 계산
		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				A_sum += board[A[i]][A[j]];
				A_sum += board[A[j]][A[i]];
			}
		}

		// B 계산
		for (int i = 0; i < B.length - 1; i++) {
			for (int j = i + 1; j < B.length; j++) {
				B_sum += board[B[i]][B[j]];
				B_sum += board[B[j]][B[i]];
			}
		}
		
		answer = Math.min(answer, Math.abs(A_sum - B_sum));
	}

	private static int[] make_B(int[] A) {
		int[] B = new int[N / 2];

		boolean[] check = new boolean[N];

		for (int i = 0; i < A.length; i++) {
			check[A[i]] = true;
		}

		int idx = 0;

		for (int i = 0; i < N; i++) {
			if (!check[i]) {
				B[idx] = i;
				idx++;
			}
		}

		return B;
	}

}
