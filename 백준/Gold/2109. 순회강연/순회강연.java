import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static boolean[] visited;
    static Queue<Node> PQ;
    static class Node {
        int p, d;
        public Node(int p, int d) {
            this.p = p;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = now.d ; i >= 1 ; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    answer += now.p;
                    break;
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            PQ.add(new Node(p, d));
        }
    }

    static void init() {
        PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.p == o2.p) return o1.d - o2.d;
            return o2.p - o1.p;
        });
        visited = new boolean[10_001];
    }
}