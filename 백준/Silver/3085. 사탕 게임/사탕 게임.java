import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer = 0;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new char[N][N];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        // 가로끼리 바꿈
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N-1 ; j++) {
                swap(j, i, j+1, i);
                check();
                swap(j+1, i, j, i);
            }
        }

        // 세로끼리 바꿈
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N-1 ; j++) {
                swap(i, j, i, j+1);
                check();
                swap(i, j+1, i, j);
            }
        }
    }

    static void check() {
        // 가로
        for (int i = 0 ; i < N ; i++) {
            int cnt = 1;
            for (int j = 0 ; j < N-1 ; j++) {
                if (map[i][j] == map[i][j+1]) {
                    cnt++;
                    answer = Math.max(answer, cnt);
                } else {
                    cnt = 1;
                }
            }
        }

        // 세로
        for (int i = 0 ; i < N ; i++) {
            int cnt = 1;
            for (int j = 0 ; j < N-1 ; j++) {
                if (map[j][i] == map[j+1][i]) {
                    cnt++;
                    answer = Math.max(answer, cnt);
                } else {
                    cnt = 1;
                }
            }
        }
    }

    static void swap(int x1, int y1, int x2, int y2) {
        char tmp = map[y1][x1];
        map[y1][x1] = map[y2][x2];
        map[y2][x2] = tmp;
    }
}