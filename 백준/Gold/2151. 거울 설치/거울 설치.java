import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static char[][] map;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static int N, startX, startY, endX, endY, answer = 0;

    static class Light {
        int x, y, dir, cnt;

        public Light(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        map = new char[N][N];

        boolean firstDoor = false;

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();

            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#') {
                    if (!firstDoor) {
                        startX = j;
                        startY = i;
                        firstDoor = true;
                    } else {
                        endX = j;
                        endY = i;
                    }
                }
            }
        }

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        PriorityQueue<Light> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);

        PQ.add(new Light(startX, startY, 0, 0));
        PQ.add(new Light(startX, startY, 1, 0));
        PQ.add(new Light(startX, startY, 2, 0));
        PQ.add(new Light(startX, startY, 3, 0));

        boolean[][][] visited = new boolean[N][N][4];

        while (!PQ.isEmpty()) {
            Light light = PQ.poll();

            int x = light.x;
            int y = light.y;
            int dir = light.dir;
            int cnt = light.cnt;

            if (x == endX && y == endY) {
                answer = cnt;
                return;
            }

            if (!visited[y][x][dir]) {
                visited[y][x][dir] = true;
            }

            int dx = x + nx[dir];
            int dy = y + ny[dir];

            if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dy][dx][dir] && map[dy][dx] != '*') {
                // 거울을 설치할 수 있는 경우
                if (map[dy][dx] == '!') {
                    // '/' 모양의 거울을 설치한 경우
                    int ddir1 = 0;

                    // '\' 모양의 거울을 설치한 경우
                    int ddir2 = 0;

                    if (dir == 0) {
                        ddir1 = 1;
                        ddir2 = 3;
                    } else if (dir == 1) {
                        ddir1 = 0;
                        ddir2 = 2;
                    } else if (dir == 2) {
                        ddir1 = 3;
                        ddir2 = 1;
                    } else {
                        ddir1 = 2;
                        ddir2 = 0;
                    }

                    PQ.add(new Light(dx, dy, ddir1, cnt + 1));
                    PQ.add(new Light(dx, dy, ddir2, cnt + 1));

                }

                // 거울을 설치하지 않는 경우
                PQ.add(new Light(dx, dy, dir, cnt));
            }
        }

    }
}