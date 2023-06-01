package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


// SWEA 7793 : 오! 나의 여신님
public class SWEA_7793 {

    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;

    static int[] nx = { 0, 1, 0, -1 };
    static int[] ny = { -1, 0, 1, 0 };

    static Point suyeon;
    static Point god;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            String nm = bf.readLine();
            String[] nm_arr = nm.split(" ");

            N = Integer.parseInt(nm_arr[0]);
            M = Integer.parseInt(nm_arr[1]);
            map = new char[N][M];
            visited = new boolean[N][M];
            answer = 0;

            for (int j = 0 ; j < N ; j++) {
                String s = bf.readLine();
                for (int q = 0 ; q < M ; q++) {
                    map[j][q] = s.charAt(q);
                    if (map[j][q] == 'S') {
                        suyeon = new Point(q, j);
                    }

                    if (map[j][q] == 'D') {
                        god = new Point(q, j);
                    }
                }
            }


            bfs(suyeon.x, suyeon.y);

            if (answer == 0) {
                sb.append("#").append(i+1).append(" ").append("GAME OVER ").append("\n");
            }
            else {
                sb.append("#").append(i+1).append(" ").append(answer).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(x, y, 0));

        while(!Q.isEmpty()) {
            int size = Q.size();
            devil();

            for (int i = 0 ; i < size ; i++) {
                Point p = Q.poll();

                if (map[p.y][p.x] == 'D') {
                    answer = p.depth;
                    return;
                }

                visited[p.y][p.x] = true;

                for (int j = 0 ; j < 4 ; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx] && (map[dy][dx] == 'D' || map[dy][dx] == '.')) {
                        visited[dy][dx] = true;
                        Q.add(new Point(dx, dy, p.depth+1));
                    }
                }
            }
        }
    }

    private static void devil() {
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == '*') {
                    for (int q = 0 ; q < 4 ; q++) {
                        int dx = j + nx[q];
                        int dy = i + ny[q];

                        if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && map[dy][dx] != 'D' && map[dy][dx] != 'X') {
                            list.add(new Point(dx, dy));
                        }
                    }
                }
            }
        }

        for (int i = 0 ; i < list.size() ; i++) {
            Point p = list.get(i);

            map[p.y][p.x] = '*';
        }
    }

    static class Point {
        int x;
        int y;
        int depth;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
