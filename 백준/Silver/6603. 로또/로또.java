import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            K = Integer.parseInt(st.nextToken());

            if (K == 0) {
                break;
            }

            arr = new int[K];

            for (int i = 0 ; i < K ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(new int[6], 0, 0);

            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void dfs(int[] input, int idx, int start) {
        if (idx == 6) {
            for (int i = 0 ; i < 6 ; i++) {
                sb.append(input[i]).append(" ");
            }
            sb.append("\n");
            
            return;
        }

        for (int i = start ; i < K ; i++) {
            input[idx] = arr[i];
            dfs(input, idx+1, i+1);
        }
    }
}