import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] answer;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0, 1, 0};
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        visited = new boolean[M][N];
        answer = new int[2];

        for (int i = 0 ; i < M ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0 ; i < M ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (!visited[i][j]) bfs(j, i);
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

    private static void bfs(int startX, int startY) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(startX, startY));
        visited[startY][startX] = true;
        int cnt = 1;

        while (!Q.isEmpty()) {
            Node now = Q.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[ny][nx] && map[ny][nx] == map[startY][startX]) {
                    visited[ny][nx] = true;
                    cnt++;
                    Q.add(new Node(nx, ny));
                }
            }
        }

        int idx = map[startY][startX] == 'W' ? 0 : 1;

        answer[idx] += cnt*cnt;
    }
}