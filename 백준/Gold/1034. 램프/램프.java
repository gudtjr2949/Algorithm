import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, answer;
    static char[][] map;

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

        K = Integer.parseInt(bf.readLine());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            int cnt = 0;
            StringBuilder standard = new StringBuilder();

            for (int j = 0 ; j < M ; j++) {
                standard.append(map[i][j]);
                if (map[i][j] == '0') cnt++;
            }

            int sum = 0;

            if (cnt <= K && (K % 2 != 0 && cnt % 2 != 0 || K % 2 == 0 && cnt % 2 == 0)) {
                for (int j = 0 ; j < N ; j++) {
                    StringBuilder tmp = new StringBuilder();

                    for (int k = 0 ; k < M ; k++) {
                        tmp.append(map[j][k]);
                    }

                    if (standard.toString().equals(tmp.toString())) sum++;
                }
            }

            answer = Math.max(answer, sum);
        }
    }
}