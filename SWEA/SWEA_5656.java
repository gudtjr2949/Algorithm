package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 5656 : 벽돌 깨기
public class SWEA_5656 {

    static int N, H, W;
    static int[][] map;
    static int[][] copy_map;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0; T < Test; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;

            map = new int[H][W];
            copy_map = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy_map[i][j] = map[i][j];
                }
            }

            perm(0, new int[N]);

            sb.append("#").append(T+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    // 구슬 떨구는 모든 경우의 수 찾기 (중복순열)
    private static void perm(int idx, int[] input) {
        if (idx == input.length) {
            Bomb(input);
            return;
        }

        for (int i = 0; i < W; i++) {
            input[idx] = i;
            perm(idx + 1, input);
        }
    }

    private static void Bomb(int[] input) {
        // map 원상복구
        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W ; j++) {
                map[i][j] = copy_map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < H; j++) {
                if (map[j][input[i]] != 0) { // 벽돌 발견
                    bfs(input[i], j);
                    break;
                }
            }
        }

        count();
    }

    // bfs 사용해 주변에 터지는 벽돌 연속해서 터뜨리기
    private static void bfs(int x, int y) {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y));

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            int range = map[p.y][p.x]; // 폭발 범위
            map[p.y][p.x] = 0; // 폭발했으니까 0으로 바꿈
            int dx = p.x;
            int dy = p.y;

            for (int i = 0; i < range; i++) {
                for (int j = 0; j < 4; j++) {
                    dx = p.x + (i * nx[j]);
                    dy = p.y + (i * ny[j]);

                    if (!(dx >= W || dx < 0 || dy >= H || dy < 0) && map[dy][dx] != 0) {
                        Q.add(new Point(dx, dy));
                    }
                }
            }
        }

        down();
    }

    // 다 터뜨린 다음 비어있는 곳 채우기
    private static void down() {
        for (int i = 0 ; i < W ; i++) {
            for (int j = H-1 ; j >= 0 ; j--) {
                if (map[j][i] == 0) { // 빈칸 발견 -> 위에 벽돌이 있는지 찾기

                    for (int q = j-1 ; q >= 0 ; q--) {
                        if (map[q][i] != 0) { // 벽돌 찾았으면 밑으로 옮기기
                            map[j][i] = map[q][i];
                            map[q][i] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    // 벽돌 개수 세기
    private static void count() {
        int cnt = 0;

        for (int i = 0 ; i < H ; i++) {
            for (int j = 0 ; j < W ; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }

        answer = Math.min(answer, cnt);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
