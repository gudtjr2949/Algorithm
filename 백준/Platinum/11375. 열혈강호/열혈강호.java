import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] works;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i <= N ; i++) {
            visited = new boolean[M+1];
            if (dfs(i)) answer++;
        }
    }

    static boolean dfs(int idx) {
        for (int work : adj.get(idx)) {
            if (visited[work]) continue;

            visited[work] = true;

            if (works[work] == 0 || dfs(works[work])) {
                works[work] = idx;
                return true;
            }
        }

        return false;
    }


    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j < num ; j++) {
                adj.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
        works = new int[M+1];
    }
}