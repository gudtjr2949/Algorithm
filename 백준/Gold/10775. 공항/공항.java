import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int G, P;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(bf.readLine());
		P = Integer.parseInt(bf.readLine());

		parents = new int[G+1];

		for (int i = 0 ; i < G+1 ; i++) {
			parents[i] = i;
		}

		int answer = 0;

		for (int i = 0 ; i < P ; i++) {
			int num = Integer.parseInt(bf.readLine());

			int findNum = findParents(num);

			if (findNum != 0) {
				union(findNum, findNum-1);
				answer++;
			} else {
				break;
			}
		}

		System.out.println(answer);
	}

	private static void union(int a, int b) {
		a = findParents(a);
		b = findParents(b);
		parents[a] = b;
	}

	private static int findParents(int num) {
		if (parents[num] == num) {
			return num;
		} else {
			return parents[num] = findParents(parents[num]);
		}
	}
}