import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int W, H, answer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0, 1, 0};
    static char[][] map;
    static boolean[][] visited;
    static Node sangguen;
    static List<Node> fires;
    static class Node {
        int x, y, time, value;

        public Node(int x, int y, int time, int value) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }

    static void solve() {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) return o1.value - o2.value;
            else return o1.time - o2.time;
        });

        PQ.add(new Node(sangguen.x, sangguen.y, sangguen.time, sangguen.value));
        visited[sangguen.y][sangguen.x] = true;

        for (Node fire : fires) {
            PQ.add(fire);
            visited[fire.y][fire.x] = true;
        }

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (now.value == 1) { // 불
                    if (check(nx, ny)) {
                        visited[ny][nx] = true;
                        map[ny][nx] = '*';
                        PQ.add(new Node(nx, ny, now.time+1, now.value));
                    }
                } else { // 상근
                    if (check(nx, ny)) {
                        visited[ny][nx] = true;
                        PQ.add(new Node(nx, ny, now.time+1, now.value));
                    } else if (!isRange(nx, ny)) {
                        answer = now.time+1;
                        return;
                    }
                }
            }
        }

        answer = -1;
    }

    private static boolean check(int nx, int ny) {
        return isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] == '.';
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            init();
            for (int i = 0 ; i < H ; i++) {
                String s = bf.readLine();
                for (int j = 0 ; j < W ; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == '@') {
                        map[i][j] = '.';
                        sangguen = new Node(j, i, 0, 2);
                    }
                    else if (map[i][j] == '*') fires.add(new Node(j, i, 0, 1));
                }
            }
            solve();

            if (answer == -1) sb.append("IMPOSSIBLE").append("\n");
            else sb.append(answer).append("\n");
        }
    }

    static void init() {
        map = new char[H][W];
        visited = new boolean[H][W];
        fires = new ArrayList<>();
        answer = 0;
    }
}