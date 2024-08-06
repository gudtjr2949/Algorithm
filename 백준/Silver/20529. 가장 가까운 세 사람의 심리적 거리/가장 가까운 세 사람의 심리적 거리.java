import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static String[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            StringTokenizer st = new StringTokenizer(bf.readLine());

            arr = new String[N];

            for (int i = 0 ; i < N ; i++) {
                arr[i] = st.nextToken();
            }

            if (N >= 33) {
                sb.append(0).append("\n");
            } else {
                sb.append(solve()).append("\n");
            }
        }

        System.out.println(sb);
    }

    static int solve() {
        int min = 100_000_000;

        for (int i = 0 ; i < N ; i++) {
            for (int j = i+1 ; j < N ; j++) {
                for (int k = j+1 ; k < N ; k++) {
                    int cnt = 0;

                    for (int z = 0 ; z < 4 ; z++) {
                        cnt += arr[i].charAt(z) == arr[j].charAt(z) ? 0 : 1;
                        cnt += arr[j].charAt(z) == arr[k].charAt(z) ? 0 : 1;
                        cnt += arr[k].charAt(z) == arr[i].charAt(z) ? 0 : 1;
                    }

                    min = Math.min(min, cnt);

                    if (min == 0) {
                        return 0;
                    }
                }
            }
        }

        return min;
    }
}