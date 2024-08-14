import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, x, y;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static class Node {
        int x, y, dir, cnt;

        public Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(bf.readLine());

        if (R * C < K)
            System.out.println(0);
        else {
            solve();
            System.out.println((x+1) + " " + (y+1));
        }
    }

    static void solve() {
        Queue<Node> Q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        Q.add(new Node(0, 0, 0, 1));

        while (!Q.isEmpty()) {
            Node now = Q.poll();
            
            visited[now.y][now.x] = true;

            if (now.cnt == K) {
                x = now.x;
                y = now.y;
                return;
            }

            int nextX = now.x + dx[now.dir];
            int nextY = now.y + dy[now.dir];

            if (now.dir == 0) {
                if (nextY < R && !visited[nextY][nextX]) Q.add(new Node(nextX, nextY, now.dir, now.cnt+1));
                else Q.add(new Node(now.x+1, now.y, now.dir+1, now.cnt+1));
            } else if (now.dir == 1) {
                if (nextX < C && !visited[nextY][nextX]) Q.add(new Node(nextX, nextY, now.dir, now.cnt+1));
                else Q.add(new Node(now.x, now.y-1, now.dir+1, now.cnt+1));
            } else if (now.dir == 2) {
                if (nextY >= 0 && !visited[nextY][nextX]) Q.add(new Node(nextX, nextY, now.dir, now.cnt+1));
                else Q.add(new Node(now.x-1, now.y, now.dir+1, now.cnt+1));
            } else {
                if (nextX >= 0 && !visited[nextY][nextX]) Q.add(new Node(nextX, nextY, now.dir, now.cnt+1));
                else Q.add(new Node(now.x, now.y+1, 0, now.cnt+1));
            }
        }
    }
}