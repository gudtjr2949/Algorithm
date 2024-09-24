import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visited = new boolean[H][W];
            int cnt = 0;

            for (int i = 0 ; i < H ; i++) {
                String s = bf.readLine();
                for (int j = 0 ; j < W ; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            for (int i = 0 ; i < H ; i++) {
                for (int j = 0 ; j < W ; j++) {
                    if (map[i][j] == '#' && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(j, i);
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] == '#' && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }
}