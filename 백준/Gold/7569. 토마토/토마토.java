import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 1, 0, -1, 0, 0}, dy = {-1, 0, 1, 0, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    static class Tomato {
        int x, y, z;

        public Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < N ; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0 ; k < M ; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Tomato> Q = new LinkedList<>();
        visited = new boolean[H][N][M];

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < M ; k++) {
                    if (map[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        Q.add(new Tomato(k, j, i));
                    }
                }
            }
        }

        int day = 0;

        while (!Q.isEmpty()) {

            if (checkTomato()) break;
            else day++;

            int size = Q.size();
            for (int i = 0 ; i < size ; i++) {
                Tomato now = Q.poll();
                for (int j = 0 ; j < 6 ; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    int nz = now.z + dz[j];

                    if (nx >= 0 && nx < M && ny >= 0 && ny < N && nz >= 0 && nz < H && map[nz][ny][nx] == 0 && !visited[nz][ny][nx]) {
                        visited[nz][ny][nx] = true;
                        map[nz][ny][nx] = 1;
                        Q.add(new Tomato(nx, ny, nz));
                    }
                }
            }
        }

        if (!checkTomato()) day = -1;

        return day;
    }

    static boolean checkTomato() {
        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < M ; k++) {
                    if (map[i][j][k] == 0) return false;
                }
            }
        }

        return true;
    }
}