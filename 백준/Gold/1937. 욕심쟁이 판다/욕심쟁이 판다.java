import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, dp;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        dp = new int[N][N];
        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                int cnt = dfs(j, i);

                if (answer < cnt) {
                    answer = cnt;
                }
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        // 이미 다른 곳에서 탐색한 경로일 경우
        if (dp[y][x] != 0) {
            return dp[y][x];
        }

        dp[y][x] = 1;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < N && dy >= 0 && dy < N && map[y][x] < map[dy][dx]) {
                // dfs에 +1 을 해주는 이유는, dfs 특성상 다시 되돌아가면서 한 칸식 이동한 걸 더해야 하기 때문
                dp[y][x] = Math.max(dp[y][x], dfs(dx, dy) + 1);
            }
        }

        return dp[y][x];
    }
}