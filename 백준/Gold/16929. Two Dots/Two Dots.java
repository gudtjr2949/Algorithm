import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, startX, startY;
    static boolean cycle;
    static char[][] map;
    static boolean[][] visited;
    static int[] nx = {1, 0, -1, 0}, ny = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        Loop:
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                visited = new boolean[N][M];

                visited[i][j] = true;

                startX = j;
                startY = i;

                dfs(j, i, 0, 0);

                if (cycle) {
//                    System.out.println(i + " " + j);
//                    for (int q = 0 ; q < N ; q++) {
//                        for (int z = 0 ; z < M ; z++) {
//                            System.out.print(visited[q][z] + " ");
//                        }
//                        System.out.println();
//                    }
                    break Loop;
                }
            }
        }

        if (cycle) System.out.println("Yes");
        else System.out.println("No");
    }

    static void dfs(int x, int y, int dir, int changeDir) {
        if (cycle) return;

        for (int i = 0 ; i < 4 ; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx >= 0 && dx < M && dy >= 0 && dy < N && map[y][x] == map[dy][dx]) {
                if (!visited[dy][dx]) {
                    visited[dy][dx] = true;
                    if (i == dir) {
                        dfs(dx, dy, i, changeDir);
                    } else {
                        dfs(dx, dy, i, changeDir+1);
                    }
                } else {
                    if (dx == startX && dy == startY && changeDir >= 3) {
                        cycle = true;
                        return;
                    }
                }
            }
        }
    }
}