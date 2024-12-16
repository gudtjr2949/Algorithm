import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, endTime, endX, endY;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static class Node {
        int time, type, x, y;

        public Node(int time, int type, int x, int y) {
            this.time = time;
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());;
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        endTime = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken())-1;
        endX = Integer.parseInt(st.nextToken())-1;

        bfs();

        System.out.println(map[endY][endX]);
    }

    static void bfs() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) return o1.type - o2.type;
            return o1.time - o2.time;
        });

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j] != 0) {
                    PQ.add(new Node(0, map[i][j], j, i));
                }
            }
        }

        int time = 0;

        while (!PQ.isEmpty()) {

            if (time == endTime) break;

            int size = PQ.size();
            for (int i = 0 ; i < size ; i++) {
                Node now = PQ.poll();

                if (now.x == endX && now.y == endY) {
                    map[now.y][now.x] = now.type;
                    return;
                }

                for (int j = 0 ; j < 4 ; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (isRange(nx, ny) && map[ny][nx] == 0) {
                        map[ny][nx] = now.type;
                        PQ.add(new Node(now.time+1, now.type, nx, ny));
                    }
                }
            }

            time++;
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}