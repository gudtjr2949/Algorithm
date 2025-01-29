import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long M, answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);
        long left = 1;
        long right = arr[N-1];

        while (left < right) {
            long mid = (left + right) / 2;

            if (cutTree(mid) < M) right = mid; // 너무 적게 잘림 -> mid를 더 작게 만들어야 함
            else left = mid+1;
        }

        answer = left-1;
    }

    static long cutTree(long mid) {
        long result = 0;

        for (long tree : arr) {
            result += tree - mid > 0 ? tree - mid : 0;
        }
        
        return result;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) arr[i] = Long.parseLong(st.nextToken());
    }

    static void init() {
        arr = new long[N];
    }
}