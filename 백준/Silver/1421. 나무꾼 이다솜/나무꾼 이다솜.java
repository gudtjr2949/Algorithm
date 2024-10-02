import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, C, W, max;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
            max = Math.max(max, arr[i]);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        // L 만큼의 길이를 만듦
        for (int L = 1 ; L <= max ; L++) {
            long sum = 0;
            for (int i = 0 ; i < N ; i++) {
                int cut = 0;

                if (arr[i] >= L) {
                    if(arr[i] % L == 0) cut = arr[i] / L - 1;
                    else cut = arr[i] / L;

                    if (W * L * (arr[i] / L) - cut * C > 0)
                        sum += W * L * (arr[i] / L) - (cut * C);
                }
            }

            answer = Math.max(answer, sum);
        }
    }
}