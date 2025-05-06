import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, startX, startY, answer;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        input();

        visited[startY][startX] = true;
        dfs(startX, startY);

        if (answer == 0) System.out.println("TT");
        else System.out.println(answer);
    }

    static void dfs(int x, int y) {
        if (map[y][x] == 'P') answer++;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && map[ny][nx] != 'X' && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'I') {
                    startX = j;
                    startY = i;
                }
            }
        }
    }

    static void init() {
        map = new char[N][M];
        visited = new boolean[N][M];
    }
}