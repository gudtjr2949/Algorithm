import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean possible;
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0 ; i < M ; i++) {
            if (!visited[0][i] && map[0][i] == 0) {
                dfs(i, 0);
                if (possible) {
                    break;
                }
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }

    static void dfs(int x, int y) {
        if (y == N-1) {
            possible = true;
            return;
        }


        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] == 0) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }
}