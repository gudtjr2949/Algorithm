import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int start, end, MAX = 100_001*2, answer;
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
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        solve();
        System.out.println(answer);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });

        PQ.add(new Node(start, 0));
        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[MAX];
        dist[start] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.idx == end) {
                answer = now.time;
                return;
            }

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            if (now.idx+1 <= 100_000 && !visited[now.idx+1] && dist[now.idx+1] > now.time+1) {
                dist[now.idx+1] = now.time+1;
                PQ.add(new Node(now.idx+1, now.time+1));
            }
            if (now.idx-1 >= 0 && !visited[now.idx-1] && dist[now.idx-1] > now.time+1) {
                dist[now.idx-1] = now.time+1;
                PQ.add(new Node(now.idx-1, now.time+1));
            }

            if (now.idx * 2 <= 100_000 && !visited[now.idx*2] && dist[now.idx*2] > now.time) {
                dist[now.idx*2] = now.time;
                PQ.add(new Node(now.idx*2, now.time));
            }
        }
    }
}