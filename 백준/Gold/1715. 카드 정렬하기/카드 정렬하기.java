import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int answer;
    static PriorityQueue<Integer> PQ;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PQ = new PriorityQueue<>();

        int N = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            PQ.add(Integer.parseInt(bf.readLine()));
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (PQ.size() != 1) {
            int A = PQ.poll();
            int B = PQ.poll();
            PQ.add(A + B);
            answer += A + B;
        }
    }
}