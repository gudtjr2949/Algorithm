package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 10986 : 나머지 합
public class BOJ_10986 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] sum = new int[N+1];
        long[] cnt = new long[M];

        st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i < N+1 ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = (sum[i-1] + arr[i]) % M;
            cnt[sum[i]%M]++;
        }

        long answer = cnt[0];

        for (int i = 0 ; i < M ; i++) {
            answer += cnt[i] * (cnt[i]-1) / 2;
        }

        System.out.println(answer);
    }
}
