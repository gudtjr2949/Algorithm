package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1806 : 부분합
public class BOJ_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;
        int sum = 0;

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i] == S) {
                answer = 1;
            }
        }

        while (start <= N && end <= N) {
            if (sum >= S) {
                answer = Math.min(answer, end - start);
            }

            if (sum < S) {
                sum += arr[end];
                end++;
            } else {
                sum -= arr[start];
                start++;
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        System.out.println(answer);
    }
}
