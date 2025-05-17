import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    static int N, L, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (checkRoad(0, i, 1)) {
                answer++;
            }
            else if (checkRoad(N-1, i, 3)) {
                answer++;
            }
        }

        for (int i = 0 ; i < N ; i++) {
            if (checkRoad(i, 0, 2)) {
                answer++;
            }
            else if (checkRoad(i, N-1, 0)) {
                answer++;
            }
        }
    }

    static boolean checkRoad(int nowX, int nowY, int dir) {
        int nextX = nowX;
        int nextY = nowY;
        visited = new boolean[N][N];

        while (true) {
            nowX = nextX;
            nowY = nextY;
            nextX += dx[dir];
            nextY += dy[dir];

            if (!isRange(nextX, nextY)) break;

            int heightDiff = calHeightDiff(nowX, nowY, nextX, nextY);

            if (heightDiff == 0) continue;
            else if (heightDiff == 1 && checkMakeSlope(nextX, nextY, dir)) {
                nextX += (dx[dir] * L) - dx[dir];
                nextY += (dy[dir] * L) - dy[dir];
            } else if (heightDiff == -1 && checkMakeSlope(nowX, nowY, (dir + 2) % 4)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    } // 0 -> 2, 1 -> 3, 2 -> 0, 3 -> 1

    static boolean checkMakeSlope(int x, int y, int dir) {
        int height = map[y][x];
        if (visited[y][x]) return false;
        visited[y][x] = true;

        for (int i = 0 ; i < L-1 ; i++) {
            x += dx[dir];
            y += dy[dir];
            if (!isRange(x, y) || visited[y][x] || map[y][x] != height) {
                return false;
            }
            visited[y][x] = true;
        }

        return true;
    }

    static int calHeightDiff(int x1, int y1, int x2, int y2) {
        return map[y1][x1] - map[y2][x2];
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        map = new int[N][N];
    }
}