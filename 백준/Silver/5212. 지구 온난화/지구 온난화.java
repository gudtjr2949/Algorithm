import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0 , 1 , 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        solve();

        deleteMap();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < R ; i++) {
            boolean flag = false;
            for (int j = 0 ; j < C ; j++) {
                if (!visited[i][j]) {
                    flag = true;
                    sb.append(map[i][j]);
                }
            }
            if (flag) sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int k = 0 ; k < 4 ; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        cnt += check(nx, ny);
                    }

                    if (cnt >= 3) visited[i][j] = true;
                }
            }
        }
    }

    static void deleteMap() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (visited[i][j]) map[i][j] = '.';
            }
        }

        visited = new boolean[R][C];

        // 위에서 부터
        Loop:
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] == '.') continue;
                else break Loop;
            }

            // 해당 열 출력 못하게
            for (int j = 0 ; j < C ; j++) visited[i][j] = true;
        }

        // 아래 부터
        Loop:
        for (int i = R-1 ; i >= 0 ; i--) {
            for (int j = 0 ; j < C ; j++) {
                if (map[i][j] == '.') continue;
                else break Loop;
            }

            // 해당 열 출력 못하게
            for (int j = 0 ; j < C ; j++) visited[i][j] = true;
        }

        // 왼쪽에서 부터
        Loop:
        for (int i = 0 ; i < C ; i++) {
            for (int j = 0 ; j < R ; j++) {
                if (map[j][i] == '.') continue;
                else break Loop;
            }

            // 해당 열 출력 못하게
            for (int j = 0 ; j < R ; j++) visited[j][i] = true;
        }

        // 오른쪽에서 부터
        Loop:
        for (int i = C-1 ; i >= 0 ; i--) {
            for (int j = 0 ; j < R ; j++) {
                if (map[j][i] == '.') continue;
                else break Loop;
            }

            // 해당 열 출력 못하게
            for (int j = 0 ; j < R ; j++) visited[j][i] = true;
        }
    }

    // 모서리는 2개 카운트 먹고 시작
    static int check(int x, int y) {
        if (x < 0 || x >= C || y < 0 || y >= R || map[y][x] == '.') return 1;
        return 0;
    }
}