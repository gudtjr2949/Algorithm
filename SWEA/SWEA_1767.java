package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// SWEA 1767 : 프로세서 연결하기
public class SWEA_1767 {

    static int N;
    static int connected_cnt, connected_line;
    static ArrayList<Point> cpu;
    static int[] nx = {0, 0, 1, 0, -1};
    static int[] ny = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0; T < Test; T++) {
            N = Integer.parseInt(bf.readLine());

            boolean[][] connected = new boolean[N][N];

            int[][] map = new int[N][N];

            cpu = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] == 1 && (i > 0 && i < N - 1) && (j > 0 && j < N - 1)) {
                        cpu.add(new Point(j, i));
                    } else if (map[i][j] == 1) {
                        connected[i][j] = true;
                    }
                }
            }

            connected_cnt = Integer.MIN_VALUE;
            connected_line = Integer.MAX_VALUE;

            perm(0, new int[cpu.size()], map, connected);

            sb.append("#").append(T + 1).append(" ").append(connected_line).append("\n");
        }

        System.out.println(sb);
    }

    private static void perm(int idx, int[] input, int[][] map, boolean[][] connected) {
        if (idx == input.length) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            input[idx] = i;

            // 연결 안하면? 그냥 다음으로 넘어가기
            if (input[idx] == 0) {
                perm(idx + 1, input, map, connected);
            }
            // 연결이 가능한 경우라면 연결
            else if (check(idx, input, map)) {
                map = printLine(idx, input, map, connected);
                perm(idx + 1, input, map, connected);

                // 원복코드
                map = restoration(idx, input, map);
                connected[cpu.get(idx).y][cpu.get(idx).x] = false;
            }
        }
    }

    private static boolean check(int idx, int[] input, int[][] map) {
        int dir = input[idx];
        int dx = cpu.get(idx).x;
        int dy = cpu.get(idx).y;

        for (int i = 0; i < N; i++) {
            dx += nx[dir];
            dy += ny[dir];

            // 범위를 벗어났으면 끝까지 잘 갔다는 의미 -> 라인 설치 가능
            if (dx >= N || dx < 0 || dy >= N || dy < 0) {
                break;
            } else if (map[dy][dx] == 1 || map[dy][dx] == -1) {
                return false;
            }
        }

        return true;
    }

    private static int[][] printLine(int idx, int[] input, int[][] map, boolean[][] connected) {
        int dir = input[idx];
        int dx = cpu.get(idx).x;
        int dy = cpu.get(idx).y;

        connected[dy][dx] = true;

        for (int i = 0; i < N; i++) {
            dx += nx[dir];
            dy += ny[dir];

            // 범위를 벗어났으면 끝까지 잘 갔다는 의미
            if (dx >= N || dx < 0 || dy >= N || dy < 0) {
                // 라인 설치 가능
                break;
            }

            map[dy][dx] = -1;
        }

        CountCpu(map, connected);

        return map;
    }

    private static int[][] restoration(int idx, int[] input, int[][] map) {
        int dir = input[idx];
        int dx = cpu.get(idx).x;
        int dy = cpu.get(idx).y;

        for (int i = 0; i < N; i++) {
            dx += nx[dir];
            dy += ny[dir];

            // 범위를 벗어났으면 끝까지 잘 갔다는 의미
            if (dx >= N || dx < 0 || dy >= N || dy < 0) {
                break;
            }

            map[dy][dx] = 0;
        }

        return map;
    }

    private static void CountCpu(int[][] map, boolean[][] connected) {
        int cpu_cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (connected[i][j]){
                    cpu_cnt++;
                }
            }
        }

        int line_cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    line_cnt++;
                }
            }
        }

        if (connected_cnt < cpu_cnt) {
            connected_cnt = cpu_cnt;
            connected_line = line_cnt;
        }
        else if (connected_cnt == cpu_cnt) {
            connected_line = Math.min(connected_line, line_cnt);
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}