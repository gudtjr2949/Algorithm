import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, sheep, wolf;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        int livedSheep = 0;
        int livedWolf = 0;

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') livedSheep++;
                if (map[i][j] == 'v') livedWolf++;
            }
        }

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] != '#' && !visited[i][j]) {
                    sheep = 0;
                    wolf = 0;
                    dfs(j, i);

                    if (sheep > wolf) {
                        livedWolf -= wolf;
                    } else {
                        livedSheep -= sheep;
                    }
                }
            }
        }

        System.out.println(livedSheep + " " + livedWolf);
    }

    static void dfs(int x, int y) {
        if (map[y][x] == 'o') sheep++;
        if (map[y][x] == 'v') wolf++;

        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] != '#' && !visited[ny][nx]) {
                dfs(nx, ny);
            }
        }
    }
}