import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static Node[] arr;
    static class Node {
        int s, t;

        public Node(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new Node[N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1.s == o2.s) return o1.t - o2.t;
            return o1.s - o2.s;
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.t - o2.t);

        for (int i = 0 ; i < N ; i++) {
            if (PQ.isEmpty() || PQ.peek().t > arr[i].s) {
                PQ.add(arr[i]);
            } else {
                PQ.poll();
                PQ.add(arr[i]);
            }

            answer = Math.max(answer, PQ.size());
        }
    }
}