import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1759 : 암호 만들기
public class BOJ_1759 {
	static int L;
	static int C;
	static char[] alpha;
	static char[] gather = { 'a', 'e', 'i', 'o', 'u' };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String lc = bf.readLine();
		String[] lc_arr = lc.split(" ");

		L = Integer.parseInt(lc_arr[0]);
		C = Integer.parseInt(lc_arr[1]);

		String s = bf.readLine();
		String[] s_arr = s.split(" ");

		alpha = new char[C];
		for (int i = 0; i < C; i++) {
			alpha[i] = s_arr[i].charAt(0);
		}

		Arrays.sort(alpha);

		solve(0, 0, new char[L], new boolean[C]);

		System.out.println(sb);
	}

	private static void solve(int idx, int cur, char[] input, boolean[] visited) {
		if (idx == L) {
			if (check(input)) {
				print(input);
			}
			return;
		}

		for (int i = cur; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				input[idx] = alpha[i];
				solve(idx + 1, i + 1, input, visited);
				visited[i] = false;
			}
		}
	}

	private static boolean check(char[] input) {
		// 갯수 체크
		int gather_cnt = 0; // 모음
		int consonant_cnt = 0; // 자음

		for (int i = 0; i < L; i++) {
			for (int j = 0 ; j < gather.length ; j++) {
				if (input[i] == gather[j]) {
					gather_cnt++;
				}
			}
		}
		
		consonant_cnt = L - gather_cnt;
		
		if (gather_cnt < 1 || consonant_cnt < 2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void print(char[] input) {
		for (int i = 0 ; i < input.length ; i++)
			System.out.print(input[i]);
		System.out.println();
	}

}
