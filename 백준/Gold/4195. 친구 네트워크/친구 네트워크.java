import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int idx, F;
    static int[] parents, friends;
    static int[][] operations;
    static Map<String, Integer> map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(sb);
    }

    static void solve() {
        for (int[] operation : operations) {
            sb.append(union(operation[0], operation[1])).append("\n");
        }
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }

    static int union(int A, int B) {
        A = find(A);
        B = find(B);

        if (A == B) return friends[A];

        if (A > B) {
            parents[A] = B;
            friends[B] += friends[A];
            return friends[B];
        }
        else {
            parents[B] = A;
            friends[A] += friends[B];
            return friends[A];
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            F = Integer.parseInt(bf.readLine());
            init();
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());

                String A = st.nextToken();
                if (!map.containsKey(A)) {
                    map.put(A, idx++);
                }

                String B = st.nextToken();
                if (!map.containsKey(B)) {
                    map.put(B, idx++);
                }

                operations[i][0] = map.get(A);
                operations[i][1] = map.get(B);
            }

            solve();
        }
    }

    static void init() {
        idx = 0;

        parents = new int[F*2+1];
        for (int i = 0 ; i <= F*2 ; i++) parents[i] = i;

        friends = new int[F*2+1];
        Arrays.fill(friends, 1);

        operations = new int[F][2];

        map = new HashMap<>();
    }
}