import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map, dist;
    static int[][] dx = {{1, 0}, {1}, {0}}, dy = {{0, 1}, {0}, {1}};
    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];
        dist = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijk();

        System.out.println(dist[n-1][n-1]);
    }

    static void dijk() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });
        PQ.add(new Node(0, 0, 0));

        boolean[][] visited = new boolean[n][n];

        for (int i = 0 ; i < n ; i++)
            Arrays.fill(dist[i], 1_000_000);

        dist[0][0] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (visited[now.y][now.x]) continue;

            visited[now.y][now.x] = true;

            if (now.x == n-1 && now.y == n-1) {
                return;
            }

            int dir = findDir(now.x, now.y);

            for (int i = 0 ; i < dx[dir].length ; i++) {
                int nx = now.x + dx[dir][i];
                int ny = now.y + dy[dir][i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[ny][nx]) {
                    int nCnt = (map[ny][nx] < map[now.y][now.x]) ? 0 : map[ny][nx] - map[now.y][now.x] + 1;
                    if (dist[ny][nx] > dist[now.y][now.x] + nCnt) {
                        dist[ny][nx] = dist[now.y][now.x] + nCnt;
                        PQ.add(new Node(nx, ny, now.cnt + nCnt));
                    }
                }
            }

        }
    }

    static int findDir(int x, int y) {
        if (y == n-1) return 1;
        else if (x == n-1) return 2;
        else return 0;
    }
}