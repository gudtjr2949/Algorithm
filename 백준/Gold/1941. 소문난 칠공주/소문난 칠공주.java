import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N = 5, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[] input;
    static char[][] map;
    static boolean[] visited;
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][N];
        visited = new boolean[25];
        input = new int[7];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        input[0] = 0;
        dfs(0, 0);

        System.out.println(answer);
    }

    // 조합
    static void dfs(int idx, int cur) {
        if (idx == 7) {
            if (countCheck() && nearCheck()) answer++;
            return;
        }

        for (int i = cur ; i < 25 ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = i;
                dfs(idx+1, i+1);
                visited[i] = false;
            }

        }

    }

    static boolean nearCheck() {
        boolean[][] bundle = new boolean[N][N];
        for (int i = 0 ; i < 7 ; i++) {
            int y = input[i] / 5;
            int x = input[i] % 5;
            bundle[y][x] = true;
        }

        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(input[0] % 5, input[0] / 5));

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && bundle[ny][nx]) {
                    bundle[ny][nx] = false;
                    Q.add(new Node(nx, ny));
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (bundle[i][j]) return false;
            }
        }


        return true;
    }

    static boolean countCheck() {
        int cnt = 0;

        for (int i = 0 ; i < 7 ; i++) {
            int y = input[i] / 5;
            int x = input[i] % 5;
            if (map[y][x] == 'S') cnt++;
        }

        if (cnt >= 4) return true;
        return false;
    }
}