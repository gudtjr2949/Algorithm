import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static boolean isPeak;
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1}, dy = {-1, -1, -1, 0, 1, 1, 1, 0};
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
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    isPeak = true;
                    dfs(j, i);
                    if (isPeak) answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y) {
        for (int i = 0 ; i < 8 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (map[ny][nx] > map[y][x]) isPeak = false;

                if (visited[ny][nx] || map[y][x] != map[ny][nx] ) continue;

                // 봉우리가 될 수 있는 좌표 방문체크
                visited[ny][nx] = true;
                dfs(nx, ny);
            }
        }
    }

}