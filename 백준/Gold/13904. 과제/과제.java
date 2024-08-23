import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, maxDay, answer;
    static PriorityQueue<Node> PQ;
    static class Node {
        int day, weight;

        public Node(int day, int weight) {
            this.day = day;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.weight - o1.weight;
            }
        });

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int day = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            PQ.add(new Node(day, weight));
            maxDay = Math.max(maxDay, day);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        boolean[] visited = new boolean[maxDay+1];

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = now.day ; i >= 1 ; i--) {
                if (!visited[i]) {
                    answer += now.weight;
                    visited[i] = true;
                    break;
                }
            }
        }
    }
}