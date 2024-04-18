import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == 'L') {
                    answer = Math.max(answer, bfs(j, i));
                }
            }
        }

        System.out.println(answer);
    }

    // 이동 가능한 경로중 가장 멀리 있는 위치
    static int bfs(int x, int y) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(x, y, 0));
        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;

        int max = 0;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            max = Math.max(max, now.cnt);

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] == 'L') {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }

        return max;
    }
}