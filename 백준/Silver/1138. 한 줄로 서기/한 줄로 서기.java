import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, answer;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        for (int i = 0 ; i < N ; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            int cnt = 0;
            for (int j = 0 ; j < N ; j++) {
                if (cnt == arr[i] && answer[j] == 0) {
                    answer[j] = i+1;
                    break;
                }

                if (answer[j] == 0) cnt++;
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
        answer = new int[N];
    }
}