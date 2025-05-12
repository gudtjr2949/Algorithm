import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        Queue<Integer> PQ = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            if (PQ.isEmpty() || PQ.peek() > arr[i][0]) {
                PQ.add(arr[i][1]);
            } else {
                while (!PQ.isEmpty() && PQ.peek() <= arr[i][0]) {
                    PQ.poll();
                }
                PQ.add(arr[i][1]);
            }

            answer = Math.max(answer, PQ.size());
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = start;
            arr[i][1] = end;
        }
    }

    static void init() {
        arr = new int[N][2];
    }
}