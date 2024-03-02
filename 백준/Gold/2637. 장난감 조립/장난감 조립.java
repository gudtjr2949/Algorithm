import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] edgeCnt, used;
    static List<List<Part>> adj = new ArrayList<>();

    static class Part {
        int num, need;

        public Part(int num, int need) {
            this.num = num;
            this.need = need;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        edgeCnt = new int[N+1];
        used = new int[N+1];

        int[] first = new int[N+1];

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        int M = Integer.parseInt(bf.readLine());


        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            adj.get(X).add(new Part(Y, K));
            first[X]++;
            edgeCnt[Y]++;
        }

        solve();

        for (int i = 1 ; i <= N ; i++) {
            if (first[i] == 0) {
                System.out.println(i + " " + used[i]);
            }
        }
    }

    static void solve() {
        Queue<Part> Q = new LinkedList<>();
        Q.add(new Part(N, 1));
        used[N] = 1;

        while (!Q.isEmpty()) {
            Part now = Q.poll();

            for (Part next : adj.get(now.num)) {
                used[next.num] += used[now.num] * next.need;
                edgeCnt[next.num]--;
                if (edgeCnt[next.num] == 0) Q.add(new Part(next.num, used[next.num]));
            }
        }
    }
}