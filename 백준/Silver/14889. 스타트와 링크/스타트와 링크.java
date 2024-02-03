import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] S;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        S = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 0);

        System.out.println(answer);
    }

    static void dfs(int start, int idx) {
        if (idx == N/2) {
            findAnswer();
            return;
        }

        for (int i = start ; i <= N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1, idx+1);
                visited[i] = false;
            }
        }
    }

    static void findAnswer() {
        List<Integer> startTeam = new ArrayList<>();
        List<Integer> linkTeam = new ArrayList<>();

        for (int i = 1 ; i <= N ; i++) {
            if (visited[i]) startTeam.add(i);
            else linkTeam.add(i);
        }

        int startSum = 0;
        int linkSum = 0;

        for (int i = 0 ; i < N/2 - 1 ; i++) {
            int playerStart1 = startTeam.get(i);
            int playerLink1 = linkTeam.get(i);
            for (int j = i+1 ; j < N/2 ; j++) {
                int playerStart2 = startTeam.get(j);
                int playerLink2 = linkTeam.get(j);

                startSum += S[playerStart1][playerStart2] + S[playerStart2][playerStart1];
                linkSum += S[playerLink1][playerLink2] + S[playerLink2][playerLink1];
            }
        }

        answer = Math.min(answer, Math.abs(startSum - linkSum));
    }

}