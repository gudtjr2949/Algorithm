import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static Node[] input;
    static List<Node> viruses, normal;
    static int[][] map;
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        viruses = new ArrayList<>();
        normal = new ArrayList<>();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) viruses.add(new Node(j, i));
                else if (map[i][j] == 0) normal.add(new Node(j, i));
            }
        }

        input = new Node[3];

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == 3) {
            solve();
            return;
        }

        for (int i = cur ; i < normal.size() ; i++) {
            input[idx] = normal.get(i);
            dfs(idx+1, i+1);
        }

    }

    static void solve() {
        Queue<Node> Q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        int[][] tmpMap = copiedMap();

        for (Node newAll : input) {
            tmpMap[newAll.y][newAll.x] = 1;
        }

        for (Node virus : viruses) {
            Q.add(virus);
        }

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && tmpMap[ny][nx] == 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    tmpMap[ny][nx] = 2;
                    Q.add(new Node(nx, ny));
                }
            }
        }

        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (tmpMap[i][j] == 0) {
                    cnt++;
                }
            }
        }

        answer = Math.max(answer, cnt);
    }

    private static int[][] copiedMap() {
        int[][] tmp = new int[N][M];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }
}