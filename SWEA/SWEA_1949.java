package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// SWEA 1949 : 등산로 조성
public class SWEA_1949 {

    static int N;
    static int K;
    static int[][] map;
    static int max;
    static ArrayList<Point> list;

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    static boolean sign;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String nk = bf.readLine();
            String[] nk_arr = nk.split(" ");
            N = Integer.parseInt(nk_arr[0]);
            K = Integer.parseInt(nk_arr[1]);
            map = new int[N][N];
            sign = true;
            list = new ArrayList<>();
            answer = 0;

            max = 0;

            for (int j = 0; j < N; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                for (int q = 0; q < N; q++) {
                    map[j][q] = Integer.parseInt(s_arr[q]);
                    max = Math.max(max, map[j][q]);
                }
            }

            find_max();

            int[] input = new int[N*N];
            input[0] = max;

            for (int j = 0 ; j < list.size() ; j++) {
                dfs(list.get(j).x, list.get(j).y, 1, input, new boolean[N][N], true);
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int idx, int[] input, boolean[][] visited, boolean chance) {
        if (idx == input.length) {
            answer = Math.max(answer, idx);
            return;
        }

        visited[y][x] = true;
        answer = Math.max(answer, idx);

        for (int i = 0 ; i < 4 ; i++) {

            int dx = x + nx[i];
            int dy = y + ny[i];

            if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx]) {

                if (map[dy][dx] < input[idx-1]) { // 다음 이동할 경로가 현재 있는 위치의 값보다 작으면?
                    visited[dy][dx] = true;
                    input[idx] = map[dy][dx];
                    dfs(dx, dy, idx + 1, input, visited, chance);
                    visited[dy][dx] = false;
                }
                else if ((map[dy][dx] >= input[idx-1]) && chance) { // 다음 이동할 경로가 현재 있는 위치의 값보다 같거나 크지만 공사기회가 있어 최대 K만큼 자를 수 있는 경우
                    for (int j = map[dy][dx] - input[idx-1] + 1 ; j <= K ; j++) {
                        visited[dy][dx] = true;
                        input[idx] = map[dy][dx] - j;
                        chance = false;
                        dfs(dx, dy, idx + 1, input, visited, chance);
                        visited[dy][dx] = false;
                        chance = true;
                    }
                }

            }
        }
    }

    private static void find_max() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max == map[i][j]) {
                    list.add(new Point(j, i));
                }
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
