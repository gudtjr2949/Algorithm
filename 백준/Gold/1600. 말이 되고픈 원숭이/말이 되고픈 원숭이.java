import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int H, W, K, answer = -1;
    static int[] horseDx = {-2, -1, 1, 2, 2, 1, -1, -2}, horseDy = {-1, -2, -2, -1, 1, 2, 2, 1}, monkeyDx = {0, 1, 0, -1}, monkeyDy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][][] visited;
    static class Node {
        int x, y, cnt, movingHorse;
        public Node(int x, int y, int cnt, int movingHorse) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.movingHorse = movingHorse;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        PQ.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.y == H-1 && now.x == W-1) {
                answer = now.cnt;
                return;
            }

            if (now.movingHorse < K) {
                for (int i = 0 ; i < 8 ; i++) {
                    int nx = now.x + horseDx[i];
                    int ny = now.y + horseDy[i];

                    if (isRange(nx, ny) && !visited[ny][nx][now.movingHorse+1] && map[ny][nx] == 0) {
                        visited[ny][nx][now.movingHorse+1] = true;
                        PQ.add(new Node(nx, ny, now.cnt+1, now.movingHorse+1));
                    }
                }
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + monkeyDx[i];
                int ny = now.y + monkeyDy[i];

                if (isRange(nx, ny) && !visited[ny][nx][now.movingHorse] && map[ny][nx] == 0) {
                    visited[ny][nx][now.movingHorse] = true;
                    PQ.add(new Node(nx, ny, now.cnt+1, now.movingHorse));
                }
            }

        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < H ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < W ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void init() {
        map = new int[H][W];
        visited = new boolean[H][W][K+1];
    }
}