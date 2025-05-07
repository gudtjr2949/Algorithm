import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer, MAX = 10_001;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map, dp;
    static class Node {
        int x, y, remove;
        public Node(int x, int y, int remove) {
            this.x = x;
            this.y = y;
            this.remove = remove;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.remove - o2.remove);

        PQ.add(new Node(0, 0, 0));
        dp[0][0] = 0;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == M-1 && now.y == N-1) {
                answer = now.remove;
                break;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) continue;

                if (map[ny][nx] == 0 && dp[ny][nx] > dp[now.y][now.x]) {
                    dp[ny][nx] = dp[now.y][now.x];
                    PQ.add(new Node(nx, ny, dp[ny][nx]));
                } else if (map[ny][nx] == 1 && dp[ny][nx] > dp[now.y][now.x] + 1) {
                    dp[ny][nx] = dp[now.y][now.x] + 1;
                    PQ.add(new Node(nx, ny, dp[ny][nx]));
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    static void init() {
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0 ; i < N ; i++) Arrays.fill(dp[i], MAX);
    }
}