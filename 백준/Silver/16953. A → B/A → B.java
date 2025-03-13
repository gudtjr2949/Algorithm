import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int A, B, answer = -1;
    static Set<Integer> set;
    static class Node {
        int num, cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        PQ.add(new Node(B, 1));
        set.add(B);

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            int num = now.num;

            if (num == A) {
                answer = now.cnt;
                return;
            }

            if (num % 10 == 1 && !set.contains(num / 10)) {
                set.add(num / 10);
                PQ.add(new Node(num / 10, now.cnt + 1));
            }

            if (num % 2 == 0 && !set.contains(num / 2)) {
                set.add(num / 2);
                PQ.add(new Node(num / 2, now.cnt + 1));
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        init();
    }

    static void init() {
        set = new HashSet<>();
    }
}