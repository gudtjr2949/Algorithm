import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] S;
    static boolean[] visited; // 포지션 지정 여부
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            S = new int[11][11];
            visited = new boolean[11];

            max = 0;

            for (int i = 0 ; i < 11 ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0 ; j < 11 ; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 11, 0);

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int player, int cnt, int total) {
        if (cnt == 0) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0 ; i < 11 ; i++) {
            if (!visited[i] && S[player][i] != 0) {
                visited[i] = true;
                dfs(player+1, cnt-1, total + S[player][i]);
                visited[i] = false;
            }
        }
    }
}