import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, X, answer, MAX = 1_000_001;
    static int[] xDis;
    static List<List<Node>> adj;
    static class Node {
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        findXDis();
        for (int i = 1 ; i <= N ; i++) {
            if (i == X) continue;
            solve(i);
        }
        System.out.println(answer);
    }

    static void findXDis() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        PQ.add(new Node(X, 0));
        xDis[X] = 0;


        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (Node next : adj.get(now.idx)) {
                if (xDis[next.idx] > xDis[now.idx] + next.time) {
                    xDis[next.idx] = xDis[now.idx] + next.time;
                    PQ.add(new Node(next.idx, xDis[next.idx]));
                }
            }
        }
    }

    static void solve(int start) {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        int[] dp = new int[N+1];
        Arrays.fill(dp, MAX);

        PQ.add(new Node(start, 0));
        dp[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == X) break;

            for (Node next : adj.get(now.idx)) {
                if (dp[next.idx] > dp[now.idx] + next.time) {
                    dp[next.idx] = dp[now.idx] + next.time;
                    PQ.add(new Node(next.idx, dp[next.idx]));
                }
            }
        }

        answer = Math.max(answer, dp[X] + xDis[start]);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            adj.get(start).add(new Node(end, time));
        }
    }

    static void init() {
        xDis = new int[N+1];
        Arrays.fill(xDis, MAX);
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
    }

}