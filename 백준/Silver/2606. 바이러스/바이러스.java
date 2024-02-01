import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[] visited;
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        visited = new boolean[N+1];

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        int computer = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < computer ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());

            adj.get(com1).add(com2);
            adj.get(com2).add(com1);
        }

        dfs(1);

        int answer = 0;

        for (int i = 1 ; i <= N ; i++) {
            if (visited[i]) {
                answer++;
            }
        }

        System.out.println(answer-1);
    }

    static void dfs(int start) {

        visited[start] = true;

        for (int i = 0 ; i < adj.get(start).size() ; i++) {
            int nextCom = adj.get(start).get(i);

            if (!visited[nextCom]) {
                visited[nextCom] = true;
                dfs(nextCom);
            }
        }
    }
}