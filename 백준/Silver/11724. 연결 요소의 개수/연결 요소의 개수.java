import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, idx;
    static boolean[] visited;
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        visited = new boolean[N+1];

        idx = 0;

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            adj.get(num1).add(num2);
            adj.get(num2).add(num1);
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) { // 만약에 i번째 정점에 방문한 적 없으면
                dfs(i);
                idx++;
            }
        }

        HashSet<Integer> set = new HashSet<>();

        System.out.println(idx);

        // visited 에서 서로 다른 수 갯수 찾기
//        for (int i = 1 ; i <= N ; i++) {
//            set.add(visited[i]);
//        }
//
//        System.out.println(set.size());
    }

    static void dfs(int start) {

        visited[start] = true;

        for (int i = 0 ; i < adj.get(start).size() ; i++) {
            int next = adj.get(start).get(i);

            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }
}