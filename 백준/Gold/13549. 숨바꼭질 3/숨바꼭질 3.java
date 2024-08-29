import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, MAX = 100_000*2;
    static int[] dist;
    static class Node {
        int idx, cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);

        solve();

        System.out.println(dist[K]);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });

        boolean[] visited = new boolean[MAX];

        dist[N] = 0;
        PQ.add(new Node(N, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == K) return;

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            for (int i = 0 ; i < 3 ; i++) {
                int nextIdx = now.idx;
                int nextCnt = now.cnt;

                if (i == 0) {
                    nextIdx--;
                    nextCnt++;
                } else if (i == 1) {
                    nextIdx++;
                    nextCnt++;
                } else {
                    nextIdx *= 2;
                }

                if (nextIdx >= 0 && nextIdx < MAX && !visited[nextIdx] && dist[nextIdx] > nextCnt) {
                    dist[nextIdx] = nextCnt;
                    PQ.add(new Node(nextIdx, dist[nextIdx]));
                }
            }
        }
    }
}