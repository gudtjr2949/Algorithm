import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static Shark shark;
    static class Shark {
        int x, y, cnt, size;

        public Shark(int x, int y, int cnt, int size) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.size = size;
        }
    }

    static class Node {
        int x, y, dis;

        public Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    shark = new Shark(j, i, 0, 2);
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Node fish = findFish();

            if (fish == null) break;

            answer += fish.dis;
            map[fish.y][fish.x] = 0;
            shark.x = fish.x;
            shark.y = fish.y;
            shark.cnt++;

            if (shark.cnt == shark.size) {
                shark.size++;
                shark.cnt = 0;
            }
        }

        System.out.println(answer);
    }

    static Node findFish() {
        Queue<Node> Q = new LinkedList<>();
        List<Node> fishList = new ArrayList<>();

        boolean[][] visited = new boolean[N][N];
        visited[shark.y][shark.x] = true;

        Q.add(new Node(shark.x, shark.y, 0));

        while (!Q.isEmpty()) {
            Node now = Q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[ny][nx])) continue;

                if (map[ny][nx] < shark.size && map[ny][nx] != 0) {
                    visited[ny][nx] = true;
                    fishList.add(new Node(nx, ny, now.dis+1));
                } else if (map[ny][nx] == shark.size || map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny, now.dis+1));
                }
            }
        }

        if (fishList.isEmpty()) return null;
        else {
            Collections.sort(fishList, (o1, o2) -> {
                if (o1.dis == o2.dis) {
                    if (o1.y == o2.y) return o1.x - o2.x;
                    return o1.y - o2.y;
                }
                return o1.dis - o2.dis;
            });

            return fishList.get(0);
        }
    }
}