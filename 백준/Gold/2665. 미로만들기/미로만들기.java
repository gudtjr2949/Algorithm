import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map, dp;
    static class Node {
        int x, y, cnt;
        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        PQ.add(new Node(0, 0, 0));
        dp[0][0] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == N-1 && now.y == N-1) {
                answer = now.cnt;
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) continue;

                if (map[ny][nx] == 1 && dp[ny][nx] > now.cnt) {
                    dp[ny][nx] = now.cnt;
                    PQ.add(new Node(nx, ny, now.cnt));
                } else if (map[ny][nx] == 0 && dp[ny][nx] > now.cnt+1) {
                    dp[ny][nx] = now.cnt+1;
                    PQ.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    static void init() {
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0 ; i < N ; i++) Arrays.fill(dp[i], 2501);
    }
}