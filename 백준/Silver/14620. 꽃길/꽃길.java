import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, 0, -1}, dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int cnt, int money) {
        if (cnt == 3) {
            answer = Math.min(answer, money);
            return;
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!possible(j, i)) continue;
                int sum = seedling(j, i);
                setVisited(j, i, true);
                dfs(cnt+1, money+sum);
                setVisited(j, i, false);
            }
        }
    }

    static boolean possible(int x, int y) {
        for (int i = 0 ; i < 5 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx])) {
                return false;
            }
        }

        return true;
    }

    static int seedling(int x, int y) {
        int sum = 0;

        for (int i = 0 ; i < 5 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            sum += map[ny][nx];
        }

        return sum;
    }

    static void setVisited(int x, int y, boolean flag) {
        for (int i = 0 ; i < 5 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[ny][nx] = flag;
        }
    }
}