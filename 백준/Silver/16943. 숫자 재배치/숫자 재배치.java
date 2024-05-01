import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long answer = -1;
    static String A, B;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = st.nextToken();
        B = st.nextToken();

        visited = new boolean[A.length()];

        dfs(new int[A.length()], 0);

        System.out.println(answer);
    }

    static void dfs(int[] input, int idx) {
        if (idx == A.length()) {
            long num = 0;
            for (int a : input) {
                num = 10 * num + a;
            }

            if (num < Integer.parseInt(B)) {
                answer = Math.max(answer, num);
            }

            return;
        }

        if (idx > 0 && input[0] == 0) return;

        for (int i = 0 ; i < A.length() ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = A.charAt(i) - '0';
                dfs(input, idx+1);
                visited[i] = false;
            }
        }
    }
}