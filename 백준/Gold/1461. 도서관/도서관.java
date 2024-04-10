import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static PriorityQueue<Integer> positivePQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
    static PriorityQueue<Integer> negativePQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num < 0) negativePQ.add(num * -1);
            else positivePQ.add(num);
        }

        System.out.println(solve());
    }

    static int solve() {
        int sum = 0;
        int last = 0;

        // 가장 멀리가야 하는 경우(절댓값이 가장 큰 경우)를 가장 마지막에 수행해, 다시 원점으로 돌아오지 않는 것이 가장 이득임
        if (positivePQ.isEmpty()) {
            sum -= negativePQ.peek();
        } else if (negativePQ.isEmpty()) {
            sum -= positivePQ.peek();
        } else {
            sum -= Math.max(positivePQ.peek(), negativePQ.peek());
        }

        while (!positivePQ.isEmpty()) {
            int max = positivePQ.poll();
            for (int i = 0; i < M-1; i++) {
                if (!positivePQ.isEmpty()) positivePQ.poll();
            }

            sum += max * 2;
        }

        while (!negativePQ.isEmpty()) {
            int max = negativePQ.poll();
            for (int i = 0; i < M-1; i++) {
                if (!negativePQ.isEmpty()) negativePQ.poll();
            }

            sum += max * 2;
        }

        return sum;
    }
}