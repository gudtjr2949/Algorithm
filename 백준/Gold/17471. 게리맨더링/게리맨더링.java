import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, size, answer = Integer.MAX_VALUE;
    static int[] arr;
    static boolean[] visited;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void solve() {
        for (int i = 1 ; i < N ; i++) {
            visited = new boolean[N+1];
            size = i;
            dfs(1, 0);
        }
    }

    static void dfs(int cur, int cnt) {
        if (cnt == size) {
            if (check()) {
                calculatePeople();
            }
            return;
        }

        for (int i = cur ; i <= N ; i++) {
            visited[i] = true;
            dfs(i+1, cnt+1);
            visited[i] = false;
        }
    }

    static void calculatePeople() {
        int A = 0, B = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (visited[i]) {
                A += arr[i];
            } else {
                B += arr[i];
            }
        }

        answer = Math.min(answer, Math.abs(A - B));
    }

    static boolean check() {
        Queue<Integer> Q = new LinkedList<>();
        int[] tmp = new int[N+1]; // true == 1, false = -1
        for (int i = 1 ; i <= N ; i++) {
            if (visited[i]) {
                Q.add(i);
                tmp[i] = 1;
                break;
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!visited[i]) {
                Q.add(i);
                tmp[i] = -1;
                break;
            }
        }


        while (!Q.isEmpty()) {
            int now = Q.poll();

            for (int next : adj.get(now)) {
                if (tmp[next] == 0 && visited[now] == visited[next]) {
                    tmp[next] = visited[next] ? 1 : -1;
                    Q.add(next);
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (visited[i] && tmp[i] != 1) return false;
            else if (!visited[i] && tmp[i] != -1) return false;
        }

        return true;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j < size ; j++) {
                adj.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void init() {
        arr = new int[N+1];
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
    }
}