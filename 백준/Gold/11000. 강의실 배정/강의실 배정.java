import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][2];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[0] - o2[0];
            else return o1[0] - o2[0];
        });

        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        PQ.add(arr[0][1]);
        answer = 1;

        for (int i = 1 ; i < N ; i++) {
            answer = Math.max(answer, PQ.size());

            if (PQ.peek() > arr[i][0]) {
                PQ.add(arr[i][1]);
            } else {
                while (!PQ.isEmpty() && PQ.peek() <= arr[i][0]) {
                    PQ.poll();
                }
                PQ.add(arr[i][1]);
            }
        }
    }
}