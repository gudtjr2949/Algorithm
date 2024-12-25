import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, start, end, answer;
    static int[] dp;
    static List<List<Room>> adj;
    static class Room {
        int idx, dist;

        public Room(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }

    static class Node {
        int idx, sum, max;

        public Node(int idx, int sum, int max) {
            this.idx = idx;
            this.sum = sum;
            this.max = max;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < N-1 ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Room(to, dist));
            adj.get(to).add(new Room(from, dist));
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.sum == o2.sum) return o2.max - o1.max;
            return o1.sum - o2.sum;
        });

        PQ.add(new Node(start, 0, 0));
        dp[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == end) {
                answer = now.sum - now.max;
                return;
            }

            for (Room next : adj.get(now.idx)) {
                if (dp[next.idx] > dp[now.idx] + next.dist) {
                    dp[next.idx] = dp[now.idx] + next.dist;
                    PQ.add(new Node(next.idx, dp[next.idx], Math.max(now.max, next.dist)));
                }
            }
        }
    }
}