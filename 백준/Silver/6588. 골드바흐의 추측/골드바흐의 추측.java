import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static boolean[] isPrime;
    static List<Integer> list;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        isPrime = new boolean[1_000_001];
        primeSetting();

        while (true) {
            N = Integer.parseInt(bf.readLine());
            if (N == 0) break;
            list = new ArrayList<>();
            solve();
        }

        System.out.println(sb);
    }

    static void solve() {
        boolean flag = false;

        for (int i = 2; i <= N / 2; i++) {
            if (isPrime[i] && isPrime[N - i]) {
                sb.append(N + " = " + i + " + " + (N - i)).append("\n");
                flag = true;
                break;
            }
        }

        if (!flag) {
            sb.append("Goldbach's conjecture is wrong.").append("\n");
        }

    }

    static void primeSetting() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2 ; i <= Math.sqrt(1_000_000) ; i++) {
            if (isPrime[i]) {
                for (int j = i*i ; j <= 1_000_000 ; j+=i)
                    isPrime[j] = false;
            }
        }
    }
}