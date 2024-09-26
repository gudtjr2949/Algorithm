import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int MAX = 1_000_000;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map, dist;
    static class Node {
        int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        map = new int[501][501];
        dist = new int[501][501];

        for (int i = 0 ; i <= 500 ; i++)
            Arrays.fill(dist[i], MAX);

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (x1 > x2) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            if (y1 > y2) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            makeMap(x1, y1, x2, y2, 1);
        }

        N = Integer.parseInt(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (x1 > x2) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            if (y1 > y2) {
                int tmp = y1;
                y1 = y2;
                y2 = tmp;
            }

            makeMap(x1, y1, x2, y2, 2);
        }

        solve();

        System.out.println(dist[500][500] == MAX ? -1 : dist[500][500]);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });

        dist[0][0] = 0;
        PQ.add(new Node(0, 0, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == 500 && now.y == 500) {
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx <= 500 && ny >= 0 && ny <= 500 && map[ny][nx] != 2 && dist[ny][nx] > now.dist + map[ny][nx]) {
                    dist[ny][nx] = now.dist + map[ny][nx];
                    PQ.add(new Node(nx, ny, dist[ny][nx]));
                }
            }
        }
    }

    private static void makeMap(int x1, int y1, int x2, int y2, int num) {
        for (int i = y1 ; i <= y2 ; i++) {
            for (int j = x1 ; j <= x2 ; j++) {
                map[i][j] = num;
            }
        }
    }
}