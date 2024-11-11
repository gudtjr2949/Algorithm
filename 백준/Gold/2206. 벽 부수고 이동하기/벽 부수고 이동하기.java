import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][][] visited;
    static class Node {
        int x, y, destory, cnt;
        public Node(int x, int y, int destory, int cnt) {
            this.x = x;
            this.y = y;
            this.destory = destory;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        solve();

        if (answer == 0) answer = -1;

        System.out.println(answer);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);

        visited[0][0][0] = true;
        PQ.add(new Node(0, 0, 0, 1));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == M-1 && now.y == N-1) {
                answer = now.cnt;
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (map[ny][nx] == 0 && !visited[now.destory][ny][nx]) {
                        visited[now.destory][ny][nx] = true;
                        PQ.add(new Node(nx, ny, now.destory, now.cnt+1));
                    } else if (map[ny][nx] == 1 && now.destory == 0 && !visited[1][ny][nx]) {
                        visited[1][ny][nx] = true;
                        PQ.add(new Node(nx, ny, 1, now.cnt+1));
                    }
                }
            }
        }
    }
}