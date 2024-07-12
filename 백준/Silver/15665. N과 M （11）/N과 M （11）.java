import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr, input;
    static boolean[] visited;
    static StringBuilder answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        input = new int[M];

        answer = new StringBuilder();

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            for (int i = 0 ; i < M ; i++) {
                answer.append(input[i]).append(" ");
            }
            answer.append("\n");

            return;
        }

        int before = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] != before) {
                before = arr[i];
                input[idx] = arr[i];
                dfs(idx + 1, 0);
            }
        }
    }

}