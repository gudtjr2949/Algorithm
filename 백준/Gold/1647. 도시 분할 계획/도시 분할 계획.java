import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, totalCost, maxCost;
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
        input();
        solve();
        System.out.println(totalCost - maxCost);
    }

    static void solve() {
        Collections.sort(list, (o1, o2) -> o1.cost - o2.cost);

        for (int i = 0 ; i < M ; i++) {
            Node now = list.get(i);

            int start = find(now.start);
            int end = find(now.end);

            if (start != end) {
                totalCost += now.cost;
                maxCost = Math.max(maxCost, now.cost); // 모든 집이 연결된 후, 그 중 하나의 길만 없애면 됨.
                union(now.start, now.end);
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

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end, cost));
        }
    }

    static void init() {
        list = new ArrayList<>();
        parents = new int[N+1];
        for (int i = 1 ; i <= N ; i++) parents[i] = i;
    }
}