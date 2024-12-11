import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer, MAX = 100_00_001;
    static List<Node> home, chicken;
    static boolean[] visited;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        home = new ArrayList<>();
        chicken = new ArrayList<>();

        answer = MAX;

        for (int i = 0  ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 1) home.add(new Node(j, i));
                else if (num == 2) chicken.add(new Node(j, i));
            }
        }

        visited = new boolean[chicken.size()];

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            solve();
            return;
        }

        for (int i = cur ; i < chicken.size() ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(idx+1, i+1);
                visited[i] = false;
            }
        }
    }

    static void solve() {
        int sum = 0;

        for (int i = 0 ; i < home.size() ; i++) {
            int min = MAX;

            for (int j = 0 ; j < chicken.size() ; j++) {
                if (visited[j]) {
                    min = Math.min(min, Math.abs(chicken.get(j).x - home.get(i).x) + Math.abs(chicken.get(j).y - home.get(i).y));
                }
            }

            sum += min;
        }

        answer = Math.min(answer, sum);
    }
}