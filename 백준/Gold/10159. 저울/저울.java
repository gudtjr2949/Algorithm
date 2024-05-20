import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        map = new int[N+1][N+1];

        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(map[i], 100_000_000);
        }

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            map[A][B] = 1;
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    // i 기 k 보다 크고, k 가 j 보다 크면 -> i 는 j 보다 큰게 됨
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 서로의 순서를 모른다 -> map[i][j] == 100_000_000 && map[j][i] == 100_000_000
        for (int i = 1 ; i <= N ; i++) {
            int cnt = 0;
            for (int j = 1 ; j <= N ; j++) {
                if (i == j) continue;

                if (map[i][j] == 100_000_000 && map[j][i] == 100_000_000) cnt++;
            }

            sb.append(cnt).append("\n");
        }
    }
}