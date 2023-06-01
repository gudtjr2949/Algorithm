package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 17144 : 미세먼지 안녕!
public class BOJ_17144 {

    static int R, C, T, answer;
    static int[][] map;
    static int[] nx = {1, 0, -1, 0};
    static int[] ny = {0, -1, 0, 1};
    static int[] nx_2 = {1, 0, -1, 0};
    static int[] ny_2 = {0, 1, 0, -1};
    static Point[] Air;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        Air = new Point[2];
        answer = 0;

        int idx = 0;

        for (int i = 0 ; i < R ; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    Air[idx] = new Point(j, i);
                    idx++;
                }
            }
        }

        for (int i = 0 ; i < T ; i++) {
            Spread(new int[R][C]);
            TurnOnAir(new int[R][C]);
        }

        count();

        System.out.println(answer);
    }

    private static void count() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] != -1) {
                    answer += map[i][j];
                }
            }
        }
    }

    // 미세먼지 확산시키기
    private static void Spread(int[][] tmp) {
        tmp[Air[0].y][Air[0].x] = -1;
        tmp[Air[1].y][Air[1].x] = -1;

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] > 0) { // 먼지가 있으면
                    int cnt = 0;

                    for (int q = 0 ; q < 4 ; q++) {
                        int dx = j + nx[q];
                        int dy = i + ny[q];

                        if (!(dx >= C || dx < 0 || dy >= R || dy < 0) && map[dy][dx] != -1) {
                            tmp[dy][dx] += map[i][j] / 5;
                            cnt++;
                        }
                    }

                    tmp[i][j] += map[i][j] - (map[i][j] / 5) * cnt;
                }
            }
        }

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    // 공기청정기 작동시키기
    private static void TurnOnAir(int[][] copy_map) {
        copy_map = Copy(copy_map);

        int dx = Air[0].x + 1;
        int dy = Air[0].y;
        copy_map[dy][dx] = 0;

        int origin_x = dx;
        int origin_y = dy;

        for (int i = 0 ; i < 4 ; i++) {
            while (true) {
                dx += nx[i];
                dy += ny[i];

                if (dx >= C || dx < 0 || dy >= R || dy < 0) {
                    dy -= ny[i];
                    dx -= nx[i];
                    break;
                }
                if (map[dy][dx] == -1) {
                    break;
                }

                copy_map[dy][dx] = map[origin_y][origin_x];

                origin_x = dx;
                origin_y = dy;
            }
        }

        dx = Air[1].x + 1;
        dy = Air[1].y;
        copy_map[dy][dx] = 0;

        origin_x = dx;
        origin_y = dy;

        for (int i = 0 ; i < 4 ; i++) {
            while (true) {
                dx += nx_2[i];
                dy += ny_2[i];

                if (dx >= C || dx < 0 || dy >= R || dy < 0) {
                    dy -= ny_2[i];
                    dx -= nx_2[i];
                    break;
                }

                if (map[dy][dx] == -1) {
                    break;
                }

                copy_map[dy][dx] = map[origin_y][origin_x];

                origin_x = dx;
                origin_y = dy;
            }
        }

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = copy_map[i][j];
            }
        }
    }

    private static int[][] Copy(int[][] copy_map) {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                copy_map[i][j] = map[i][j];
            }
        }
        return copy_map;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
