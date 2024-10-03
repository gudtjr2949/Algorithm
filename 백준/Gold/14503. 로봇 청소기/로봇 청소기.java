import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static int[] back_dx = {0, -1, 0, 1}, back_dy = {1, 0, -1, 0}, front_dx = {0, 1, 0, -1}, front_dy = {-1, 0, 1, 0};
    static Node now;
    static class Node {
        int x, y, dir;

        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(bf.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        now = new Node(x, y, dir);

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (true) {
            if (map[now.y][now.x] == 0) {
                map[now.y][now.x] = 2;
            }

            if (!check()) {
                int nx = now.x + back_dx[now.dir];
                int ny = now.y + back_dy[now.dir];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] != 1) {
                    now.x = nx;
                    now.y = ny;
                    continue;
                } else {
                    break;
                }
            } else {
                for (int i = 0 ; i < 4 ; i++) {
                    now.dir--;
                    if (now.dir < 0) now.dir = 3;

                    int nx = now.x + front_dx[now.dir];
                    int ny = now.y + front_dy[now.dir];
                    if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 0) {
                        now.x = nx;
                        now.y = ny;
                        break;
                    }
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 2) answer++;
            }
        }
    }

    static boolean check() {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = now.x + front_dx[i];
            int ny = now.y + front_dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 0) {
                return true;
            }
        }

        return false;
    }
}