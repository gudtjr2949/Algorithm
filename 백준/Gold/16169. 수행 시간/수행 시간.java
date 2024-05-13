import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer, rateMax;
    static int[] edgeCnt, dp;
    static List<List<Node>> adj = new ArrayList<>();
    static class Node {
        int idx, time;
        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        edgeCnt = new int[N+1];
        dp = new int[N+1];

        for (int i = 0 ; i <= N+1 ; i++) {
            adj.add(new ArrayList<>());
        }

        rateMax = 0;

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int rate = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            rateMax = Math.max(rateMax, rate);
            adj.get(rate).add(new Node(i, time));
        }

        for (int i = 2 ; i <= rateMax ; i++) {
            edgeCnt[i] = adj.get(i).size() * adj.get(i-1).size();
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> Q = new LinkedList<>();

        for (Node n : adj.get(1)) {
            Q.add(n);
        }

        int rate = 2;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (Node next : adj.get(rate)) {
                edgeCnt[rate]--;

                dp[next.idx] = Math.max(dp[next.idx],
                        dp[now.idx] + (int) Math.pow(next.idx - now.idx, 2.0) + now.time);

                if (rate == rateMax) {
                    answer = Math.max(answer, dp[next.idx] + next.time);
                }

                if (edgeCnt[rate] == 0) {
                    for (Node input : adj.get(rate)) {
                        Q.add(input);
                    }
                    rate++;
                }
            }
        }
    }
}