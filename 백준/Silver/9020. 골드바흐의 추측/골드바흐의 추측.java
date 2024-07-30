import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int MAX = 10_000;
    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        int[][] result = new int[T][2];

        settingPrime();
        int idx = 0;

        while (T-- > 0) {
            int N = Integer.parseInt(bf.readLine());
            for (int i = 2 ; i <= N / 2 ; i++) {
                if (isPrime[i] && isPrime[N-i]) {
                    result[idx][0] = Math.min(i, N-i);
                    result[idx][1] = Math.max(i, N-i);
                }
            }
            idx++;
        }

        for (int i = 0 ; i < idx ; i++) {
            System.out.println(result[i][0] + " " + result[i][1]);
        }
    }

    private static void settingPrime() {
        isPrime = new boolean[MAX+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2 ; i <= Math.sqrt(MAX) ; i++) {
            if (isPrime[i]) {
                for (int j = i*i ; j <= MAX ; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}