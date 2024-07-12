import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr, input;
    static StringBuilder answer;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        input = new int[M];
        answer = new StringBuilder();
        set = new HashSet<>();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            check();
            return;
        }

        for (int i = cur; i < N; i++) {
            input[idx] = arr[i];
            dfs(idx + 1, i + 1);
        }
    }

    static void check() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(input[i]).append(" ");
        }

        if (!set.contains(sb.toString())) {
            set.add(sb.toString());
            answer.append(sb.toString()).append("\n");
        }
    }
}