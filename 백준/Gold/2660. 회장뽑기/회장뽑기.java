import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, minPoint;
    static int[] arr;
    static List<Integer> answer;
    static List<List<Integer>> adj;
    static class Node {
        int idx, cnt;
        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        adj = new ArrayList<>();
        answer = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from == -1 && to == -1) break;

            adj.get(from).add(to);
            adj.get(to).add(from);
        }

        for (int i = 1 ; i <= N ; i++) solve(i);

        findAnswer();

        System.out.println(minPoint + " " + answer.size());
        for (Integer num : answer) System.out.print(num + " ");
    }

    static void findAnswer() {
        minPoint = N+1;

        for (int i = 1 ; i <= N ; i++) {
            minPoint = Math.min(minPoint, arr[i]);
        }

        for (int i = 1 ; i <= N ; i++) {
            if (arr[i] == minPoint) answer.add(i);
        }
    }

    static void solve(int start) {
        Queue<Node> Q = new LinkedList<>();
        int[] dp = new int[N+1];
        Arrays.fill(dp, N+1);
        dp[start] = 0;
        Q.add(new Node(start, 0));

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (Integer next : adj.get(now.idx)) {
                if (dp[next] > dp[now.idx] + 1) {
                    dp[next] = dp[now.idx] + 1;
                    Q.add(new Node(next, now.cnt+1));
                }
            }
        }

        // findMax
        int max = 0;
        for (int i = 1 ; i <= N ; i++) max = Math.max(max, dp[i]);

        arr[start] = max;
    }
}