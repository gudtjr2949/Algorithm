import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static String[] numberStr = {"####.##.##.####", "..#..#..#..#..#", "###..#####..###"
		, "###..####..####", "#.##.####..#..#", "####..###..####", "####..####.####"
		, "###..#..#..#..#", "####.#####.####", "####.####..####"};

	static int N;
	static char[][] map;
	static ArrayList<ArrayList<Integer>> input;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		input = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			input.add(new ArrayList<>());
		}

		map = new char[5][4 * N - 1];

		for (int i = 0; i < 5; i++) {
			String s = bf.readLine();

			for (int j = 0; j < 4 * N - 1; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		numberCheck();

		if (checkList()) {
			findAvg();
		} else {
			System.out.println(-1);
		}
	}

	private static boolean checkList() {
		for (int i = 0 ; i < N ; i++) {
			if (input.get(i).size() == 0) {
				return false;
			}
		}

		return true;
	}

	// map 배열이 어떤 숫자로 이루어져있는지 확인 -> 1은 왼쪽 상단이 .인지 #인지 체크
	private static void numberCheck() {

		int idx = 0;

		for (int i = 0; i < 4 * N - 1; i += 4) {
			String number = "";
			for (int j = 0; j < 5; j++) {
				for (int q = 0; q < 3; q++) {
					number += map[j][i + q];
				}
			}

			// number에는 켜져있는데 numberStr에는 꺼져있다? -> 그 숫자는 아닌 숫자
			for (int j = 0; j < 10; j++) {
				boolean check = true;

				for (int q = 0; q < 15; q++) {
					if (number.charAt(q) == '#' && numberStr[j].charAt(q) == '.') {
						check = false;
						break;
					}
				}

				if (check) {
					input.get(idx).add(j);
				}
			}

			idx++;
		}
	}

	private static void findAvg() {
		double totalCnt = 1.0;
		double totalSum = 0.0;

		for (int i = 0 ; i < N ; i++) {
			totalCnt *= input.get(i).size();
		}

		for (int i = N-1 ; i >= 0 ; i--) {
			int size = input.get(i).size();

			for (int j = 0 ; j < input.get(i).size() ; j++) {
				totalSum += Math.pow(10, N-i-1) * input.get(i).get(j) * (totalCnt / size);
			}
		}

		System.out.println(totalSum / totalCnt);
	}
}