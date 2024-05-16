import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, maxLength, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0 , 1 , 0};
    static int[][] map;
    static boolean[][] visited;
    static class Node {
        int x, y, len;

        public Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0) bfs(j, i);
            }
        }

        System.out.println(answer);
    }

    static void bfs(int startX, int startY) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(startX, startY, 0));
        boolean[][] visited = new boolean[N][M];
        visited[startY][startX] = true;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            if (maxLength < now.len) {
                maxLength = now.len;
                answer = map[startY][startX] + map[now.y][now.x];
            } else if (maxLength == now.len) {
                answer = Math.max(answer, map[startY][startX] + map[now.y][now.x]);
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] != 0) {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, now.len+1));
                }
            }
        }
    }
}