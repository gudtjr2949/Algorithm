import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer, maxD;
    static PriorityQueue<Node> PQ;
    static class Node {
        int p, d;
        public Node(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.p - o1.p;
            }
        });

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            PQ.add(new Node(p ,d));
            maxD = Math.max(maxD, d);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        boolean[] visited = new boolean[maxD+1];

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = now.d ; i >= 1 ; i--) {
                if (!visited[i]) {
                    answer += now.p;
                    visited[i] = true;
                    break;
                }
            }
        }
    }
}