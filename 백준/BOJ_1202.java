package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1202 : 보석 도둑
public class BOJ_1202 {

    static int N, K;
    static long answer;
    static int[] C;
    static Jewelry[] jewelries;

    static class Jewelry implements Comparable<Jewelry> {
        int M, V;

        public Jewelry(int m, int v) {
            M = m;
            V = v;
        }

        @Override
        public String toString() {
            return "Jewelry{" +
                    "M=" + M +
                    ", V=" + V +
                    '}';
        }

        @Override
        public int compareTo(Jewelry o) {
            if (o.M == this.M) {
                return o.V - this.V;
            }
            return this.M - o.M;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewelries = new Jewelry[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());

            Jewelry j = new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            jewelries[i] = j;
        }

        Arrays.sort(jewelries);

        C = new int[K];

        for (int i = 0 ; i < K ; i++) {
            C[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(C);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        // 보석 인덱스
        int idx = 0;

        // 내림차순 정렬
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0 ; i < K ; i++) {
            // 보석의 인덱스가 N보다 작고, 현재 보고있는 보석이 i번째 가방에 들어갈 수 있는 경우
            while (idx < N && jewelries[idx].M <= C[i]) {
                PQ.add(jewelries[idx].V);
                idx++;
            }

            if (!PQ.isEmpty()) {
                answer += PQ.poll();
            }
        }
    }
}
