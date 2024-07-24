import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long sum, answer;
    static List<Node> list;
    static class Node {
        long idx, cnt;

        public Node(long idx, long cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        list = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            long idx = Long.parseLong(st.nextToken());
            long cnt = Long.parseLong(st.nextToken());
            list.add(new Node(idx, cnt));
            sum += cnt;
        }

        sum++;

        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.idx - o2.idx);
            }
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long result = 0;

        for (Node next : list) {
            result += next.cnt;

            if ((sum / 2) <= result) {
                answer = next.idx;
                break;
            }
        }
    }
}