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

        for (int i = 0 ; i < M ; i++) {
            int cnt = 0;

            if (s.charAt(i) == 'I') {
                while (i+2 < M && s.charAt(i+1) == 'O' && s.charAt(i+2) == 'I') {
                    cnt++;
                    if (cnt == N) {
                        cnt--;
                        answer++;
                    }

                    i+=2;
                }
            }
        }
    }
}