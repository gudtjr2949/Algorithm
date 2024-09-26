import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, T, D, answer, MAX = 1_000_000;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // (시작점 -> 가장 높은 높이 -> 시작점) 의 합이 D를 넘으면 안됨
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                // 시작점 -> 가장 높은 높이
                int go = dijk(0, 0, j, i);

                // 가장 높은 높이 -> 시작점
                int back = dijk(j, i, 0, 0);
                if (go != -1 && back != -1 && go + back <= D) {
                    answer = Math.max(answer, Character.isUpperCase(map[i][j]) ? map[i][j] - 'A' : map[i][j] - 'a' + 26);
                }
            }
        }

        System.out.println(answer);
    }

    // 가능한 빠르게 도착해야 함 -> PQ의 기준이 time임
    static int dijk(int startX, int startY, int endX, int endY) {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });

        int[][] dist = new int[N][M];
        for (int i = 0 ; i < N ; i++) Arrays.fill(dist[i], MAX);
        dist[startY][startX] = 0;
        PQ.add(new Node(startX, startY, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == endX && now.y == endY) {
                return now.time;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    int from = Character.isUpperCase(map[now.y][now.x]) ? map[now.y][now.x] - 'A' : map[now.y][now.x] - 'a' + 26;
                    int to = Character.isUpperCase(map[ny][nx]) ? map[ny][nx] - 'A' : map[ny][nx] - 'a' + 26;

                    if (Math.abs(from - to) > T) continue;

                    int time = 0;
                    if (from < to) {
                        time = (int) Math.pow(Math.abs(from - to), 2);
                    } else time = 1;

                    if (dist[ny][nx] > now.time + time) {
                        dist[ny][nx] = now.time + time;
                        PQ.add(new Node(nx, ny, dist[ny][nx]));
                    }
                }
            }
        }

        return -1;
    }
}