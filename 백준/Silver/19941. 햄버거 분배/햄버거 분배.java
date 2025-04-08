import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static boolean[] visited;
    static String s;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (s.charAt(i) == 'P') {
                boolean result = searchLeft(i);
                if (result) {
                    answer++;
                }
                else {
                    result = searchRight(i);
                    if (result) {
                        answer++;
                    }
                }

            }
        }
    }

    static boolean searchLeft(int idx) {
        for (int i = idx-K ; i < idx ; i++) {
            if (isRange(i) && s.charAt(i) == 'H' && !visited[i]) {
                visited[i] = true;
                return true;
            }
        }

        return false;
    }

    static boolean searchRight(int idx) {
        for (int i = idx+1 ; i <= idx+K ; i++) {
            if (isRange(i) && s.charAt(i) == 'H' && !visited[i]) {
                visited[i] = true;
                return true;
            }
        }

        return false;
    }

    static boolean isRange(int idx) {
        return idx >= 0 && idx < N;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        s = bf.readLine();
    }

    static void init() {
        visited = new boolean[N];
    }
}