import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static int[][][] prefix;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[M+1][N+1];
        prefix = new int[3][M+1][N+1];

        int K = Integer.parseInt(bf.readLine());

        for (int i = 1 ; i <= M ; i++) {
            String s = bf.readLine();
            for (int j = 1 ; j <= N ; j++) {
                map[i][j] = s.charAt(j-1);
            }
        }

        solve();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()); // y1
            int b = Integer.parseInt(st.nextToken()); // x1
            int c = Integer.parseInt(st.nextToken()); // y2
            int d = Integer.parseInt(st.nextToken()); // x2

            sb.append(prefix[0][c][d] - prefix[0][c][b-1] - prefix[0][a-1][d] + prefix[0][a-1][b-1]).append(" "); // 정글
            sb.append(prefix[1][c][d] - prefix[1][c][b-1] - prefix[1][a-1][d] + prefix[1][a-1][b-1]).append(" "); // 바다
            sb.append(prefix[2][c][d] - prefix[2][c][b-1] - prefix[2][a-1][d] + prefix[2][a-1][b-1]); // 얼음
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i <= M ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                int J = map[i][j] == 'J' ? 1 : 0;
                int O = map[i][j] == 'O' ? 1 : 0;
                int I = map[i][j] == 'I' ? 1 : 0;

                prefix[0][i][j] = J + prefix[0][i-1][j] + prefix[0][i][j-1] - prefix[0][i-1][j-1];
                prefix[1][i][j] = O + prefix[1][i-1][j] + prefix[1][i][j-1] - prefix[1][i-1][j-1];
                prefix[2][i][j] = I + prefix[2][i-1][j] + prefix[2][i][j-1] - prefix[2][i-1][j-1];
            }
        }
    }
}