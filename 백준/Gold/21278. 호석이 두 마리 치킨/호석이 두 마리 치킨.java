import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for (int i = 0 ; i <= N ; i++) {
            Arrays.fill(map[i], 100_000_000);
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }

        for (int i = 1 ; i <= N ; i++) {
            map[i][i] = 0;
        }

        solve();
    }

    static void solve() {
        for (int k = 1 ; k <= N ; k++) {
            for (int i = 1 ; i <= N ; i++) {
                for (int j = 1 ; j <= N ; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int min = 100_000_000;

        int building1 = 0;
        int building2 = 0;

        // 2개의 건물 선택
        for (int i = 1 ; i <= N ; i++) {
            for (int j = i+1 ; j <= N ; j++) {
                if (i == j) continue;

                int sum = 0;

                // i 와 j 에 치킨집을 설치함 -> 해당 건물에서 부터 나머지 건물들 까지의 최소 거리의 합을 구해야 함
                for (int k = 1 ; k <= N ; k++) {
                    sum += Math.min(map[i][k], map[j][k]) * 2;
                }

                if (min > sum) {
                    min = sum;
                    building1 = i;
                    building2 = j;
                }
            }
        }

        System.out.println(building1 + " " + building2 + " " + min);

    }
}