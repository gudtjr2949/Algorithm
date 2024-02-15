import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, empty = 0, answer = Integer.MAX_VALUE;
    static int[][] map;
    static List<Point> virusList = new ArrayList<>();
    static int[] ny = {-1, 0, 1, 0}, nx = {0, 1, 0, -1};

    static class Point {
        int x, y, time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    empty++;
                else if (map[i][j] == 2)
                    virusList.add(new Point(j, i, 0));
            }
        }

        // 어느 좌표에 활성화 바이러스를 둘 지 결정 -> 조합 (DFS)
        if (empty != 0) {
            dfs(0, 0, new int[M], new boolean[virusList.size()]);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        } else {
            System.out.println(0);
        }
    }

    static void dfs(int idx, int start, int[] input, boolean[] visited) {
        if (idx == M) {
            bfs(input);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = i;
                dfs(idx + 1, i + 1, input, visited);
                visited[i] = false;
            }
        }
    }

    static void bfs(int[] input) {
        Queue<Point> Q = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];
        int tmpEmpty = empty;

        for (int i = 0; i < M; i++) {
            Point p = virusList.get(input[i]);
            infected[p.y][p.x] = true;
            Q.add(new Point(p.x, p.y, p.time));
        }

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            int x = p.x;
            int y = p.y;
            int time = p.time;

            for (int i = 0; i < 4; i++) {
                int dx = x + nx[i];
                int dy = y + ny[i];


                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !infected[dy][dx] && map[dy][dx] != 1) {
                    if (map[dy][dx] == 0) { // 빈 칸
                        tmpEmpty--;
                    }

                    if (tmpEmpty == 0) { // 다 감염됨
                        answer = Math.min(answer, time + 1);
                        return;
                    }

                    infected[dy][dx] = true;
                    Q.add(new Point(dx, dy, time + 1));
                }
            }
        }
    }
}