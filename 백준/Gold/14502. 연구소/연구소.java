import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    static int N, M, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static int[][] map;
    static List<Node> list;
    static Node[] input;

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
        list = new ArrayList<>();
        input = new Node[3];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) list.add(new Node(j, i));
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cur) {
        if (idx == 3) {
            bfs();
            return;
        }

        for (int i = cur ; i < list.size() ; i++) {
            input[idx] = list.get(i);
            dfs(idx+1, i+1);
        }
    }

    static void bfs() {
        Queue<Node> Q = new LinkedList<>();

        int[][] tmp = new int[N][M];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for (int i = 0 ; i < 3 ; i++) {
            int y = input[i].y;
            int x = input[i].x;
            tmp[y][x] = 1;
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (tmp[i][j] == 2) {
                    Q.add(new Node(j, i));
                }
            }
        }


        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && tmp[ny][nx] == 0) {
                    tmp[ny][nx] = 2;
                    Q.add(new Node(nx, ny));
                }
            }
        }

        int result = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (tmp[i][j] == 0) result++;
            }
        }

        answer = Math.max(answer, result);
    }
}