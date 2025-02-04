import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;
    static List<List<Integer>> adj;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        Queue<Integer> PQ = new PriorityQueue<>();

        for (int i = 1 ; i <= N ; i++) {
            if (arr[i] == 0) PQ.add(i);
        }

        while (!PQ.isEmpty()) {
            int now = PQ.poll();

            sb.append(now).append(" ");

            for (int next : adj.get(now)) {
                arr[next]--;
                if (arr[next] == 0) PQ.add(next);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int first = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());
            adj.get(first).add(after);
            arr[after]++;
        }
    }

    static void init() {
        arr = new int[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
        sb = new StringBuilder();
    }
}