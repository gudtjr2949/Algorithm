package coding_test;

import java.util.*;
import java.io.*;

// 백준 2596 : 비밀편지
public class BOJ_2596 {

	/*
	 * 조건 1 : 일치하는 숫자열이 있으면 종료
	 * 조건 2 : 만약에 완벽하게 일치하는 숫자열이 없지만 다른 문자가 1개인 경우에는 해당 문자를 정답 배열에 넣음
	 * 조건 3 : 그래도 없으면 해당 숫자열의 인덱스를 정답으로 정함
	 * 
	 */

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String[] num = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010" };
		String[] alpha = { "A", "B", "C", "D", "E", "F", "G", "H" };

		int N = Integer.parseInt(bf.readLine());

		String s = bf.readLine();

		String[] arr = new String[N];

		String[] answer = new String[N];

		for (int i = 0; i < N; i++) {
			arr[i] = "";
			answer[i] = "";
		}

		int idx = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i > 0 && (i % 6 == 0))
				idx++;

			arr[idx] += s.charAt(i);
		}

		int tmp = 0;
		
		// 조건 1
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 8; j++) {
				if (arr[i].equals(num[j])) {
					answer[i] = alpha[j];
					break;
				}
			}
		}
		
		// 조건 2
		for (int i = 0; i < N; i++) {
			if (answer[i].equals("")) {
				for (int j = 0; j < 8; j++) {
					int cnt = 0;
					for (int q = 0; q < 6; q++) {
						if (arr[i].charAt(q) != num[j].charAt(q))
							cnt++;
					}

					if (cnt == 1) {
						answer[i] = alpha[j];
						break;
					}
				}
			}
		}

		boolean sign = true;

		int answer_idx = 0;
		
		// 조건 3
		for (int i = 0; i < N; i++) {
			if (answer[i].equals("")) {
				sign = false;
				answer_idx = i + 1;
				break;
			}
		}

		if (sign) {
			for (int i = 0; i < N; i++)
				System.out.print(answer[i]);
		} else {
			System.out.println(answer_idx);
		}
	}

}
