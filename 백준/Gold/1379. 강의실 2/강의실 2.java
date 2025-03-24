import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, roomCnt;
    static int[] result;
    static Queue<Lecture> lectures;
    static StringBuilder sb = new StringBuilder();
    static class Lecture {
        int idx, start, end;
        public Lecture(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
    }

    static class Room {
        int idx, end;

        public Room(int idx, int end) {
            this.idx = idx;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(roomCnt);
        System.out.println(sb);
    }

    static void solve() {
        Queue<Room> rooms = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

        while (!lectures.isEmpty()) {
            Lecture now = lectures.poll();

            if (rooms.isEmpty()) {
                result[now.idx] = 1;
                rooms.add(new Room(1, now.end));
            } else {
                if (rooms.peek().end <= now.start) {
                    result[now.idx] = rooms.poll().idx;
                } else {
                    result[now.idx] = rooms.size()+1;
                }
                rooms.add(new Room(result[now.idx], now.end));
            }

            roomCnt = Math.max(roomCnt, rooms.size());
        }

        for (int i = 1 ; i <= N ; i++) {
            sb.append(result[i]).append("\n");
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(idx, start, end));
        }
    }

    static void init() {
        lectures = new PriorityQueue<>((o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });
        result = new int[N+1];
    }
}