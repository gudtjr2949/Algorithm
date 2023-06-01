package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 14502 : 연구소
public class BOJ_14502 {

    static int N, M, answer;
    static int[][] map;
    static ArrayList<Point> list;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MIN_VALUE;

        list = new ArrayList<>();
        map = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    list.add(new Point(j, i));
                }
            }
        }

        dfs(0, 0, new Point[3]);

        System.out.println(answer);
    }

    private static void dfs(int idx, int cur, Point[] input) {
        if (idx == 3) {
            setWall(input);
            return;
        }

        for (int i = cur ; i < list.size() ; i++) {
            input[idx] = list.get(i);
            dfs(idx + 1, i + 1, input);
        }
    }

    private static void setWall(Point[] input) {
        int[][] copy = copyMap();
        
        // 벽 세우기
        for (int i = 0 ; i < 3 ; i++) {
            Point p = input[i];
            copy[p.y][p.x] = 1;
        }
        
        // 바이러스 퍼지게 하기
        spread(copy);
    }

    private static void spread(int[][] copy) {
        Queue<Point> Q = new LinkedList<>();

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (copy[i][j] == 2) {
                    Q.add(new Point(j, i));
                }
            }
        }

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int dx = p.x + nx[i];
                int dy = p.y + ny[i];

                if (!(dx >= M || dx < 0 || dy >= N || dy < 0) && copy[dy][dx] == 0) {
                    copy[dy][dx] = 2;
                    Q.add(new Point(dx, dy));
                }
            }
        }

        count(copy);
    }

    private static void count(int[][] copy) {
        int cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (copy[i][j] == 0) {
                    cnt++;
                }
            }
        }

        answer = Math.max(answer, cnt);
    }

    private static int[][] copyMap() {
        int[][] tmp = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
