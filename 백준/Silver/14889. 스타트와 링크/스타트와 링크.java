import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static boolean[] visited;
    static int[][] S;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        dfs(0, 0);
    }

    static void dfs(int idx, int cur) {
        if (idx == N/2) {
            calculateOveralls();
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

    static void calculateOveralls() {
        int aTeam = 0;
        int bTeam = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (i == j) continue;

                if (visited[i] && visited[j]) {
                    aTeam += S[i][j];
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (i == j) continue;

                if (!visited[i] && !visited[j]) {
                    bTeam += S[i][j];
                }
            }
        }

        answer = Math.min(answer, Math.abs(aTeam - bTeam));
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
        visited = new boolean[N];
        S = new int[N][N];
        answer = 10_001;
    }
}