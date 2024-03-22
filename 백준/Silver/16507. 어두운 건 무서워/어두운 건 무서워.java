import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int[][] prefix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        prefix = new int[R+1][C+1];

        for (int i = 1 ; i <= R ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= C ; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < Q ; i++) {
            st = new StringTokenizer(bf.readLine());
            solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb);
    }

    static void solve(int r1, int c1, int r2, int c2) {
        int sum = prefix[r2][c2] - prefix[r1-1][c2] - prefix[r2][c1-1] + prefix[r1-1][c1-1];
        int cnt = ((c2 - c1) + 1) * ((r2 - r1) + 1);
        sb.append(sum/cnt).append("\n");
    }
}