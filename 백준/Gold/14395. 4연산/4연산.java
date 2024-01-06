import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static long s, t;
	static String answer;

	static class Number {
		private String s;
		private long number;

		public Number(String s, long number) {
			this.s = s;
			this.number = number;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		s = Long.parseLong(st.nextToken());
		t = Long.parseLong(st.nextToken());

		if (s == t) {
			answer = "0";
		}
		else {
			bfs();
		}

		System.out.println(answer);
	}

	private static void bfs() {
		Queue<Number> Q = new LinkedList<>();
		Set<Long> set = new HashSet<>();

		Q.add(new Number("", s));

		char[] operation = {'*', '+', '-', '/'};


		while (!Q.isEmpty()) {
			int size = Q.size();

			for (int i = 0 ; i < size ; i++) {
				Number n = Q.poll();

				String s = n.s;
				long number = n.number;

				if (number == t) {
					answer = s;
					return;
				}

				for (int j = 0 ; j < 4 ; j++) {
					long tmp = 0;

					switch (j) {
						case 0:
							tmp = number * number;
							break;
						case 1:
							tmp = number + number;
							break;
						case 2:
							tmp = number - number;
							break;
						case 3:
							if (number != 0) tmp = number / number;
							break;
					}

					if (!set.contains(tmp)) {
						set.add(tmp);
						Q.add(new Number(s+operation[j], tmp));
					}
				}
			}
		}

		answer = "-1";
	}
}