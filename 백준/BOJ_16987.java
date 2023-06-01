import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 16987 : 계란으로 계란치기
public class BOJ_16987 {
	static int answer = 0;

	static Egg[] eggs;

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());

		eggs = new Egg[N];

		for (int i = 0; i < N; i++) {
			String dw = bf.readLine();
			String[] dw_arr = dw.split(" ");

			eggs[i] = new Egg(Integer.parseInt(dw_arr[0]), Integer.parseInt(dw_arr[1]));
		}

		recursive(new int[N], 0);

		System.out.println(answer);
	}

	private static void recursive(int[] order, int idx) {
		if (idx == order.length) {
			solve(new Egg[N], order);

			return;
		}

		for (int i = 0; i < order.length; i++) {
			if (idx != i) {
				order[idx] = i;
				recursive(order, idx + 1);
			}
		}
	}

	private static void solve(Egg[] sel_egg, int[] order) {
		int[] sel_egg_s = new int[N];
		int[] sel_egg_w = new int[N];
		
		// 계란 지정하기
		for (int i = 0; i < N; i++) {
			sel_egg_s[i] = eggs[i].s;
			sel_egg_w[i] = eggs[i].w;
		}

		for (int i = 0; i < N; i++) {
			// 계란 때리기
			if (sel_egg_s[i] > 0 && sel_egg_s[order[i]] > 0) {
				int attack_1 = sel_egg_s[i] - sel_egg_w[order[i]];
				int attack_2 = sel_egg_s[order[i]] - sel_egg_w[i];

				sel_egg_s[i] = attack_1;
				sel_egg_s[order[i]] = attack_2;
			}
		}

		int cnt = 0;

		for (int i = 0; i < order.length; i++) {
			if (sel_egg_s[i] <= 0) // 깨졌다는 의미
				cnt++;
		}

		answer = Math.max(answer, cnt);
	}
}

class Egg {
	int s;
	int w;

	Egg(int s, int w) {
		this.s = s;
		this.w = w;
	}
}
