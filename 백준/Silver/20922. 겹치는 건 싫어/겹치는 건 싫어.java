import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] cnt, arr;


    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = 0;
        cnt[arr[0]]++;

        while (right+1 < N && left+1 < N) {
            if (cnt[arr[right+1]]+1 <= K) {
                cnt[arr[right+1]]++;
                right++;
            } else {
                cnt[arr[left]]--;
                left++;
            }

            answer = Math.max(answer, right-left+1);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        cnt = new int[max+1];
    }

    static void init() {
        arr = new int[N];
    }
}