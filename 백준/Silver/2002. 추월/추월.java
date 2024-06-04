import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer;
    static String[] start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        answer = N;

        start = new String[N];
        end = new String[N];

        for (int i = 0 ; i < N ; i++) {
            start[i] = bf.readLine();
        }

        for (int i = 0 ; i < N ; i++) {
            end[i] = bf.readLine();
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = idx ; j < N ; j++) {
                if (start[i].equals(end[j])) {
                    idx = j+1;
                    answer--;
                    break;
                }
            }
        }
    }
}