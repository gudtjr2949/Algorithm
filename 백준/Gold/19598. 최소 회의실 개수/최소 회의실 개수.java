import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Node[] arr;
    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Node(start, end);
        }

        Arrays.sort(arr, (n1, n2) -> n1.start - n2.start);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>((n1, n2) -> n1.end - n2.end);
        PQ.add(arr[0]);
        answer = 1;

        for (int i = 1 ; i < N ; i++) {
            if (PQ.peek().end <= arr[i].start) {
                PQ.poll();
                PQ.add(arr[i]);
            } else {
                PQ.add(arr[i]);
            }

            answer = Math.max(answer, PQ.size());
        }
    }
}