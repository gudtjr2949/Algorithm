import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean possible;
    static Node[] nodes;
    static List<List<Integer>> adj;
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            input(bf);
            setAdj();
            solve();
            if (possible) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[N+2];

        Q.add(0);
        visited[0] = true;

        while (!Q.isEmpty()) {
            int now = Q.poll();

            if (now == N+1) {
                possible = true;
                return;
            }

            for (int next : adj.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    Q.add(next);
                }
            }
        }
    }

    static int calDistance(Node node1, Node node2) {
        return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
    }


    static void setAdj() {
        for (int i = 0 ; i < N+2 ; i++) {
            for (int j = i+1 ; j < N+2 ; j++) {
                if (calDistance(nodes[i], nodes[j]) <= 1000) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
    }

    static void input(BufferedReader bf) throws Exception {
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        nodes[0] = new Node(x, y);
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }
        st = new StringTokenizer(bf.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        nodes[N+1] = new Node(x, y);
    }

    static void init() {
        nodes = new Node[N+2];
        adj = new ArrayList<>();
        for (int i = 0 ; i < N+2 ; i++) {
            adj.add(new ArrayList<>());
        }
        possible = false;
    }
}