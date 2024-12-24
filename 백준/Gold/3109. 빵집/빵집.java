import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer;
    static int[] dx = {1, 1, 1}, dy = {-1, 0, 1};
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0 ; i < R ; i++) {
            dfs(0, i);
        }

        System.out.println(answer);
    }

    static boolean dfs(int x, int y) {
        for (int i = 0 ; i < 3 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && map[ny][nx] == '.') {
                if (nx == C-1) {
                    answer++;
                    return true;
                }

                map[ny][nx] = '-';

                if (dfs(nx, ny)) return true;
            }
        }

        return false;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }
}