import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static long answer;
    static long[] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dp = new long[1_000_001];
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Long> Q = new LinkedList<>();

        for (long i = 1 ; i <= 9 ; i++) Q.add(i);

        for (int i = 0 ; i <= 9 ; i++) {
            dp[i] = i;
        }

        if (N >= 0 && N < 10) {
            answer = N;
            return;
        }

        int idx = 10;

        while (idx <= N && !Q.isEmpty()) {
            Long now = Q.poll();

            Long end = now % 10; // 가장 마지막 자리 수

            for (long i = 0 ; i < end ; i++) {
                Long next = now * 10 + i;
                dp[idx++] = next;
                Q.add(next);
            }
        }

        if (idx >= N && dp[N] != 0) answer = dp[N];
        else answer = -1;
    }
}