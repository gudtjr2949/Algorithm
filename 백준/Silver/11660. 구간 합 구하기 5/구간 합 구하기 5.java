import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, prefix;
    static int[][] operation;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + map[i][j];
            }
        }

        for (int i = 0 ; i < M ; i++) {
            int y1 = operation[i][0];
            int x1 = operation[i][1];
            int y2 = operation[i][2];
            int x2 = operation[i][3];
            int sum = prefix[y2][x2] - prefix[y1-1][x2] - prefix[y2][x1-1] + prefix[y1-1][x1-1];
            sb.append(sum).append("\n");
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            operation[i][0] = Integer.parseInt(st.nextToken());
            operation[i][1] = Integer.parseInt(st.nextToken());
            operation[i][2] = Integer.parseInt(st.nextToken());
            operation[i][3] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        map = new int[N+1][N+1];
        prefix = new int[N+1][N+1];
        operation = new int[M+1][4];
        sb = new StringBuilder();
    }
}