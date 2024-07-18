import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, K;
    static boolean[] prime;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        N = 1299709;
        prime = new boolean[N+1];
        makePrime();

        while (T-- > 0) {
            K = Integer.parseInt(bf.readLine());

            if (prime[K]) {
                sb.append(0).append("\n");
            } else {
                sb.append(solve()).append("\n");
            }
        }

        System.out.println(sb);
    }

    // K에서 작은 쪽으로 가장 가까운 소수, 큰 쪽으로 가장 가까운 소수 찾기
    static int solve() {
        int left = K;
        int right = K;

        while (true) {
            if (prime[--left]) break;
        }

        while (true) {
            if (prime[++right]) break;
        }

        return right - left;
    }

    static void makePrime() {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2 ; i <= Math.sqrt(N) ; i++) {
            if (prime[i]) {
                for (int j = i*i ; j <= N ; j += i) {
                    prime[j] = false;
                }
            }
        }
    }
}