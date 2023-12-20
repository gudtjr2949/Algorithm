import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] sumArr;
    static List<boolean[]> adj;
    static Queue<Integer> Q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sumArr = new int[N+1];
        adj = new ArrayList<>();

        for (int i = 0 ; i < N+1 ; i++) {
            adj.add(new boolean[N+1]);
        }

        boolean answer = true;

        Loop:
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int len = Integer.parseInt(st.nextToken());
            int[] arr = new int[len];

            for (int j = 0 ; j < len ; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = len-1 ; j >= 1 ; j--) {
                for (int q = j-1 ; q >= 0 ; q--) {
                    if (adj.get(arr[q])[arr[j]]) {
                        answer = false;
                        break Loop;
                    }
                    adj.get(arr[j])[arr[q]] = true;
                    sumArr[arr[j]] += arr[q];
                }
            }
        }

        if (answer) {
            for (int i = 1; i < N + 1; i++) {
                if (sumArr[i] == 0) {
                    Q.add(i);
                }
            }

            solve();

            for (int i = 1; i < N + 1; i++) {
                if (!check(i)) {
                    answer = false;
                    break;
                }
            }

            if (answer) {
                System.out.println(sb);
            } else {
                System.out.println(0);
            }
        } else {
            System.out.println(0);
        }
    }

    static void solve() {
        while (!Q.isEmpty()) {
            int num = Q.poll();

            sb.append(num).append("\n");

            for (int i = 1 ; i < N+1 ; i++) {
                if (i != num) {
                    if (adj.get(i)[num]) {
                        adj.get(i)[num] = false;
                        if (check(i)) {
                            Q.add(i);
                        }
                    }
                }
            }
        }
    }

    static boolean check(int idx) {
        for (int i = 1 ; i < N+1 ; i++) {
            if (adj.get(idx)[i]) {
                return false;
            }
        }

        return true;
    }
}