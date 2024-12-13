import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, answer = 1;
    static int[] A;
    static boolean[] visited;
    static Deque<Integer> up, down;
    static Queue<Integer> robot;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N*2];
        visited = new boolean[N*2];
        robot = new LinkedList<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N*2 ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        up = new ArrayDeque<>();
        for (int i = 0 ; i < N ; i++) up.add(i);

        down = new ArrayDeque<>();
        for (int i = N ; i < N*2 ; i++) down.addFirst(i);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        while (true) {
            // 회전
            down.add(up.pollLast());
            up.addFirst(down.poll());

            // 로봇 이동
            moveRobot();

            // 올리는 칸에 로봇 올리기
            if (!visited[up.peek()] && A[up.peek()] > 0) {
                A[up.peek()]--;
                visited[up.peek()] = true;
                robot.add(up.peek());
            }
            
            if (check()) break;

            answer++;
        }
    }

    static void moveRobot() {
        int size = robot.size();

        for (int i = 0 ; i < size ; i++) {
            int now = robot.poll();

            // 내려갈 위치에 있는 로봇
            if (now == up.peekLast() || down.contains(now)) {
                visited[now] = false;
                continue;
            }

            int next = (now+1) % (N*2);

            if (!visited[next] && A[next] > 0) {
                visited[next] = true;
                visited[now] = false;
                A[next]--;
                robot.add(next);
            } else {
                robot.add(now);
            }
        }
    }

    static boolean check() {
        int cnt = 0;

        for (int i = 0 ; i < N*2 ; i++) {
            if (A[i] == 0) cnt++;

            if (cnt >= K) return true;
        }

        return false;
    }
}