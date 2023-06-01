package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 5427 : 불
public class BOJ_5427 {

    static int w, h;
    static char[][] map;
    static int answer;
    static StringBuilder sb = new StringBuilder();
    static List<Point> fire;
    static Point sanggeun;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < N ; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fire = new ArrayList<>();

            answer = 0;

            map = new char[h][w];
            for (int i = 0 ; i < h ; i++) {
                String s = bf.readLine();
                for (int j = 0 ; j < w ; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == '*') {
                        fire.add(new Point(j, i, 0, '*'));
                    } else if (map[i][j] == '@') {
                        sanggeun = new Point(j, i, 0, '@');
                        map[i][j] = '.';
                    }
                }
            }

            bfs();

            if (answer == 0) {
                sb.append("IMPOSSIBLE").append("\n");
            } else {
                sb.append(answer).append("\n");
            }

        }

        System.out.println(sb);
    }

    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    private static void bfs() {
        Queue<Point> Q = new LinkedList<>();

        for (int i = 0 ; i < fire.size() ; i++) {
            Q.add(fire.get(i));
        }

        Q.add(sanggeun);

        boolean[][] visited = new boolean[h][w];

        while (!Q.isEmpty()) {

            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                Point p = Q.poll();

                int x = p.x;
                int y = p.y;
                int depth = p.depth;
                char value = p.value;

                visited[y][x] = true;

                for (int j = 0 ; j < 4 ; j++) {
                    int dx = x + nx[j];
                    int dy = y + ny[j];

                    if (value == '*') { // 불
                        if (dx >= 0 && dx < w && dy >= 0 && dy < h && map[dy][dx] != '#' &&!visited[dy][dx]) {
                            visited[dy][dx] = true;
                            map[dy][dx] = '#';
                            Q.add(new Point(dx, dy, depth + 1, value));
                        }
                    } else if (value == '@') { // 상근
                        if (dx >= w || dx < 0 || dy >= h || dy < 0) { // 탈출 성공
                            answer = depth+1;
                            return;
                        }

                        if (dx >= 0 && dx < w && dy >= 0 && dy < h && map[dy][dx] == '.' && !visited[dy][dx]) {
                            visited[dy][dx] = true;
                            Q.add(new Point(dx, dy, depth + 1, value));
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int x, y, depth;
        char value;

        public Point(int x, int y, int depth, char value) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    '}';
        }
    }
}
