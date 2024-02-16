import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, answer = 0;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static Point ji;
    static List<Point> fireList = new ArrayList<>();
    static class Point {
        int x, y, time; // checkJi 가 1이면 지훈, 0이면 불

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", time=" + time +
                    '}';
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'J') {
                    ji = new Point(j, i, 0);
                } else if (map[i][j] == 'F') {
                    fireList.add(new Point(j, i, 0));
                }
            }
        }

        bfs();

        if (answer == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(answer);
    }

    static void bfs() {
        Queue<Point> jiPQ = new LinkedList<>();
        Queue<Point> firePQ = new LinkedList<>();

        boolean[][] visited = new boolean[R][C];

        jiPQ.add(ji);
        visited[ji.y][ji.x] = true;

        for (int i = 0 ; i < fireList.size() ; i++) {
            firePQ.add(fireList.get(i));
            visited[fireList.get(i).y][fireList.get(i).x] = true;
        }

        while (!jiPQ.isEmpty()) {
            int size = jiPQ.size();
            for (int i = 0 ; i < size ; i++) {
                Point p = jiPQ.poll();

                // 지성이가 이동한 곳에 불이 옴
                if (map[p.y][p.x] == 'F') continue;

                for (int j = 0 ; j < 4 ; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    // 지훈이가 탈출할 수 있음
                    if (nx < 0 || nx >= C || ny < 0 || ny >= R) {
                        answer = p.time + 1;
                        return;
                    } else if (!visited[ny][nx] && map[ny][nx] == '.') {
                        visited[ny][nx] = true;
                        jiPQ.add(new Point(nx, ny, p.time + 1));
                    }
                }
            }

            size = firePQ.size();

            for (int i = 0 ; i < size ; i++) {
                Point p = firePQ.poll();

                for (int j = 0 ; j < 4 ; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];

                    // 불은 걍 벽을 제외하고 싹 다 이동해야 함
                    if (nx >= 0 && nx < C && ny >= 0 && ny < R && map[ny][nx] != '#' && map[ny][nx] != 'F') {
                        visited[ny][nx] = true;
                        map[ny][nx] = 'F';
                        firePQ.add(new Point(nx, ny, p.time+1));
                    }
                }
            }
        }
    }
}