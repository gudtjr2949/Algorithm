package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1644 : 소수의 연속합
public class BOJ_1644 {

    static int N, answer;
    static boolean[] prime;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        prime = new boolean[N+1];

        checkPrime();
        findCount();

        System.out.println(answer);
    }

    private static void checkPrime() {

        prime[0] = prime[1] = true;

        for (int i = 2 ; i <= Math.sqrt(N) ; i++) {
            if (!prime[i]) {
                for (int j = i*i ; j <= N ; j+=i) {
                    prime[j] = true;
                }
            }
        }

        int cnt = 0;

        for (int i = 0 ; i <= N ; i++) {
            if (!prime[i]) {
                cnt++;
            }
        }

        arr = new int[cnt+2];

        int idx = 1;

        for (int i = 0 ; i <= N ; i++) {
            if (!prime[i]) {
                arr[idx] = i;
                idx++;
            }
        }

    }

    private static void findCount() {
        int sum = 0;
        int start = 1;
        int end = 1;

        int len = arr.length;

        while (start < len && end < len) {
            if (sum == N) {
                answer++;
            }

            if (sum >= N) {
                sum -= arr[start];
                start++;
            } else {
                sum += arr[end];
                end++;
            }
        }
    }
}
