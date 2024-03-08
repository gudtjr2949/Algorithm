import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] preVisited;
    static List<List<Node>> adj;

    static class Node implements Comparable<Node> {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        preVisited = new int[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        int M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Node(v, cost));
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(dijk(start, end));

        // 역추적
        int cnt = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(end);
        while (preVisited[end] != 0) {
            cnt++;
            stack.push(preVisited[end]);
            end = preVisited[end];
        }

        System.out.println(cnt);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    static int dijk(int start, int end) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        PQ.add(new Node(start, 0));
        dp[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (!visited[now.v]) {
                visited[now.v] = true;
                for (Node next : adj.get(now.v)) {
                    if (dp[next.v] > now.cost + next.cost) {
                        dp[next.v] = now.cost + next.cost;
                        preVisited[next.v] = now.v;
                        PQ.add(new Node(next.v, dp[next.v]));
                    }
                }
            }
        }

        return dp[end];
    }
}