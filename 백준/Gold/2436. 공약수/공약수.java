import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int A, B;
    static long resultA, resultB;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(resultA + " " + resultB);
    }

    static void solve() {
        int num = B / A;

        for (int i = 1 ; i * i <= num ; i++) {
            if (num % i == 0) {
                int a = i;
                int b = num / i;

                if (GCD(a, b) == 1) {
                    resultA = a * A;
                    resultB = b * A;
                }
            }
        }
    }

    static int GCD(int x, int y) {
        if (x % y == 0) return y;
        return GCD(y, x % y);
    }
}