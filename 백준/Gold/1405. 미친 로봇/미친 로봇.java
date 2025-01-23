import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static double answer;
    static int[] dir, dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        input();
        visited[15][15] = true;
        dfs(15, 15, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int idx, double percent) {
        if (idx == N) {
            answer += percent;
            return;
        }

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny, idx+1, percent * dir[i] * 0.01);
                visited[ny][nx] = false;
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < 30 && y >= 0 && y < 30;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        init();
        dir[0] = Integer.parseInt(st.nextToken());
        dir[1] = Integer.parseInt(st.nextToken());
        dir[2] = Integer.parseInt(st.nextToken());
        dir[3] = Integer.parseInt(st.nextToken());
    }

    static void init() {
        dir = new int[4];
        visited = new boolean[30][30];
    }
}