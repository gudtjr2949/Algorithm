import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, dir, time;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static Deque<Node> deque;
    static Map<Integer, Character> move;
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

        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        for (int i = 0 ; i < K ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            map[y][x] = 1;
        }

        int L = Integer.parseInt(bf.readLine());
        move = new HashMap<>();

        for (int i = 0 ; i < L ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            move.put(x, c);
        }

        deque = new ArrayDeque<>();
        deque.add(new Node(0, 0));
        dir = 1;
        map[0][0] = -1;

        solve();

        System.out.println(time+1);
    }

    static void solve() {
        while (check()) {
            // 다음 경로로 이동
            int nx = deque.peek().x + dx[dir];
            int ny = deque.peek().y + dy[dir];
            deque.addFirst(new Node(nx, ny));

            // 사과 먹음의 유무에 따라 꼬리를 자를지, 자르지않을지 결정
            if (map[ny][nx] != 1) { // map[ny][nx] == 1 이면 사과인 경우
                Node last = deque.pollLast();
                map[last.y][last.x] = 0;
            }

            // 다음 경로에 방문체크
            map[ny][nx] = -1;

            time++;

            // 방향을 바꿔야하는 시간인지 확인
            if (move.containsKey(time)) {
                if (move.get(time) == 'L') {
                    dir--;
                    if (dir < 0) dir = 3;
                } else {
                    dir++;
                    if (dir == 4) dir = 0;
                }
            }
        }
    }

    static boolean check() {
        int nx = deque.peek().x + dx[dir];
        int ny = deque.peek().y + dy[dir];

        if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[ny][nx] != -1) {
            return true;
        }

        return false;
    }
}