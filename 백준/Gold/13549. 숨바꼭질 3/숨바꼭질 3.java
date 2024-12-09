import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer, MAX = 100_001;
    static int[] dp; // i번째 좌표에 도달할 수 있는 최소시간
    static class Node {
        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[MAX];
        Arrays.fill(dp, MAX);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        dp[N] = 0;

        PQ.add(new Node(N, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == K) {
                answer = now.time;
                return;
            }

            int nIdx = now.idx+1;
            if (nIdx < MAX && dp[nIdx] > now.time+1) {
                dp[nIdx] = now.time+1;
                PQ.add(new Node(nIdx, now.time+1));
            }

            nIdx = now.idx-1;
            if (nIdx >= 0 && dp[nIdx] > now.time+1) {
                dp[nIdx] = now.time+1;
                PQ.add(new Node(nIdx, now.time+1));
            }

            nIdx = now.idx*2;
            if (nIdx < MAX && dp[nIdx] > now.time) {
                dp[nIdx] = now.time;
                PQ.add(new Node(nIdx, now.time));
            }

        }
    }
}