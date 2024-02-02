import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N][N];

        int min = 100;
        int max = 1;

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(arr[i][j], min);
                max = Math.max(arr[i][j], max);
            }
        }

        int answer = 1;

        for (int i = min ; i < max ; i++) {
            check(i);
            int cnt = 0;

            for (int j = 0 ; j < N ; j++) {
                for (int q = 0 ; q < N ; q++) {
                    if (!visited[j][q]) {
                        dfs(q, j);
                        cnt++;
                    }
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y) {

        visited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dy][dx]) {
                visited[dy][dx] = true;
                dfs(dx, dy);
            }
        }
    }

    static void check(int rain) {
        visited = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (arr[i][j] <= rain) {
                    visited[i][j] = true;
                }
            }
        }
    }
}