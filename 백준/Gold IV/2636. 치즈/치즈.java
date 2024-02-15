import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] ny = {-1, 0, 1, 0}, nx = {0, 1, 0, -1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
//                if (i == 0 || j == 0 || i == N-1 || j == M-1) {
//                    map[i][j] = 2;
//                }
            }
        }

        int time = 0;
        int cnt = 0;

        while (checkCheese() > 0){
            visited = new boolean[N][M];
            cnt = checkCheese();
            bfs(); // 외부 공기와 맞닿아 있는 치즈 녹이기
            time++;
        }
        
        System.out.println(time);
        System.out.println(cnt);
    }

    // Q엔 내부 공기였다가 외부 공기로 변할 좌표를 넣어야함
    static void bfs() {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(0, 0));
        visited[0][0] = true;

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            for (int i = 0; i < 4; i++) {
                int dx = p.x + nx[i];
                int dy = p.y + ny[i];
                if (dx >= 0 && dx < M && dy >= 0 && dy < N && !visited[dy][dx]) {
                    visited[dy][dx] = true;
                    if (map[dy][dx] == 0) {
                        Q.add(new Point(dx, dy));
                    } else if (map[dy][dx] == 1) {
                        map[dy][dx] = 0;
                    }
                }
            }
        }
    }

    private static int checkCheese() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}