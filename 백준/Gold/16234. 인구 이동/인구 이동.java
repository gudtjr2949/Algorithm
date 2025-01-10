import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R;
    static boolean flag;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static Nation[] nations;
    static int[][] map, nationsIdx;
    static boolean[][] visited;
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Nation {
        int sum, cnt;
        public Nation(int sum, int cnt) {
            this.sum = sum;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int t = 0 ; t <= 2000 ; t++) {
            visited = new boolean[N][N];
            nations = new Nation[(N*N)+1];
            nationsIdx = new int[N][N];
            flag = false;

            int idx = 1;
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (!visited[i][j]) {
                        bfs(j, i, idx++);
                    }
                }
            }

            if (!flag) {
                answer = t;
                break;
            }

            mergeNation();
        }

        System.out.println(answer);
    }

    static void mergeNation() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (nationsIdx[i][j] != 0) {
                    map[i][j] = nations[nationsIdx[i][j]].sum / nations[nationsIdx[i][j]].cnt;
                }
            }
        }
    }

    static void bfs(int x, int y, int idx) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(x, y));
        nationsIdx[y][x] = idx;
        visited[y][x] = true;
        int sum = map[y][x];
        int cnt = 1;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) continue;

                int diff = Math.abs(map[now.y][now.x] - map[ny][nx]);

                if (!visited[ny][nx] && diff >= L && diff <= R) {
                    visited[ny][nx] = true;
                    if (!flag) flag = true;
                    sum += map[ny][nx];
                    cnt++;
                    nationsIdx[ny][nx] = idx;
                    Q.add(new Node(nx, ny));
                }
            }
        }

        nations[idx] = new Nation(sum, cnt);
    }

    static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N;
    }
}