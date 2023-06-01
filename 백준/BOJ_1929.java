package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1929 : 소수 구하기
public class BOJ_1929 {

    static int N, M;
    static boolean[] prime;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        prime = new boolean[N+1];

        checkPrime();

        for (int i = M ; i <= N ; i++) {
            if (!prime[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void checkPrime() {
        prime[0] = prime[1] = true;

        for (int i = 2 ; i <= Math.sqrt(N) ; i++) {
            if (!prime[i]) {
                for (int j = i * i ; j <= N ; j+=i) {
                    prime[j] = true;
                }
            }
        }
    }
}
