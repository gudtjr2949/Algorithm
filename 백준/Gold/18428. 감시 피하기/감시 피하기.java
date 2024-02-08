import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Point> student = new ArrayList<>();
    static int N;
    static char[][] map;
    static int[] nx = {0, 0, 1, -1};
    static int[] ny = {1, -1, 0, 0};
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new char[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'S'){
                    student.add(new Point(i, j));
                }
            }
        }

        dfs(0);

        System.out.println("NO");
    }

    static void dfs(int wall) {
        if (wall == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(wall + 1);
                    map[i][j] = 'X';
                }
            }
        }
    }

    static void bfs() {
        Queue<Point> Q = new LinkedList<>();
        char[][] copyMap = new char[N][N];
        boolean[][] check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copyMap[i][j] == 'T') {
                    Q.add(new Point(i, j));
                    check[i][j] = true;
                }
            }
        }

        while (!Q.isEmpty()) {
            Point now = Q.poll();
            int x = now.x;
            int y = now.y;

            for (int k = 0; k < 4; k++) {
                int dx = x + nx[k];
                int dy = y + ny[k];

                while(0 <= dx && dx < N && 0 <= dy && dy < N) {
                    if (copyMap[dx][dy] != 'O') {
                        check[dx][dy] = true;
                        dx += nx[k];
                        dy += ny[k];
                    }else{
                        break;
                    }
                }
            }
        }

        if(catchStudent(check)){
            System.out.println("YES");
            System.exit(0);
        }
    }

    static boolean catchStudent(boolean[][] check) {
        for (Point point : student) {
            if (check[point.x][point.y] == true) {
                return false;
            }
        }
        return true;
    }
}