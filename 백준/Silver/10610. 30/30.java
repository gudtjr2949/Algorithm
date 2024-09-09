import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    static String s;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        s = bf.readLine();
        sb = new StringBuilder();
        solve();
    }

    static void solve() {
        int[] arr = new int[10];

        for (int i = 0 ; i < s.length() ; i++) {
            arr[s.charAt(i) - '0']++;
        }

        for (int i = 9 ; i >= 0 ; i--) {
            while (arr[i] > 0) {
                sb.append(i);
                arr[i]--;
            }
        }

        BigInteger bigInt = new BigInteger(sb.toString());
        BigInteger bigInt2 = new BigInteger(String.valueOf(30));

        BigInteger mod = bigInt.mod(bigInt2);

        if (mod.equals(new BigInteger(String.valueOf(0)))) {
            System.out.println(bigInt);
        } else {
            System.out.println(-1);
        }
    }
}