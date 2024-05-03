import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, D, K, C, answer;
    static int[] arr, eat;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        eat = new int[D+1];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {

        int cnt = 0;

        for (int i = 0 ; i < K ; i++) {
            if (eat[arr[i]] == 0) {
                cnt++;
            }
            eat[arr[i]]++;
        }

        answer = cnt;

        for (int i = 1 ; i <= N ; i++) {
            if (answer <= cnt) {
                if (eat[C] == 0) { // 쿠폰 아직 안씀
                    answer = cnt + 1;
                } else { // eat[C] == 0 라면 쿠폰을 써도 똑같음
                    answer = cnt;
                }
            }

            // 마지막 초밥 더함
            int end = (i + K - 1) % N;
            if (eat[arr[end]] == 0) {
                cnt++;
            }
            eat[arr[end]]++;

            // 처음 초밥 뺌
            eat[arr[i-1]]--;
            if (eat[arr[i-1]] == 0) {
                cnt--;
            }
        }
    }
}