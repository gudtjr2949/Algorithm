import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] A;
    static int[][] R, M;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        R = new int[K][N];
        M = new int[K][N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++)
            A[i] = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        if (idx == K) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0 ; i < N ; i++) { // 랑이
            for (int j = 0 ; j < N ; j++) { // 메리
                if ((i == j && A[i] > 1) || (i != j && A[i] > 0 && A[j] > 0)) {
                    A[i]--;
                    A[j]--;
                    dfs(idx+1, sum + R[idx][i] +M[idx][j]);
                    A[i]++;
                    A[j]++;
                }
            }
        }
    }
}