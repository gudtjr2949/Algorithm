import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// 백준 15650 : N과 M (2)
public class BOJ_15650 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine();
		String[] s_arr = s.split(" ");

		int N = Integer.parseInt(s_arr[0]);
		int M = Integer.parseInt(s_arr[1]);

		recursive(N, new int[M], new boolean[N], 0, 0);
	}
	
	/*
	 * cur : output 현재 인덱스
	 * idx : 원본배열 인덱스
	 */

	private static void recursive(int N, int[] output, boolean[] visited, int cur, int idx) {
		if (cur == output.length) {
			String answer = "";

			for (int i = 0; i < output.length; i++) {
				answer += output[i] + " ";
			}

			System.out.println(answer);

			return;
		}

		for (int i = idx; i < N; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				output[cur] = i + 1;
				recursive(N, output, visited, cur + 1, i + 1);
				visited[i] = false;
			}
		}
	}

}
