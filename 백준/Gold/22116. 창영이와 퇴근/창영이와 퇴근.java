import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int left = 0;
        int right = 1000000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (bfs(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean bfs(int mid) {
        Queue<Point> PQ = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        PQ.add(new Point(0, 0));
        visited[0][0] = true;

        while (!PQ.isEmpty()) {
            Point p = PQ.poll();

            if (p.x == N-1 && p.y == N-1) {
                return true;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx] && Math.abs(map[ny][nx]-map[p.y][p.x]) <= mid) {
                    visited[ny][nx] = true;
                    PQ.add(new Point(nx, ny));
                }
            }
        }

        return false;
    }
}