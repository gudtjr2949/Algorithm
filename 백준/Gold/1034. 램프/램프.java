import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer;
    static String[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (!visited[i] && check(i)) {
                int cnt = 0;

                for (int j = i ; j < N ; j++) {
                    if (arr[i].equals(arr[j])) {
                        visited[j] = true;
                        cnt++;
                    }
                }
                answer = Math.max(answer, cnt);
            }
        }
    }

    static boolean check(int idx) {
        int zero = 0;
        for (int i = 0 ; i < M ; i++) {
            if (arr[idx].charAt(i) == '0') zero++;
        }

        if (zero <= K && ((K%2 != 0 && zero%2 != 0) || (K%2 == 0 && zero%2 == 0))) return true;

        return false;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            arr[i] = s;
        }
        K = Integer.parseInt(bf.readLine());
    }

    static void init() {
        arr = new String[N];
        visited = new boolean[N];
    }
}