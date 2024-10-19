import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static int[] input;
    static boolean[] visited;
    static List<Node> stores;
    static List<Node> homes;

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
        map = new int[N][N];
        homes = new ArrayList<>();
        stores = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) homes.add(new Node(j, i));
                else if (map[i][j] == 2) stores.add(new Node(j, i));
            }
        }

        input = new int[M];
        visited = new boolean[stores.size()];

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == M) {
            calculateDistance();
            return;
        }

        for (int i = cur ; i < stores.size() ; i++) {
            if (!visited[i]) {
                input[idx] = i;
                visited[i] = true;
                dfs(idx+1, i+1);
                visited[i] = false;
            }
        }
    }

    static void calculateDistance() {
        int sum = 0;

        for (Node home : homes) {
            int min = Integer.MAX_VALUE;
            for (int i = 0 ; i < M ; i++) {
                Node store = stores.get(input[i]);
                int distance = Math.abs(home.x - store.x) + Math.abs(home.y - store.y);
                min = Math.min(min, distance);
            }

            sum += min;
        }

        answer = Math.min(answer, sum);
    }
}