import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, totalCost, useCost;
    static int[] parents;
    static List<Node> list;
    static class Node {
        int start, end, cost;
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            init();
            input(bf);
            solve();
            sb.append(totalCost-useCost).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Collections.sort(list, (o1, o2) -> o1.cost - o2.cost);

        for (int i = 0 ; i < M ; i++) {
            Node now = list.get(i);

            int start = find(now.start);
            int end = find(now.end);

            if (start != end) {
                union(now.start, now.end);
                useCost += now.cost;
            }
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static int find(int idx) {
        if (idx == parents[idx]) return idx;
        return parents[idx] = find(parents[idx]);
    }

    static void input(BufferedReader bf) throws Exception {
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            totalCost += cost;
            list.add(new Node(start, end, cost));
        }
    }

    static void init() {
        totalCost = 0;
        useCost = 0;
        list = new ArrayList<>();
        parents = new int[N+1];
        for (int i = 0 ; i <= N ; i++) parents[i] = i;
    }
}