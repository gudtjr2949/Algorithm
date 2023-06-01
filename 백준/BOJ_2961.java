import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 2961 : 도영이가 만든 맛있는 음식
public class BOJ_2961 {

	static int N;

	static int[] sour;

	static int[] bitter;

	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		sour = new int[N];
		bitter = new int[N];

		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");

			sour[i] = Integer.parseInt(s_arr[0]);
			bitter[i] = Integer.parseInt(s_arr[1]);
		}

		recursive(new boolean[N], 0);

		System.out.println(answer);
	}

	private static void recursive(boolean[] visited, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == N) {
			boolean sign = false;
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sign = true;
					break;
				}
			}

			if (sign) {
				int sum = Math.abs(sour_sum(visited) - bitter_sum(visited));
				
				answer = Math.min(answer, sum);
			}
			
			return;
		}

		visited[cnt] = true;
		recursive(visited, cnt + 1);
		visited[cnt] = false;
		recursive(visited, cnt + 1);
	}
	
	// 신 맛의 곱 계산
	private static int sour_sum(boolean[] visited) {
		int sum = 1;

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				sum *= sour[i];
			}
		}

		return sum;
	}
	
	// 쓴 맛의 합 계산
	private static int bitter_sum(boolean[] visited) {
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				sum += bitter[i];
			}
		}

		return sum;
	}
}
