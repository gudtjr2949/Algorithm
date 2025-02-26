import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();

        Arrays.sort(arr, (o1 ,o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        Q.add(arr[0][1]);
        answer = 1;

        for (int i = 1 ; i < N ; i++) {
            if (Q.peek() <= arr[i][0]) {
                Q.poll();
                Q.add(arr[i][1]);
                answer++;
            }
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