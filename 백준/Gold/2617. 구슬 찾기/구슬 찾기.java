import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][N+1];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = true;
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }

        int mid = N / 2;

        for (int i = 1 ; i <= N ; i++) {
            int smallCnt = 0;
            int largeCnt = 0;

            for (int j = 1 ; j <= N ; j++) {
                if (i == j) continue;

                if (map[i][j]) largeCnt++;
                else if (map[j][i]) smallCnt++;
            }

            if (smallCnt > mid || largeCnt > mid) {
                answer++;
            }
        }
    }
}