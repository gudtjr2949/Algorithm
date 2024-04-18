import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer = 0;
    static String[] words;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = bf.readLine();
        }

        visited = new boolean[27];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;


        if (K == 26) {
            answer = N;
        } else if (K >= 5) {
            dfs(0, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == K-5) {
            answer = Math.max(answer, solve());
            return;
        }

        for (int i = cur; i <= 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(idx + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static int solve() {
        int cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            String tmp = words[i];
            boolean possible = true;

            for (int j = 0 ; j < tmp.length() ; j++) {
                if (!visited[tmp.charAt(j) - 'a']) {
                    possible = false;
                    break;
                }
            }

            if (possible) cnt++;
        }

        return cnt;
    }
}