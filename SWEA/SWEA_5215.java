package coding_test.SWEA;

import java.io.*;
import java.util.Arrays;

// SWEA 5215 : 햄버거 다이어트
public class SWEA_5215 {
	static int N;
	static int L;

	static int[] T;
	static int[] K;

	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int i = 0; i < Test; i++) {
			answer = Integer.MIN_VALUE;

			String nl = bf.readLine();
			String[] nl_arr = nl.split(" ");
			N = Integer.parseInt(nl_arr[0]);
			L = Integer.parseInt(nl_arr[1]);

			T = new int[N]; // 맛의 점수
			K = new int[N]; // 칼로리

			for (int j = 0; j < N; j++) {
				String s = bf.readLine();
				String[] s_arr = s.split(" ");
				T[j] = Integer.parseInt(s_arr[0]);
				K[j] = Integer.parseInt(s_arr[1]);
			}

			for (int j = 1; j <= N; j++) { // 선택한 재료가 j개 일 때,
				solve(new int[j], new int[j], 0, 0);
			}

			sb.append("#").append(i + 1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void solve(int[] score, int[] kal, int cur, int idx) {
		if (cur == score.length) {
			if (sum(kal) <= L) { // 선택한 재료들의 칼로리 합이 제한 칼로리 L 이하인 경우
				answer = Math.max(answer, sum(score)); // 그 재료들의 점수 합 구하기
			}

			return;
		}

		for (int i = idx; i < N; i++) {
			score[cur] = T[i];
			kal[cur] = K[i];

			solve(score, kal, cur + 1, i + 1);
		}
	}

	private static int sum(int[] arr) {
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		return sum;
	}
}
