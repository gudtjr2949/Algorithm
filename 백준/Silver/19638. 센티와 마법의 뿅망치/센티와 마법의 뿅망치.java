import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, H, T, cntT;
    static Queue<Integer> PQ;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        printAnswer();
    }

    private static void printAnswer() {
        if (PQ.peek() < H) {
            System.out.println("YES");
            System.out.println(cntT);
        }
        else {
            System.out.println("NO");
            System.out.println(PQ.peek());
        }
    }

    static void solve() {

        while (T > 0 && PQ.peek() >= H) {
            int now = PQ.peek();
            
            if (now == 1) break;
            
            if (now >= H) {
                T--;
                cntT++;
                PQ.poll();
                PQ.add(now/2);
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            PQ.add(Integer.parseInt(bf.readLine()));
        }
    }

    static void init() {
        PQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }
}