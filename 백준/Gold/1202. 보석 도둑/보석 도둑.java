import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static long answer;
    static int[] C;
    static Node[] jews;
    static class Node {
        int M, V;

        public Node(int m, int v) {
            M = m;
            V = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        C = new int[K];
        jews = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            jews[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(jews, (o1, o2) -> {
            if (o1.M == o2.M) return o2.V - o1.V;
            else return o1.M - o2.M;
        });

        for (int i = 0 ; i < K ; i++)
            C[i] = Integer.parseInt(bf.readLine());

        Arrays.sort(C);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int idx = 0; // 보석 idx

        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0 ; i < K ; i++) {
            while (idx < N && C[i] >= jews[idx].M) {
                PQ.add(jews[idx].V);
                idx++;
            }

            if (!PQ.isEmpty()) answer += PQ.poll();
        }

    }
}