import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, startX, startY, MAX = 1000, answerPass, answerNear;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;

    static class Node {
        int x, y, nearG, passG;

        public Node(int x, int y, int nearG, int passG) {
            this.x = x;
            this.y = y;
            this.nearG = nearG;
            this.passG = passG;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    startX = j;
                    startY = i;
                }
            }
        }

        solve();

        System.out.println(answerPass + " " + answerNear);
    }

    static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.passG == o2.passG) return o1.nearG - o2.nearG;
                else return o1.passG - o2.passG;
            }
        });

//        int[][] dist = new int[N][M];
//        for (int i = 0 ; i < N ; i++) Arrays.fill(dist[i], MAX);
//        dist[startY][startX] = 0;
        boolean[][] visited = new boolean[N][M];
        PQ.add(new Node(startX, startY, 0, 0));
        visited[startY][startX] = true;

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (map[now.y][now.x] == 'F') {
                answerPass = now.passG;
                answerNear = now.nearG;
                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if (map[ny][nx] == 'g')
                        PQ.add(new Node(nx, ny, now.nearG, now.passG + 1));
                    else if (map[ny][nx] == '.') {
                        PQ.add(new Node(nx, ny, now.nearG + checkNearG(nx, ny), now.passG));
                    } else
                        PQ.add(new Node(nx, ny, now.nearG, now.passG));
                }
            }
        }
    }

    static int checkNearG(int x, int y) {
        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 'g') {
                return 1;
            }
        }
        return 0;
    }
}