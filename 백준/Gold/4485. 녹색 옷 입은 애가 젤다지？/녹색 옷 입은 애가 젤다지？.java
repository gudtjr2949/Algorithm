import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, min;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static class Node {
        int x, y, deducted;
        public Node(int x, int y, int deducted) {
            this.x = x;
            this.y = y;
            this.deducted = deducted;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while (true) {
            N = Integer.parseInt(bf.readLine());
            if (N == 0) break;

            map = new int[N][N];
            min = 100_000_000;

            for (int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve();

            sb.append("Problem ").append(idx++).append(": ").append(min).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.deducted - o2.deducted);
        int[][] dp = new int[N][N];

        for (int i = 0 ; i < N ; i++) Arrays.fill(dp[i], 100_00_00);
        PQ.add(new Node(0, 0, map[0][0]));
        dp[0][0] = map[0][0];

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == N-1 && now.y == N-1) {
                min = now.deducted;
                return;
            }


            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && dp[ny][nx] > dp[now.y][now.x] + map[ny][nx]) {
                    dp[ny][nx] = dp[now.y][now.x] + map[ny][nx];
                    PQ.add(new Node(nx, ny, dp[ny][nx]));
                }
            }
        }
    }
}