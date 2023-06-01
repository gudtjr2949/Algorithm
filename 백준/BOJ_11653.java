package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 11653 : 소인수분해
public class BOJ_11653 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 2 ; i <= N ; i++) {
            while (N % i == 0) {
                sb.append(i).append("\n");
                N /= i;
            }
        }

        System.out.println(sb);
    }
}
