import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static boolean findAnswer = false;
    static class Point implements Comparable<Point> {
        int x, y, destroy;

        public Point(int x, int y, int destroy) {
            this.x = x;
            this.y = y;
            this.destroy = destroy;
        }

        @Override
        public int compareTo(Point p) {
            return p.destroy - this.destroy;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int destroy = 0;

        while (true) {
            bfs(destroy);
            if (findAnswer) {
                System.out.println(destroy);
                break;
            }
            destroy++;
        }
    }

    static void bfs(int destroy) {
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        visited = new boolean[N][N];

        PQ.add(new Point(0, 0, destroy));
        visited[0][0] = true;

        while (!PQ.isEmpty()) {
            Point p = PQ.poll();

            if (p.x == N-1 && p.y == N-1) {
                findAnswer = true;
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx]) {
                    if (map[ny][nx] == 0 && p.destroy > 0) { // 흰 방 -> 검은 방
                        visited[ny][nx] = true;
                        PQ.add(new Point(nx, ny, p.destroy-1));
                    } else if (map[ny][nx] == 1) { // 그냥 흰 방으로 이동
                        visited[ny][nx] = true;
                        PQ.add(new Point(nx, ny, p.destroy));
                    }
                }
            }
        }
    }
}