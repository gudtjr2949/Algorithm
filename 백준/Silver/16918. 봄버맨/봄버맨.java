import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, N, time;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static void solve() {
        time = 1;

        while (time++ < N) {
            installBomb();
            bomb();
        }

        printAnswer();
    }


    static void bomb() {
        Queue<Node> Q = new LinkedList<>();

        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] != -1 && time - map[i][j] >= 3) {
                    map[i][j] = -1;
                    Q.add(new Node(j, i));
                }
            }
        }

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny)) map[ny][nx] = -1;
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    static void installBomb() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] == -1) map[i][j] = time;
            }
        }
    }

    static void printAnswer() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] != -1) System.out.print("O");
                else System.out.print(".");
            }
            System.out.println();
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C  ; j++) {
                if (s.charAt(j) == 'O') map[i][j] = 0;
                else map[i][j] = -1;
            }
        }
    }
}