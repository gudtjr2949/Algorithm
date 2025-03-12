import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] A, B;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            input(bf);
            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        Arrays.sort(A);
        Arrays.sort(B);

        int cnt = 0;

        for (int num : A) {
            cnt += bs(num);
        }

        sb.append(cnt).append("\n");
    }


    static int bs(int find) {
        int left = 0;
        int right = M;

        while (left < right) {
            int mid = (left + right) / 2;

            if (B[mid] < find) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < M ; i++) B[i] = Integer.parseInt(st.nextToken());
    }

    static void init() {
        A = new int[N];
        B = new int[M];
    }
}