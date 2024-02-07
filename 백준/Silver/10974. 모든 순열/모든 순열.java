import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] input;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        visited = new boolean[N+1];
        input = new int[N];

        dfs(0);
    }

    static void dfs(int idx) {
        if (idx == N) {
            for (int i = 0 ; i < N ; i++) {
                System.out.print(input[i] + " ");
            }
            System.out.println();
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = i;
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }
}