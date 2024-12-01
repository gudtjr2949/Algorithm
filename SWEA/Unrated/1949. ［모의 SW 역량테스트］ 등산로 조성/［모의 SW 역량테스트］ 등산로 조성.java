import java.util.*;
import java.io.*;

class Solution {

    static int N, K, maxCnt;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];

            int max = 0;
            maxCnt = 0;

            for (int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (map[i][j] == max) {
                        visited[i][j] = true;
                        dfs(j, i, 1, true);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y, int cnt, boolean cut) {
        maxCnt = Math.max(maxCnt, cnt);

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx])) continue;

            if (map[ny][nx] < map[y][x]) {
                visited[ny][nx] = true;
                dfs(nx, ny, cnt+1, cut);
                visited[ny][nx] = false;
            } else if (cut) {
                for (int j = 1 ; j <= K ; j++) {
                    if (map[ny][nx] - j < map[y][x]) {
                        map[ny][nx] -= j;
                        dfs(nx, ny, cnt+1, false);
                        map[ny][nx] += j;
                    }
                }
            }
        }
    }
}