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
        S = new int[N][N];
        visited = new boolean[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int start) {
        if (idx == N) {
            findAnswer();
            return;
        }

        visited[start] = true;
        dfs(idx+1, start+1);
        visited[start] = false;
        dfs(idx+1, start+1);
    }

    static void findAnswer() {
        List<Integer> teamA = new ArrayList<>();
        List<Integer> teamB = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            if (visited[i]) {
                teamA.add(i);
            } else {
                teamB.add(i);
            }
        }

        int teamASum = 0;
        int teamBSum = 0;

        for (int i = 0 ; i < teamA.size()-1 ; i++) {
            int player1 = teamA.get(i);

            for (int j = i+1 ; j < teamA.size() ; j++) {
                int player2 = teamA.get(j);
                teamASum += S[player1][player2] + S[player2][player1];
            }
        }

        for (int i = 0 ; i < teamB.size()-1 ; i++) {
            int player1 = teamB.get(i);

            for (int j = i+1 ; j < teamB.size() ; j++) {
                int player2 = teamB.get(j);
                teamBSum += S[player1][player2] + S[player2][player1];
            }
        }

        answer = Math.min(answer, Math.abs(teamASum - teamBSum));
    }
}