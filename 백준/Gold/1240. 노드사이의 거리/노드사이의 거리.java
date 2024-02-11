import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, end;
    static boolean findAnswer;
    static boolean[] visited;
    static List<List<Point>> adj;
    static StringBuilder sb = new StringBuilder();
    static class Point {
        int node, dis;

        public Point(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N-1 ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            adj.get(from).add(new Point(to, dis));
            adj.get(to).add(new Point(from, dis));
        }

        for (int i = 0 ; i < M ; i++) {
            visited = new boolean[N+1];
            findAnswer = false;

            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            dfs(start, 0);
        }

        System.out.println(sb);
    }

    static void dfs(int start, int sum) {
        if (start == end) {
            sb.append(sum).append("\n");
            findAnswer = true;
            return;
        }

        visited[start] = true;

        for (int i = 0 ; i < adj.get(start).size() ; i++) {
            Point p = adj.get(start).get(i);

            if (!visited[p.node]) {
                visited[p.node] = true;
                dfs(p.node, sum + p.dis);
                if (findAnswer) {
                    return;
                }
            }
        }
    }
}