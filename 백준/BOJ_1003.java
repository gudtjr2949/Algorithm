import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1003 : 피보나치 함수
public class BOJ_1003 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(bf.readLine());

		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(bf.readLine());

			int[] dp_0 = new int[N + 1];
			int[] dp_1 = new int[N + 1];

			dp_0[0] = 1;
			dp_1[0] = 0;

			if (N >= 1) {
				dp_0[1] = 0;
				dp_1[1] = 1;

				// i가 호출될 때, 출력되는 0과 1의 수
				for (int i = 2; i <= N; i++) {
					dp_0[i] = dp_0[i - 1] + dp_0[i - 2];
					dp_1[i] = dp_1[i - 1] + dp_1[i - 2];
				}
			}

			sb.append(dp_0[N] + " " + dp_1[N] + "\n");
		}

		System.out.println(sb);
	}
}

/*
 * f(10) = f(9) + f(8) f(9) = f(8) + f(7) f(8) = f(7) + f(6) f(7) = f(6) + f(5)
 * f(6) = f(5) + f(4) f(5) = f(4) + f(3) f(4) = f(3) + f(2) f(3) = f(2) + f(1)
 * f(2) = f(1) + f(0) f(1) = print(1) f(0) = print(0) f(2) = print(1) + print(0)
 * f(3) = print(1) + print(0) + print(1) f(4) = print(1) + print(0) + print(1) +
 * print(1) + print(0)
 * 
 */
