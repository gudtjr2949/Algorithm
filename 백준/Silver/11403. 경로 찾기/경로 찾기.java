import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void solve() {
        for (int k = 0 ; k < N ; k++) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }
    }
}