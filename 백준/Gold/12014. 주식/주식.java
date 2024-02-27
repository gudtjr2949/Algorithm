import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 1; T <= Test ; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            int[] dp = new int[N];

            st = new StringTokenizer(bf.readLine());
            for (int i = 0 ; i < N ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.fill(dp, Integer.MIN_VALUE);

            dp[0] = 1;

            boolean possible = false;

            for (int i = 1 ; i < N ; i++) {
                dp[i] = 1;
                for (int j = 0 ; j < i ; j++) {
                    if (arr[i] > arr[j]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }

            for (int i = 0 ; i < N ; i++) {
                if (dp[i] >= K) {
                    possible = true;
                    break;
                }
            }

            sb.append("Case #").append(T).append("\n");
            sb.append(possible ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}