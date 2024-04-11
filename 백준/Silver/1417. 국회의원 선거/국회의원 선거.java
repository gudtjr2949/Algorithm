import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N, dasom;
    static PriorityQueue<Integer> PQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        dasom = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < N-1 ; i++) {
            PQ.add(Integer.parseInt(bf.readLine()));
        }

        System.out.println(solve());
    }

    static int solve() {

        int cnt = 0;

        while (!PQ.isEmpty()) {
            int tmp = PQ.poll();
            if (tmp >= dasom) { // 매수
                dasom++;
                PQ.add(tmp-1);
                cnt++;
            } else {
                break;
            }
        }

        return cnt;
    }
}