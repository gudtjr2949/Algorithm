import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] matchResult;
    static boolean possible;
    static boolean[][] visited;
    static int[][] matchLineup;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        matchLineup = new int[15][2];

        int idx = 0;

        for(int i = 0; i < 5; i++) {
            for(int j = i + 1; j < 6; j++) {
                matchLineup[idx][0] = i;
                matchLineup[idx][1] = j;
                idx++;
            }
        }

        for (int T = 0 ; T < 4 ; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            matchResult = new int[6][3];
            possible = true;

            for (int i = 0 ; i < 6 ; i++) {
                int sum = 0;

                for (int j = 0 ; j < 3 ; j++) {
                    matchResult[i][j] = Integer.parseInt(st.nextToken());
                    sum += matchResult[i][j];
                }

                if (sum != 5) possible = false;
            }

            if (possible) {
                possible = false;
                dfs(0);
            }


            if (possible) sb.append(1 + " ");
            else sb.append(0 + " ");
        }

        System.out.println(sb);
    }

    /**
     * result = 0 -> 이김
     * result = 1 -> 비김
     * result = 2 -> 짐
     */
    static void dfs(int idx) {
        if (possible) return;

        if (check()) {
            possible = true;
            return;
        }

        int home = matchLineup[idx][0];
        int away = matchLineup[idx][1];

        // home 이 away 를 이긴 경우
        if (matchResult[home][0] > 0 && matchResult[away][2] > 0) {
            matchResult[home][0]--;
            matchResult[away][2]--;
            dfs(idx+1);
            matchResult[home][0]++;
            matchResult[away][2]++;
        }

        // home 이 away 를 비긴 경우
        if (matchResult[home][1] > 0 && matchResult[away][1] > 0) {
            matchResult[home][1]--;
            matchResult[away][1]--;
            dfs(idx+1);
            matchResult[home][1]++;
            matchResult[away][1]++;
        }

        // home 이 away 에게 진 경우
        if (matchResult[home][2] > 0 && matchResult[away][0] > 0) {
            matchResult[home][2]--;
            matchResult[away][0]--;
            dfs(idx+1);
            matchResult[home][2]++;
            matchResult[away][0]++;
        }
    }

    static boolean check() {
        for (int i = 0 ; i < 6 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if (matchResult[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}