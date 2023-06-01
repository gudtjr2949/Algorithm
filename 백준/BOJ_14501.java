package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 14501 : 퇴사
public class BOJ_14501 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] T = new int[N+1];
        int[] P = new int[N+1];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N+2];

        for (int i = N ; i >= 1 ; i--) {
            if (i + T[i] <= N+1) {
                // Math.max(i일에 상담을 해서 얻을 수 있는 이득 + i+T[i] 일에 얻은 최대이득, i+1 ~ i+T[i]일 동안 얻을 수 있는 이득)
                memo[i] = Math.max(P[i] + memo[i+T[i]], memo[i+1]);
            } else {
                memo[i] = memo[i+1];
            }
        }

        System.out.println(memo[1]);
    }
}
