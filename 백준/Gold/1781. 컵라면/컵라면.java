import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static PriorityQueue<Node> PQ1;
    static PriorityQueue<Integer> PQ2;
    static class Node {
        int deadLine, ramen;

        public Node(int deadLine, int ramen) {
            this.deadLine = deadLine;
            this.ramen = ramen;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "deadLine=" + deadLine +
                    ", ramen=" + ramen +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        PQ1 = new PriorityQueue<>((o1, o2) -> {
            if (o1.deadLine == o2.deadLine) return o2.ramen - o1.ramen;
            else return o1.deadLine - o2.deadLine;
        });

        PQ2 = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            PQ1.add(new Node(deadLine, ramen));
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (!PQ1.isEmpty()) {
            Node now = PQ1.poll();
            if (now.deadLine > PQ2.size()) {
                PQ2.add(now.ramen);
            } else {
                if (PQ2.peek() < now.ramen) {
                    PQ2.poll();
                    PQ2.add(now.ramen);
                }
            }
        }

        while (!PQ2.isEmpty()) {
            answer += PQ2.poll();
        }
    }
}