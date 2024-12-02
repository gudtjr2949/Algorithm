import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, start, end, MAX, answer;
    static boolean possible;
    static boolean[] visited;
    static List<List<Node>> adj;
    static class Node {
        int idx, weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, c);
            adj.get(a).add(new Node(b, c));
            adj.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = MAX;

        while (left <= right) {
            int mid = (left + right) / 2;
            visited = new boolean[N+1];
            possible = false;
            check(start, mid);
            if (possible) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        answer = right;
    }

    static void check(int idx, int weight) {
        if (idx == end) {
            possible = true;
            return;
        }

        visited[idx] = true;

        for (Node next : adj.get(idx)) {
            if (next.weight >= weight && !visited[next.idx]) {
                check(next.idx, weight);
            }
        }
    }
}