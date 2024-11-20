import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static long answer;
    static int[] bags;
    static Node[] jews;
    static class Node {
        int m, v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jews = new Node[N];
        bags = new int[K];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            jews[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0 ; i < K ; i++) {
            bags[i] = Integer.parseInt(bf.readLine());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(jews, (o1, o2) -> o1.m - o2.m);
        Arrays.sort(bags);

        Queue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());

        int jewIdx = 0;

        for (int i = 0 ; i < K ; i++) {
            while (jewIdx < N && bags[i] >= jews[jewIdx].m) {
                PQ.add(jews[jewIdx++].v);
            }

            if (!PQ.isEmpty()) answer += PQ.poll();
        }
    }
}