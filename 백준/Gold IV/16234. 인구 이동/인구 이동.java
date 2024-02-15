import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static boolean flag;
    static int[][] map;
    static boolean[][] visited;
    static int[] ny = {-1, 0, 1, 0}, nx = {0, 1, 0, -1};
    static class Country {
        int x, y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        while (true) {
            flag = false;
            visited = new boolean[N][N];

            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (!visited[i][j]) {
                        bfs(j, i);
                    }
                }
            }

            if (!flag) break;
            else cnt++;
        }

        System.out.println(cnt);
    }

    static void bfs(int x, int y) {
        Queue<Country> Q = new LinkedList<>();
        List<Country> list = new ArrayList<>();

        Q.add(new Country(x, y));
        list.add(new Country(x, y));

        visited[y][x] = true;
        int sum = map[y][x];

        while (!Q.isEmpty()) {
            Country c = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int dx = c.x + nx[i];
                int dy = c.y + ny[i];

                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dy][dx]) {
                    int diff = Math.abs(map[c.y][c.x] - map[dy][dx]);

                    if (diff >= L && diff <= R) {
                        sum += map[dy][dx];
                        flag = true;
                        visited[dy][dx] = true;
                        list.add(new Country(dx, dy));
                        Q.add(new Country(dx, dy));
                    }
                }
            }
        }

        if (flag) {
            int change = sum / list.size();
            for (int i = 0; i < list.size(); i++) {
                Country c = list.get(i);
                map[c.y][c.x] = change;
            }
        }
    }
}