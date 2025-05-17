import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, MAX = 2501, answer = MAX;
    static int[] input, dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map, dp;
    static boolean[][] visited;
    static List<Node> list;
    static class Node {
        int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        if (answer == MAX) answer = -1;
        System.out.println(answer);
    }

    static void solve() {
        dfs(0, 0);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            spreadVirus();
            return;
        }

        for (int i = cur ; i < list.size() ; i++) {
            input[idx] = i;
            dfs(idx+1, i+1);
        }
    }
    static void spreadVirus() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);

        dp = new int[N][N];
        for (int i = 0 ; i < N ; i++) Arrays.fill(dp[i], MAX);

        visited = new boolean[N][N];
        for (int i = 0 ; i < M ; i++) {
            Node now = list.get(input[i]);
            PQ.add(new Node(now.x, now.y, 0));
            dp[now.y][now.x] = 0;
            visited[now.y][now.x] = true;
        }

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && map[ny][nx] != 1 && !visited[ny][nx] && dp[ny][nx] > now.time+1) {
                    visited[ny][nx] = true;
                    dp[ny][nx] = now.time+1;
                    PQ.add(new Node(nx, ny, dp[ny][nx]));
                }
            }
        }

        findMaxTime();
    }

    private static void findMaxTime() {
        int completeTime = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j] != 0) continue;

                completeTime = Math.max(completeTime, dp[i][j]);
            }
        }

        answer = Math.min(answer, completeTime);
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) list.add(new Node(j, i, 0));
            }
        }
    }

    static void init() {
        list = new ArrayList<>();
        input = new int[M];
        map = new int[N][N];
    }
}