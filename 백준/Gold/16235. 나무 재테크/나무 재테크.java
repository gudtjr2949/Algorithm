import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] map;
    static int[][] add;
    static Queue<Node> springQ, summerQ, autumnQ;
    static class Node {
        int x, y, age;
        public Node(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        add = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        springQ = new PriorityQueue<>((o1, o2) -> o1.age - o2.age);
        summerQ = new LinkedList<>();
        autumnQ = new LinkedList<>();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            springQ.add(new Node(x, y, age));
        }

        solve();

        System.out.println(springQ.size());
    }

    static void solve() {
        for (int i = 0 ; i < K ; i++) {
            // 봄
            while (!springQ.isEmpty()) {
                Node now = springQ.poll();

                if (now.age > map[now.y][now.x]) {
                    summerQ.add(now);
                    continue;
                }

                map[now.y][now.x] -= now.age;

                autumnQ.add(new Node(now.x, now.y, now.age+1));
            }

            // 여름
            while (!summerQ.isEmpty()) {
                Node dead = summerQ.poll();
                map[dead.y][dead.x] += dead.age / 2;
            }


            // 가을
            while (!autumnQ.isEmpty()) {
                Node now = autumnQ.poll();

                springQ.add(now);
                if (now.age % 5 != 0) {
                    continue;
                }

                for (int k = 0 ; k < 8 ; k++) {
                    int nx = now.x + dx[k];
                    int ny = now.y + dy[k];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        springQ.add(new Node(nx, ny, 1));
                    }
                }
            }


            // 겨울
            for (int j = 0 ; j < N ; j++) {
                for (int k = 0 ; k < N ; k++) {
                    map[j][k] += add[j][k];
                }
            }
        }
    }
}