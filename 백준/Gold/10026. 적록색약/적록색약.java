import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new char[2][N][N];
        visited = new boolean[2][N][N];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                char c = s.charAt(j) == 'G' ? 'R' : s.charAt(j);
                map[0][i][j] = s.charAt(j);
                map[1][i][j] = c;
            }
        }

        int[] answer = new int[2];

        // 적록색약 X
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[0][i][j]) {
                    dfs(j, i, 0);
                    answer[0]++;
                }

                if (!visited[1][i][j]) {
                    dfs(j, i, 1);
                    answer[1]++;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

    static void dfs(int x, int y, int idx) {
        visited[idx][y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[idx][ny][nx] && map[idx][y][x] == map[idx][ny][nx]) {
                dfs(nx, ny, idx);
            }
        }
    }
}