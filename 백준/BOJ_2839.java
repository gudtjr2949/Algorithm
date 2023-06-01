package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2839 : 설탕 배달
public class BOJ_2839 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int answer = -1;

        Loop:
        for (int i = 0 ; i <= N/3 ; i++) {
            for (int j = 0 ; j <= N/5 ; j++) {
                if ((i * 3) + (j * 5) == N) {
                    answer = i + j;
                    break Loop;
                }
            }
        }

        System.out.println(answer);
    }
}
