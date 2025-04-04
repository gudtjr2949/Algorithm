import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C, answer = -1;
    static Node start, end;
    static List<Node> waters;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static Queue<Node> PQ;

    // 물이면 status = 0, 고슴도치면 status = 1
    static class Node {
        int x, y, cnt, status;
        public Node(int x, int y, int cnt, int status) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    ", status=" + status +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        bfs();
        if (answer == -1) System.out.println("KAKTUS");
        else System.out.println(answer);
    }

    static void bfs() {
        PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.cnt == o2.cnt) return o2.status - o1.status;
            return o1.cnt - o2.cnt;
        });

        PQ.add(start);

        for (Node water : waters) {
            PQ.add(water);
        }

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.status == 1 && map[now.y][now.x] == '*') continue;

            if (now.x == end.x && now.y == end.y) {
                answer = now.cnt;
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny)) {
                    if (now.status == 0) { // 물
                        if (map[ny][nx] == '.' || map[ny][nx] == 'S') {
                            map[ny][nx] = '*';
                            PQ.add(new Node(nx, ny, now.cnt+1, now.status));
                        }
                    } else {
                        if (map[ny][nx] == '.' || map[ny][nx] == 'D') {
                            map[ny][nx] = 'S';
                            PQ.add(new Node(nx, ny, now.cnt+1, now.status));
                        }
                    }
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < C && y >= 0 && y < R;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    start = new Node(j, i, 0, 1);
                } else if (map[i][j] == '*') {
                    waters.add(new Node(j, i, 0, 0));
                } else if (map[i][j] == 'D') {
                    end = new Node(j, i, 0, 0);
                }
            }
        }
    }

    static void init() {
        map = new char[R][C];
        waters = new ArrayList<>();
    }
}