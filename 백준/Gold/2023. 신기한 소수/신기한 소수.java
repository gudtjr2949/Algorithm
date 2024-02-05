import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        dfs(0, N);
    }

    static void dfs(int num, int N) {
        if (N == 0) { // N == 0 이면 자릿수를 다 채웠다는 의미
            System.out.println(num);
            return;
        }

        // num의 자릿수를 하나씩 들려가며 체크함
        for (int i = 1 ; i < 10 ; i++) {
            int next = 10 * num + i;
            if (isPrime(next)) {
                dfs(next, N-1);
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2 ; i <= Math.sqrt(num) ; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}