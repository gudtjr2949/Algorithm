import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] edgeCnt;
    static List<Integer> list;
    static List<List<Integer>> adj = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeCnt = new int[N+1];
        list = new ArrayList<>();

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj.get(A).add(B);
            edgeCnt[B]++;
        }

        solve();
        
        for (int answer : list) {
            System.out.print(answer + " ");
        }

    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 1 ; i <= N ; i++) {
            if (edgeCnt[i] == 0) {
                Q.add(i);
            }
        }

        while (!Q.isEmpty()) {
            int now = Q.poll();

            list.add(now);

            for (int next : adj.get(now)) {
                edgeCnt[next]--;
                if (edgeCnt[next] == 0) Q.add(next);
            }
        }
    }
}