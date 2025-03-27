import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static double answer;
    static int[] parents;
    static Node[] gods;
    static Queue<Node> PQ;
    static class Node {
        int from, to;
        double distance;

        public Node(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        setPQ();
        solve();
        System.out.println(String.format("%.2f", answer));
    }

    static void solve() {
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (find(now.from) != find(now.to)) {
                union(now.from, now.to);
                answer += now.distance;
            }
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        else return parents[num] = find(parents[num]);
    }

    static void setPQ() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = i+1 ; j <= N ; j++) {
                PQ.add(new Node(i, j, calDistance(i, j)));
            }
        }
    }

    static double calDistance(int i, int j) {
        return Math.sqrt(Math.pow(gods[j].to - gods[i].to, 2.0) + Math.pow(gods[j].from - gods[i].from, 2.0));
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = new Node(x, y, 0);
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }
    }

    static void init() {
        parents = new int[N+1];
        for (int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
        gods = new Node[N+1];
        PQ = new PriorityQueue<>((o1, o2) -> Double.compare(o1.distance, o2.distance));
    }
}