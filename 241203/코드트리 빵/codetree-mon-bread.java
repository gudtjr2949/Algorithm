import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][][][] visited;
    static Node[] baseCamps;
    static class Node {
        int x, y, time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        baseCamps = new Node[M+1];
        visited = new boolean[N][N][N*N+1][M+1];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            findNearBase(x, y, i);
        }

        int answer = 0;

        for (int i = 1 ; i <= M ; i++) {
            answer = Math.max(answer, baseCamps[i].time);
        }

        System.out.println(answer);
    }

    static void findNearBase(int x, int y, int idx) {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) {
                if (o1.y == o2.y) return o1.x - o2.x;
                return o1.y - o2.y;
            }
            return o1.time - o2.time;
        });

        PQ.add(new Node(x, y, idx));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            visited[now.y][now.x][now.time][idx] = true;

            if (map[now.y][now.x] == 1) {
                baseCamps[idx] = new Node(now.x, now.y, now.time);

                for (int i = 1 ; i <= M ; i++) {
                    for (int j = idx ; j <= N*N ; j++) {
                        visited[now.y][now.x][j][i] = true; // now 위치가 베이스캠프이고, idx 분에 베이스캠프에서 출발하기 때문에 모든 사람은 idx 분 이후에 베이스캠프를 지나면 안됨
                    }
                    for (int j = now.time ; j <= N*N ; j++) {
                        visited[y][x][j][i] = true;
                    }
                }

                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && !visited[ny][nx][now.time+1][idx]) {
                    PQ.add(new Node(nx, ny, now.time+1));
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}