import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static String[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());

            arr = new String[N];

            for (int i = 0 ; i < N ; i++) {
                arr[i] = bf.readLine();
            }

            Arrays.sort(arr);

            if (solve()) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean solve() {
        for (int i = 0 ; i < N-1 ; i++) {
            if (arr[i+1].startsWith(arr[i])) return false;
        }

        return true;
    }
}