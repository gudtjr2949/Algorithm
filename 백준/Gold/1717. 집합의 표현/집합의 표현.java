import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n+1];

        for (int i = 0 ; i <= n ; i++)
            parents[i] = i;

        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(bf.readLine());

            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operation == 0) union(a, b);
            else {
                if (isSameParents(a, b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parents[b] = a;
            } else {
                parents[a] = b;
            }
        }
    }

    static int find(int num) {
        if (num == parents[num]) return num;
        return parents[num] = find(parents[num]);
    }

    static boolean isSameParents(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return true;

        return false;
    }
}