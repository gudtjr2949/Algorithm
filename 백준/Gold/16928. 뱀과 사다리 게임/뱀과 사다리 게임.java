import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static Point[] ladder, snake;
    static class Point {
        int from, to;

        public Point(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

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
        M = Integer.parseInt(st.nextToken());
        ladder = new Point[N];
        snake = new Point[M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            ladder[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            snake[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });

        boolean[] visited = new boolean[101];
        visited[1] = true;
        PQ.add(new Node(1, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            visited[now.idx] = true;

            if (now.idx == 100) {
                answer = now.cnt;
                return;
            }

            int next = find(now.idx);
            if (next != -1) {
                PQ.add(new Node(next, now.cnt));
                continue;
            }

            for (int i = 1 ; i <= 6 ; i++) {
                if (now.idx+i <= 100 && !visited[now.idx+i]) {
                    PQ.add(new Node(now.idx + i, now.cnt + 1));
                }
            }
        }
    }

    static int find(int idx) {
        for (int i = 0 ; i < N ; i++) {
            if (ladder[i].from == idx) {
                return ladder[i].to;
            }
        }

        for (int i = 0 ; i < M ; i++) {
            if (snake[i].from == idx) {
                return snake[i].to;
            }
        }

        return -1;
    }
}