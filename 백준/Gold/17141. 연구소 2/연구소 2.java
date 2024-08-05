import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer = 100_000;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[] visited;
    static List<Node> list = new ArrayList<>();
    static int[][] map;

    static class Node {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new Node(j, i, 0));
                }
            }
        }

        visited = new boolean[list.size()];

        dfs(0, 0, new Node[M]);

        System.out.println(answer == 100_000 ? -1 : answer);
    }

    static void dfs(int idx, int cur, Node[] input) {
        if (idx == M) {
//            System.out.println(Arrays.toString(input));
            int tmp = bfs(input);
            if (tmp != -1) {
                answer = Math.min(answer, tmp);
            }
            return;
        }

        for (int i = cur; i < list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = list.get(i);
                dfs(idx + 1, i + 1, input);
                visited[i] = false;
            }
        }
    }

    static int bfs(Node[] arr) {
        Queue<Node> Q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (Node node : arr) {
            Q.add(node);
            visited[node.y][node.x] = true;
        }

        int time = 0;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            time = Math.max(time, now.cnt);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] != 1) {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[i][j] && map[i][j] != 1) {
                    return -1;
                }
            }
        }

        return time;
    }
}