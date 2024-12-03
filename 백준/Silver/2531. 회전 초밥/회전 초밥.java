import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, D, K, C, answer;
    static int[] arr, cnt;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        set = new HashSet<>();

        arr = new int[N];
        cnt = new int[D+1];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        set.add(C);
        cnt[C]++;

        for (int i = 0 ; i < K ; i++) {
            set.add(arr[i]);
            cnt[arr[i]]++;
        }

        answer = set.size();

        for (int i = 1 ; i < N ; i++) {
            if (--cnt[arr[i-1]] == 0) set.remove(arr[i-1]);

            if (i+K-1 < N) {
                cnt[arr[i+K-1]]++;
                set.add(arr[i+K-1]);
            } else {
                cnt[arr[(i+K-1)-N]]++;
                set.add(arr[(i+K-1)-N]);
            }

            answer = Math.max(answer, set.size());
        }
    }
}