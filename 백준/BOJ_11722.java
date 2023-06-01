package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11722 : 가장 긴 감소하는 부분 수열
public class BOJ_11722 {
	public static void main(String[] args) throws Exception { 
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int[] memo = new int[N];

		for (int i = 0 ; i < N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(memo, 1);

		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < i ; j++) {
				if (A[i] < A[j]) {
					memo[i] = Math.max(memo[j]+1, memo[i]);
				}
			}
		}

		int answer = Integer.MIN_VALUE;

		for (int i = 0 ; i < N ; i++) {
			if (answer < memo[i]) {
				answer = memo[i];
			}
		}

		System.out.println(answer);
	}
}
