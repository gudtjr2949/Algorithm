import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static String[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            arr = new String[N];

            for (int i = 0 ; i < N ; i++) {
                arr[i] = bf.readLine();
            }

            sb.append(solve()).append("\n");
        }

        System.out.println(sb);
    }

    static String solve() {
        Arrays.sort(arr);

        for (int i = 1 ; i < N ; i++) {
            if (arr[i].startsWith(arr[i-1])) {
                return "NO";
            }
        }

        return "YES";
    }
}