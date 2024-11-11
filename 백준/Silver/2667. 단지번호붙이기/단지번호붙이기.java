import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N, cnt;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int answer = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    cnt = 0;
                    visited[i][j] = true;
                    dfs(j, i);
                    answer++;
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);

        System.out.println(answer);
        for (int num : list) {
            System.out.println(num);
        }
    }

    static void dfs(int x, int y) {

        cnt++;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[ny][nx] == 1 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }
}