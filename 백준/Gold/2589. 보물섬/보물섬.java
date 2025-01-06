import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int L, W, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
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
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[L][W];

        for (int i = 0 ; i < L ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < W ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0 ; i < L ; i++) {
            for (int j = 0 ; j < W ; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[L][W];
                    bfs(j, i);
                }
            }
        }

        System.out.println(answer);
    }


    // 이동이 가능하고, 두 좌표간 이동거리가 최대인 경우
    static void bfs(int x, int y) {
        Queue<Node> Q = new LinkedList<>();

        Q.add(new Node(x, y, 0));
        visited[y][x] = true;

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            answer = Math.max(answer, now.cnt);

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < W && ny >= 0 && ny < L && map[ny][nx] == 'L' && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }
    }
}