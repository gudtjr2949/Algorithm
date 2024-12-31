import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, P, answer;
    static int[] stations;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        stations = new int[1_000_001];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            stations[idx] = fuel;
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Integer> PQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0 ; i < L ; i++) {
            if (stations[i] != 0) PQ.add(stations[i]);

            if (P == 0) {
                if (PQ.isEmpty()) {
                    answer = -1;
                    return;
                }

                P += PQ.poll();
                answer++;
            }

            P--;
        }
    }
}