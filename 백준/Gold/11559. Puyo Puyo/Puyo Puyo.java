import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean check;
    static char[][] map;

    static class Node {
        int x, y;
        char color;

        public Node(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        int answer = 0;

        for (int i = 0; i < 12; i++) {
            String s = bf.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        check = true;

        while (check) {
            check = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {
                        bfs(j, i);
                    }
                }
            }

            destroy();
            
            if (check) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(x, y, map[y][x]));
        boolean[][] visited = new boolean[12][6];
        visited[y][x] = true;

        List<Node> list = new ArrayList<>();

        while (!Q.isEmpty()) {
            Node now = Q.poll();
            list.add(now);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx >= 0 && nx < 6 && ny >= 0 && ny < 12
                        && !visited[ny][nx] && map[ny][nx] == map[now.y][now.x]) {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, map[ny][nx]));
                }
            }
        }

        if (list.size() >= 4) {
            check = true;

            for (Node now : list) {
                map[now.y][now.x] = '.';
            }
        }
    }

    static void destroy() {
        for (int i = 0 ; i < 6 ; i++) {
            Queue<Node> Q = new LinkedList<>();
            int idx = 11;

            for(int j = 11; j >= 0; j--) {
                if(map[j][i] != '.') {
                    Q.add(new Node(i, j, map[j][i]));
                    map[j][i] = '.';
                }
            }

            while(!Q.isEmpty()) {
                Node now = Q.poll();
                char color = now.color;
                map[idx][i] = color;
                idx--;
            }
        }
    }
}