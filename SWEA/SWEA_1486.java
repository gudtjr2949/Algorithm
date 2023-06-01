package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 1486 : 장훈이의 높은 선반
public class SWEA_1486 {

    static int N, B, answer;
    static int[] H;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 1 ; T <= Test ; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            H = new int[N];

            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(bf.readLine());

            for (int i = 0 ; i < N ; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1 ; i <= N ; i++) {
                dfs(0, 0, new int[i]);
            }

            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int idx, int start, int[] input) {
        if (idx == input.length) {
            check(input);

            return;
        }

        for (int i = start ; i < N ; i++) {
            input[idx] = i;
            dfs(idx+1, i+1, input);
        }
    }

    private static void check(int[] input) {
        int sum = 0;

        for (int i = 0 ; i < input.length ; i++) {
            sum += H[input[i]];
        }

        if (sum >= B) {
            answer = Math.min(answer, sum - B);
        }
    }
}
