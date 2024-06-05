import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static boolean[][] visited;
    static List<Cross> list;
    static char[][] map;
    static class Cross {
        int x, y, s;

        public Cross(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        map = new char[N][M];
        visited = new boolean[N][M];
        K = 0;

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == '*') {
                    solve(j, i);
                }
            }
        }

        boolean possible = true;

        Loop:
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] == '*' && !visited[i][j]) {
                    possible = false;
                    break Loop;
                }
            }
        }

        if (!possible) System.out.println(-1);
        else {
            System.out.println(list.size());
            for (Cross cross : list) {
                System.out.println(cross.y + " " + cross.x + " " + cross.s);
            }
        }
    }

    private static void solve(int x, int y) {

        int size = 0;

        for (int i = 1 ; ; i++) {
            if (x - i < 0 || x + i >= M || y - i < 0 || y + i >= N) break;

            if (map[y-i][x] == '*' && map[y][x+i] == '*' && map[y+i][x] == '*' && map[y][x-i] == '*') {
                size = i;
            } else {
                break;
            }
        }

        if (size > 0) {
            for (int i = 1 ; i <= size ; i++) {
                list.add(new Cross(x + 1, y + 1, i));
                visited[y][x] = true;
                for (int j = 1; j <= i ; j++) {
                    visited[y - j][x] = true;
                    visited[y][x + j] = true;
                    visited[y + j][x] = true;
                    visited[y][x - j] = true;
                }
            }
        }
    }
}