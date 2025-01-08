import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long total, answer;
    static Node[] nodes;
    static class Node {
        long idx, person;
        public Node(long idx, long person) {
            this.idx = idx;
            this.person = person;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        nodes = new Node[N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            long idx = Long.parseLong(st.nextToken());
            long person = Long.parseLong(st.nextToken());
            nodes[i] = new Node(idx, person);
            total += person;
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(nodes, (o1, o2) -> (int) o1.idx - (int) o2.idx);

        long mid = (total + 1) / 2;

        long sum = 0;

        for (int i = 0 ; i < N ; i++) {
            sum += nodes[i].person;

            if (sum >= mid) {
                answer = nodes[i].idx;
                return;
            }
        }
    }
}