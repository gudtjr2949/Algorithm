import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, A, B;
    static long C;
    static List<List<Street>> adj = new ArrayList<>();
    static class Street implements Comparable<Street> {
        int location, money, total, shame;

        public Street(int location, int money, int total, int shame) {
            this.location = location;
            this.money = money;
            this.total = total;
            this.shame = shame;
        }

        @Override
        public int compareTo(Street s) {
            return s.total - this.total;
        }

        @Override
        public String toString() {
            return "Street{" +
                    "location=" + location +
                    ", money=" + money +
                    ", total=" + total +
                    ", shame=" + shame +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            adj.get(from).add(new Street(to, money, 0, 0));
            adj.get(to).add(new Street(from, money, 0, 0));
        }

        long answer = dijk();

        System.out.println(answer == Long.MAX_VALUE ? -1 : answer);
    }


    static long dijk() {
        PriorityQueue<Street> PQ = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        long[] money = new long[N+1];

        Arrays.fill(money, Long.MAX_VALUE);

        PQ.add(new Street(A, 0, 0, 0));
        money[A] = 0;

        long minShame = Long.MAX_VALUE;

        while (!PQ.isEmpty()) {
            Street s = PQ.poll();

            if (s.location == B) {
                minShame = Math.min(minShame, s.shame);
                continue;
            }

            if (!visited[s.location]) visited[s.location] = true;

            for (Street next : adj.get(s.location)) {
                if (!visited[next.location] && money[s.location] + next.money < money[next.location] && s.total + next.money <= C) {
                    money[next.location] = money[s.location] + next.money;
                    PQ.add(new Street(next.location, next.money, s.total + next.money, Math.max(s.shame, next.money)));
                }
            }
        }

        return minShame;
    }
}