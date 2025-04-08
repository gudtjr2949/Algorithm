import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static boolean[] visited;
    static Queue<Integer> Q;
    static Queue<Node> PQ;
    static class Node {
        int time, ramen;
        public Node(int time, int ramen) {
            this.time = time;
            this.ramen = ramen;
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

            if (now.time > Q.size()) {
                Q.add(now.ramen);
            } else {
                if (Q.peek() < now.ramen) {
                    Q.poll();
                    Q.add(now.ramen);
                }
            }
        }

        while (!Q.isEmpty()) {
            answer += Q.poll();
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int time = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            PQ.add(new Node(time, ramen));
        }
    }

    static void init() {
        Q = new PriorityQueue<>();
        PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) return o2.ramen - o1.ramen;
            return o1.time - o2.time;
        });
    }
}