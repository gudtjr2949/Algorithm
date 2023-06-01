import java.io.*;
import java.util.*;

// 백준 8320 : 직사각형을 만드는 방법
public class BOJ_8320 {
	
	/* 
	 * 조건 1 : 정사각형이 1개일 때 만들 수 있는 사각형은 약수와 관련..?
	 * 조건 2 : 각각의 약수로 쌍을 만들고 cnt++
	 * 조건 3 : cnt가 짝수면 answer += cnt / 2 
	 * 조건 4 : 만약에 숫자 하나가 남으면 answer += (cnt / 2) + 1 */

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		int answer = 1; // 정사각형 갯수가 1개인 경우
		
		for (int i = 2 ; i <= N ; i++) {
			int cnt = 0;
			for (int j = 1 ; j <= i ; j++) { // 조건 1
				if (i % j == 0) { 
					cnt++; // 조건 2
				}
			}
			
			if (cnt % 2 == 0) // 조건 3
				answer += cnt/2;
			else // 조건 4
				answer += (cnt/2) + 1;
			
		}
		
		System.out.println(answer);
	}
}
