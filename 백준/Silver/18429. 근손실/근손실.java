import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(0, 500);

        System.out.println(answer);
    }

    static void dfs(int day, int weight) {
        if (day == N) {
            if (weight >= 500) answer++;
            return;
        }

        for (int i = 0 ; i < N ; i++) {
            if (!visited[i] && weight + arr[i] - K >= 500) {
                visited[i] = true;
                dfs(day+1, weight + arr[i] - K);
                visited[i] = false;
            }
        }
    }
}