import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, answer, guestIdx;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static Node near;
    static Taxi taxi;
    static List<Guest> guest;
    static class Taxi {
        int x, y, fuel;
        public Taxi(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }
    }

    static class Guest {
        int startX, startY, endX, endY;

        public Guest(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(bf.readLine());
        int y = Integer.parseInt(st.nextToken())-1;
        int x = Integer.parseInt(st.nextToken())-1;
        taxi = new Taxi(x, y, fuel);

        guest = new ArrayList<>();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int startY = Integer.parseInt(st.nextToken())-1;
            int startX = Integer.parseInt(st.nextToken())-1;
            int endY = Integer.parseInt(st.nextToken())-1;
            int endX = Integer.parseInt(st.nextToken())-1;
            guest.add(new Guest(startX, startY, endX, endY));
        }


        for (int i = 0 ; i < M ; i++) {
            guestIdx = 0;
            near = null;
            
            findNear();

            if (near == null || guestIdx == -1) {
                answer = -1;
                break;
            }

            if (taxi.fuel >= near.dist) {
                taxi.fuel -= near.dist;
                taxi.x = near.x;
                taxi.y = near.y;
            } else {
                answer = -1;
                break;
            }

            move();

            if (answer == -1) break;
        }

        if (answer != -1) answer = taxi.fuel;

        System.out.println(answer);
    }


    static void move() {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

        visited[taxi.y][taxi.x] = true;
        PQ.add(new Node(taxi.x, taxi.y, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            if (now.x == guest.get(guestIdx).endX && now.y == guest.get(guestIdx).endY) {
                if (taxi.fuel >= now.dist) {
                    taxi.fuel += (now.dist * 2) - now.dist; // 연료 충전
                    taxi.x = now.x;
                    taxi.y = now.y;
                    guest.remove(guestIdx);
                } else {
                    answer = -1;
                }

                return;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] != 1) {
                    visited[ny][nx] = true;
                    PQ.add(new Node(nx, ny, now.dist + 1));
                }
            }
        }

        answer = -1;
    }

    static void findNear() {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> {
            if (o1.dist == o2.dist) {
                if (o1.y == o2.y) return o1.x - o2.x;
                return o1.y - o2.y;
            }
            return o1.dist - o2.dist;
        });

        visited[taxi.y][taxi.x] = true;
        PQ.add(new Node(taxi.x, taxi.y, 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();

            int findIdx = findGuest(now);
            if (findIdx != -1) {
                guestIdx = findIdx;
                near = new Node(now.x, now.y, now.dist);
                return;
            }


            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && !visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    PQ.add(new Node(nx, ny, now.dist + 1));
                }
            }
        }
    }

    static int findGuest(Node now) {
        for (int i = 0 ; i < guest.size() ; i++) {
            if (guest.get(i).startX == now.x && guest.get(i).startY == now.y) return i;
        }
        return -1;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}