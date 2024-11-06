import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] edge;
    static List<List<Integer>> adj;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edge = new int[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int later = Integer.parseInt(st.nextToken());
            edge[later]++;
            adj.get(first).add(later);
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        PriorityQueue<Integer> PQ = new PriorityQueue<>();

        for (int i = 1 ; i <= N ; i++) {
            if (edge[i] == 0) PQ.add(i);
        }

        while (!PQ.isEmpty()) {
            int now = PQ.poll();

            sb.append(now).append(" ");

            for (int next : adj.get(now)) {
                edge[next]--;

                if (edge[next] == 0) PQ.add(next);
            }
        }
    }
}