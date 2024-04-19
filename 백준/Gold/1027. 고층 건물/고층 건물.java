import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            int cnt = 0;
            double tmp = 0;

            // 왼쪽
            for (int j = i-1 ; j >= 0 ; j--) {
                double inclination = (double) (arr[i] - arr[j]) / (i - j);

                if (j == i-1 || tmp > inclination) {
                    cnt++;
                    tmp = inclination;
                }
            }

            tmp = 0;

            // 오른쪽
            for (int j = i+1 ; j < N ; j++) {
                double inclination = (double) (arr[i] - arr[j]) / (i - j);

                if (j == i+1 || tmp < inclination) {
                    cnt++;
                    tmp = inclination;
                }
            }

            answer = Math.max(answer, cnt);
        }
    }
}