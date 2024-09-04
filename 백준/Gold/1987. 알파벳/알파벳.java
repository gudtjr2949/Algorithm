import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static boolean[] visited;
//    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[26];
//        visited = new boolean[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt) {
        answer = Math.max(answer, cnt);

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < C && ny >= 0 && ny < R && !visited[map[ny][nx] - 'A']) {
                visited[map[ny][nx] - 'A'] = true;
                dfs(nx, ny, cnt+1);
                visited[map[ny][nx] - 'A'] = false;
            }
        }
    }
}