import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static String[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            N = Integer.parseInt(bf.readLine());
            arr = new String[N];
            for (int i = 0 ; i < N ; i++) {
                arr[i] = bf.readLine();
            }

            Arrays.sort(arr);

            if (solve()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean solve() {
        for (int i = 0 ; i < N-1 ; i++) {
            if (arr[i+1].startsWith(arr[i])) return false;
        }

        return true;
    }
}