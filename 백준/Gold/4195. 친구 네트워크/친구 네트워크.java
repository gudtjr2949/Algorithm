import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int F;
    static int[] parents, rank;
    static int[][] arr;
    static Map<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            F = Integer.parseInt(bf.readLine());

            arr = new int[F][2];

            map = new HashMap<>();

            int idx = 1;

            for (int i = 0 ; i < F ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(a)) map.put(a, idx++);
                if (!map.containsKey(b)) map.put(b, idx++);

                arr[i][0] = map.get(a);
                arr[i][1] = map.get(b);
            }

            parents = new int[idx];
            rank = new int[idx];

            for (int i = 0 ; i < idx ; i++) parents[i] = i;
            Arrays.fill(rank, 1);

            for (int i = 0 ; i < F ; i++) {
                sb.append(union(arr[i][0], arr[i][1])).append("\n");
            }
        }

        System.out.println(sb);
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) {
                parents[a] = b;
                rank[b] += rank[a];
                return rank[b];
            } else {
                parents[b] = a;
                rank[a] += rank[b];
                return rank[a];
            }
        }

        return rank[a];
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
}