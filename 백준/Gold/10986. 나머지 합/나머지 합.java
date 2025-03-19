import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long answer;
    static long[] arr, prefix, cnt;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        prefix[0] = arr[0];
        for (int i = 1 ; i < N ; i++) {
            prefix[i] = arr[i] + prefix[i-1];
        }

        for (int i = 0 ; i < N ; i++) {
            int remain = (int) (prefix[i] % M);

            if (remain == 0) {
                answer++;
            }

            cnt[remain]++;
        }

        for (int i = 0 ; i < M ; i++) {
            if (cnt[i] == 0) {
                continue;
            }

            answer += (cnt[i] * (cnt[i]-1)) / 2;
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
    }

    static void init() {
        arr = new long[N];
        prefix = new long[N];
        cnt = new long[M];
    }
}