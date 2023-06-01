package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 9466 : 텀 프로젝트
public class BOJ_9466 {

    static int N, cnt;
    static int[] arr;
    static boolean[] finals, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            N = Integer.parseInt(bf.readLine());
            arr = new int[N+1];
            finals = new boolean[N+1];
            visited = new boolean[N+1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

            for (int i = 1 ; i < N+1 ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1 ; i < N+1 ; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            sb.append(N-cnt).append("\n");
        }
        System.out.println(sb);
    }

    // dfs 돌렸을 때, 자기 자신에게 돌아와야 함
    private static void dfs(int num) {
        // 방문처리하고,
        visited[num] = true;

        // 선택받은 인원에게 방문한 적이 있고, 아직까지 그 인원이 최종 팀 결정이 되지 않은 경우 -> 싸이클
        if (visited[arr[num]] && !finals[arr[num]]) {
            for (int i = arr[num]; num != i; i = arr[i])
                cnt++;
            cnt++;
        }

        if (!visited[arr[num]]) {
            dfs(arr[num]);
        }

        // 여기까지 내려왔다는 것은 끝까지 다 돌고 내려온거
        finals[num] = true;
    }
}
