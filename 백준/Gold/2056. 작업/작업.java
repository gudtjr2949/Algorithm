import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] edgeCnt, time;
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        edgeCnt = new int[N + 1];
        time = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            edgeCnt[i] = M;
            for (int j = 0; j < M; j++) {
                int first = Integer.parseInt(st.nextToken());
                adj.get(first).add(i);
            }
        }
        
        System.out.println(solve());
    }

    static int solve() {
        Queue<Integer> Q = new LinkedList<>();
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = time[i];
            if (edgeCnt[i] == 0) Q.add(i);
        }

        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                int num = Q.poll();
                for (int next : adj.get(num)) {
                    edgeCnt[next]--;
                    dp[next] = Math.max(dp[next], dp[num] + time[next]);
                    if (edgeCnt[next] == 0) Q.add(next);
                }
            }
        }

        int total = 0;

        for (int i = 1; i <= N; i++) {
            total = Math.max(total, dp[i]);
        }

        return total;
    }
}