import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            boolean[] visited = new boolean[N];
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j] == 'Y') {
                    visited[j] = true;
                    for (int k = 0 ; k < N ; k++) {
                        if (map[j][k] == 'Y') {
                            visited[k] = true;
                        }
                    }
                }
            }

            int cnt = 0;
            for (int j = 0 ; j < N ; j++) {
                if (i == j) continue;
                if (visited[j]) cnt++;
            }

            answer = Math.max(answer, cnt);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }

    static void init() {
        map = new char[N][N];
    }
}