import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer = Integer.MIN_VALUE;
    static int[][] C;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        C = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                C[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        dfs(0, 0, new int[K]);

        System.out.println(answer);
    }

    static void dfs(int idx, int depth, int[] input) {
        if (depth == K) {
            answer = Math.max(answer, solve(input));
            return;
        }

        for (int i = idx; i < N; i++) {
            input[depth] = i;
            dfs(i + 1, depth + 1, input);
        }
    }

    static int solve(int[] input) {
        int sum = 0;

        for (int i = 0 ; i < K-1 ; i++) {
            for (int j = i+1 ; j < K ; j++) {
                sum += C[input[i]][input[j]];
            }
        }

        return sum;
    }
}