import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, MAX, answer;
    static int[] arr;
    static Queue<Node> PQ;
    static class Node {
        int d, w;

        public Node(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        PQ = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            MAX = Math.max(MAX, d);
            PQ.add(new Node(d, w));
        }

        arr = new int[MAX+1];

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = now.d ; i >= 1 ; i--) {
                if (arr[i] == 0) {
                    arr[i] = now.w;
                    break;
                }
            }
        }

        for (int i = 1 ; i <= MAX ; i++) {
            answer += arr[i];
        }
    }
}