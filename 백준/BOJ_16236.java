package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 16236 : 아기 상어
public class BOJ_16236 {

    static int N;
    static int[][] map;
    static Shark shark;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(j, i, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        Queue<Point> Q = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();

        boolean[][] visited = new boolean[N][N];

        Q.add(new Point(shark.x, shark.y, shark.size, 0));

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();

                // 상어가 먹을 수 있는 물고기를 만났을 때
                if (p.size < shark.size && p.size > 0) {
                    // 근데 같은 거리에 있는 물고기가 또 있을 수도 있으니까 일단 저장하기
                    list.add(p);
                }

                visited[p.y][p.x] = true;

                for (int j = 0; j < 4; j++) {
                    int dx = p.x + nx[j];
                    int dy = p.y + ny[j];

                    if (!(dx >= N || dx < 0 || dy >= N || dy < 0) && !visited[dy][dx] && map[dy][dx] <= shark.size) {
                        visited[dy][dx] = true;
                        Q.add(new Point(dx, dy, map[dy][dx], p.depth + 1));
                    }
                }
            }

            // 먹을 수 있는 물고기를 만났다는 것
            if (list.size() > 0) {
                Collections.sort(list);
                shark.cnt++;
                shark.x = list.get(0).x;
                shark.y = list.get(0).y;
                answer += list.get(0).depth;

                if (shark.cnt == shark.size) {
                    shark.cnt = 0;
                    shark.size++;
                }

                list = new ArrayList<>();
                visited = new boolean[N][N];
                Q = new LinkedList<>();
                Q.add(new Point(shark.x, shark.y, shark.size, 0));
                map[shark.y][shark.x] = 0;
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x, y, size, depth;

        public Point(int x, int y, int size, int depth) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point o) {
            if (o.y == this.y) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }

    static class Shark {
        int x, y, size, cnt;

        public Shark(int x, int y, int size, int cnt) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.cnt = cnt;
        }
    }
}