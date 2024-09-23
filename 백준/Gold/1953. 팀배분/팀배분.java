import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[] visited;
    static List<List<Integer>> adj, input;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        adj = new ArrayList<>();
        input = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());

        input.add(new ArrayList<>()); // 1팀
        input.add(new ArrayList<>()); // 2팀

        visited = new boolean[N+1];

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j < num ; j++) {
                adj.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) {
                dfs(i, 0);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 2 ; i++) {
            sb.append(input.get(i).size()).append("\n");
            Collections.sort(input.get(i));
            for (Integer idx : input.get(i)) sb.append(idx).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, int cnt) {
        if (!visited[idx]) {
            visited[idx] = true;
            input.get(cnt % 2).add(idx);
            for (Integer next : adj.get(idx)) {
                dfs(next, cnt+1);
            }
        }
    }
}