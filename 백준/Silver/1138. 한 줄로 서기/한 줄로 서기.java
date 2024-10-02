import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean find;
    static StringBuilder sb;
    static int[] arr, input;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N+1];
        input = new int[N+1];
        visited = new boolean[N+1];
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);

        System.out.println(sb);
    }

    static void dfs(int idx) {
        if (idx == N+1) {
            if (check()) {
                find = true;
                for (int i = 1 ; i <= N ; i++) sb.append(input[i]).append(" ");
            }
            return;
        }

        if (find) return;

        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = i;
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 1 ; i <= N ; i++) {
            int cnt = 0;
            for (int j = 1 ; j < i ; j++) {
                if (input[i] < input[j]) cnt++;
            }

            if (arr[input[i]] != cnt) return false;
        }

        return true;
    }
}