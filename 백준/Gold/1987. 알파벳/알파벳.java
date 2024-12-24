import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int R, C, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static Set<Character> set;

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

        set = new HashSet<>();
        set.add(map[0][0]);

        dfs(0, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt) {
        answer = Math.max(answer, cnt);

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !set.contains(map[ny][nx])) {
                set.add(map[ny][nx]);
                dfs(nx, ny, cnt+1);
                set.remove(map[ny][nx]);
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }
}