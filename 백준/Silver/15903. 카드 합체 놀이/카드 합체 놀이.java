import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long answer;
    static Queue<Long> PQ;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < M ; i++) {
            long a = PQ.poll();
            long b = PQ.poll();
            long sum = a+b;
            PQ.add(sum);
            PQ.add(sum);
        }

        while (!PQ.isEmpty()) {
            answer += PQ.poll();
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            PQ.add(Long.parseLong(st.nextToken()));
        }
    }

    static void init() {
        PQ = new PriorityQueue<>();
    }
}