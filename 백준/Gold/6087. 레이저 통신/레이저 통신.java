import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int W, H, startX = -1, startY = -1, endX = -1, endY = -1;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] dp;
    static char[][] map;
    static class Node implements Comparable<Node> {
        int x, y, cnt, dir;

        public Node(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    ", dir=" + dir +
                    '}';
        }

        @Override
        public int compareTo(Node n) {
            return this.cnt - n.cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dp = new int[H][W];
        map = new char[H][W];
        visited = new boolean[H][W];

        for (int i = 0 ; i < H ; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0 ; i < H ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < W ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                    if (startX == -1 && startY == -1) {
                        startX = j;
                        startY = i;
                    } else {
                        endX = j;
                        endY = i;
                    }
                }
            }
        }

        solve();

//        for (int i = 0 ; i < H ; i++) {
//            for (int j = 0 ; j < W ; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        System.out.println(dp[endY][endX]);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>();

        PQ.add(new Node(startX, startY, 0, 0));
        PQ.add(new Node(startX, startY, 0, 1));
        PQ.add(new Node(startX, startY, 0, 2));
        PQ.add(new Node(startX, startY, 0, 3));

        visited[startY][startX] = true;
        dp[startY][startX] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
//            System.out.println(now.toString());

//            if (now.x == endX && now.y == endY) return;

            if (!visited[now.y][now.x]) visited[now.y][now.x] = true;

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[ny][nx] != '*' && !visited[ny][nx]) {
                    if (now.dir != i && dp[ny][nx] > now.cnt + 1) { // 거울 설치
                        dp[ny][nx] = now.cnt + 1;
                        PQ.add(new Node(nx, ny, dp[ny][nx], i));
                    } else if (now.dir == i) { // 거울 설치 X
                        dp[ny][nx] = Math.min(dp[ny][nx], now.cnt);
                        PQ.add(new Node(nx, ny, dp[ny][nx], i));
                    }
                }
            }
        }
    }
}