import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static PriorityQueue<Long> PQ;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PQ = new PriorityQueue<>();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) PQ.add(Long.parseLong(st.nextToken()));

        System.out.println(solve());
    }

    static long solve() {
        for (int i = 0 ; i < M ; i++) {
            long a = PQ.poll();
            long b = PQ.poll();
            PQ.add(a+b);
            PQ.add(a+b);
        }

        long sum = 0;

        for (int i = 0 ; i < N ; i++) sum += PQ.poll();

        return sum;
    }
}