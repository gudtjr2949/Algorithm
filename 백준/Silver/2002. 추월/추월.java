import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static String[] in, out;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = N;

        in = new String[N];
        out = new String[N];

        for (int i = 0 ; i < N ; i++) in[i] = bf.readLine();

        for (int i = 0 ; i < N ; i++) out[i] = bf.readLine();

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = idx ; j < N ; j++) {
                if (in[i].equals(out[j])) {
                    answer--;
                    idx = j+1;
                    break;
                }
            }
        }
    }
}