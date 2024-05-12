import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][K];
        visited = new boolean[N*2+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < K ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1 ; i <= N*2 ; i++) {
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int cur, int idx) {
        if (idx == N) {
            answer = Math.max(answer, solve());
            return;
        }

        for (int i = cur ; i <= N*2 ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1,idx+1);
                visited[i] = false;
            }
        }
    }

    static int solve() {
        int cnt = 0;

        for (int i = 0 ; i < M ; i++) {
            boolean possible = true;

            for (int j = 0 ; j < K ; j++) {
                if (!visited[map[i][j]]) {
                    possible = false;
                    break;
                }
            }

            if (possible) cnt++;
        }

        return cnt;
    }
}