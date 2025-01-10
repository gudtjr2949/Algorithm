import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = 10001;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;
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
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        int idx = 1;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    findCountry(j, i, idx++);
                }
            }
        }

        for (int i = 1 ; i < idx ; i++) {
            findNearCountry(i);
        }

        System.out.println(answer);
    }

    static void findNearCountry(int idx) {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        visited = new boolean[N][N];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j] == idx) {
                    for (int k = 0 ; k < 4 ; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];

                        if (isRange(nx, ny) && map[ny][nx] == 0) {
                            visited[ny][nx] = true;
                            PQ.add(new Node(nx, ny, 0));
                        }
                    }
                }
            }
        }

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (map[now.y][now.x] != 0) {
                answer = Math.min(answer, now.cnt);
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && !visited[ny][nx]) {
                    if (map[ny][nx] != idx) {
                        visited[ny][nx] = true;
                        PQ.add(new Node(nx, ny, now.cnt+1));
                    }
                }
            }
        }
    }

    static void findCountry(int x, int y, int idx) {
        Queue<Node> Q = new LinkedList<>();

        Q.add(new Node(x, y, 0));
        map[y][x] = idx;
        visited[y][x] = true;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    map[ny][nx] = idx;
                    Q.add(new Node(nx, ny, 0));
                }
            }
        }
    }

    static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N;
    }
}