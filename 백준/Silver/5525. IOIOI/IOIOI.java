import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, M, answer;
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        s = bf.readLine();
        solve();

        System.out.println(answer);
    }

    static void solve() {
        int cnt = 0;

        for (int i = 1 ; i < M-1 ; ) {
            if (s.charAt(i) == 'O' && s.charAt(i+1) == 'I') {
                cnt++;
                if (cnt == N) {
                    if (s.charAt(i - (2*N-1)) == 'I') answer++;
                    cnt--;
                }
                i += 2;
            } else {
                cnt = 0;
                i++;
            }
        }
    }
}