import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, team, answer = Integer.MAX_VALUE;
    static int[][] S;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N/2 ; i++) {
            team = i;
            dfs(0, 0);
            if (answer == 0) return;
        }
    }

    static void dfs(int idx, int cur) {
        if (idx == team) {
            int diff = calOverall();
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = cur ; i < N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(idx+1, i+1);
                visited[i] = false;
            }
        }
    }

    static int calOverall() {
        int A = 0;
        int B = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = i+1 ; j < N ; j++) {
                if (i == j) continue;

                if (visited[i] && visited[j]) {
                    A += S[i][j] + S[j][i];
                } else if (!visited[i] && !visited[j]) {
                    B += S[i][j] + S[j][i];
                }
            }
        }

        return Math.abs(A - B);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        S = new int[N][N];
        visited = new boolean[N];
    }
}