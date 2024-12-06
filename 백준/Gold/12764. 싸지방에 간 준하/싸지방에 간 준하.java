import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, cnt, MAX = 100_001;
    static int[] computers;
    static int[][] arr;
    static class Node {
        int idx, end;

        public Node(int idx, int end) {
            this.idx = idx;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        computers = new int[N];
        arr = new int[N][2];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = start;
            arr[i][1] = end;
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        solve();

        System.out.println(cnt);
        for (int i = 0 ; i < cnt ; i++) {
            System.out.print(computers[i] + " ");
        }
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        Queue<Integer> computerQ = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) computerQ.add(i);

        for (int i = 0 ; i < N ; i++) {
            while (!PQ.isEmpty() && PQ.peek().end <= arr[i][0]) {
                computerQ.add(PQ.poll().idx);
            }

            int idx = computerQ.poll();

            PQ.add(new Node(idx, arr[i][1]));
            computers[idx]++;
            cnt = Math.max(cnt, PQ.size());
        }
    }
}