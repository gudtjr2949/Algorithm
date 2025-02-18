import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] book;
    static boolean[] visited;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            input(bf);
            solve();
            findAnswer();
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i <= M ; i++) {
            visited = new boolean[N+1];
            dfs(i);
        }
    }

    static boolean dfs(int idx) {
        for (int i = arr[idx][0] ; i <= arr[idx][1] ; i++) {
            if (visited[i]) continue;

            visited[i] = true;

            if (book[i] == 0 || dfs(book[i])) {
                book[i] = idx;
                return true;
            }
        }

        return false;
    }

    static void findAnswer() {
        int result = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (book[i] != 0) result++;
        }

        sb.append(result).append("\n");
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a;
            arr[i][1] = b;
        }
    }

    static void init() {
        arr = new int[M+1][2];
        book = new int[N+1];
    }
}