import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] S;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        S = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int first = 0;
        if (N != 2) {
            first = findFirst();
        } else {
            first = 1;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(first).append(" ");

        for (int i = 1 ; i < N ; i++) {
            sb.append(S[0][i] - first).append(" ");
        }

        System.out.println(sb);
    }

    private static int findFirst() {
        int sum = 0;

        for (int i = 1 ; i < N ; i++) sum += S[0][i];

        int a = 1;
        int b = 2;

        while (a < N && b < N) {
            sum -= S[a][b];
            a += 2;
            b += 2;
        }

        int result = 0;

        if (N % 2 != 0) {
            result = sum / (N-1);
        } else {
            sum -= S[0][N-1];
            result = sum / (N-2);
        }

        return result;
    }
}