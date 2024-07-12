import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr, input;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        input = new int[M];
        sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(sb);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            for (int i = 0 ; i < M ; i++)
                sb.append(input[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = cur ; i < N ; i++) {
            input[idx] = arr[i];
            dfs(idx + 1, i);
        }
    }
}