import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int q = 0; q < N; q++) {
                    // i -> q 로 바로 가는 것 보다, i -> j -> q q를 경유해 가는 방법이 더 짧은 경우
                    if (arr[i][j] + arr[j][q] < arr[i][q]) {
                        arr[i][q] = arr[i][j] + arr[j][q];
                    }
                }
            }
        }

        dfs(start, 0);

        System.out.println(answer);
    }

    static void dfs(int start, int time) {
        if (check()) {
            answer = Math.min(answer, time);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, time + arr[start][i]);
                visited[i] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }
}